import {getUrlencodedFormData, makeRequest, redirect} from '../util.mjs';

const userPanelElement = document.querySelector('#user-panel');

const Table = {
    MY_ITEMS: 'my_items',
    EXCHANGE_ITEMS: 'exchange_items',
    MY_EXCHANGE_ITEMS: 'my_exchange_items'
};

const tableHydrateFunction = {
    [Table.MY_ITEMS]: hydrateMyItemsTable,
    [Table.MY_EXCHANGE_ITEMS]: hydrateMyExchangeItemsTable,
    [Table.EXCHANGE_ITEMS]: hydrateExchangeItemsTable,
};
let tableContainer = {};

let currentTable = Table.MY_ITEMS;

if (userPanelElement !== null) {
    main();
}

function main() {
    const myExchangeItemsTabElement = document.querySelector('#my-things-on-exchange-tab');
    const myExchangeItemsContainerElement = document.querySelector('#my-things-on-exchange-container');

    const myItemsTabElement = document.querySelector('#my-things-tab');
    const myItemsContainerElement = document.querySelector('#my-things-container');

    const exchangeItemsTabElement = document.querySelector('#things-on-exchange-tab');
    const exchangeItemsContainerElement = document.querySelector('#things-on-exchange-container');

    [...document.querySelectorAll('.remove-notification-btn')].forEach(removeNotificationBtnEl => {
        const notificationId = removeNotificationBtnEl.getAttribute('data-notification-id');

        removeNotificationBtnEl.addEventListener('click', async () => {
            await makeRequest(`notification?notification_id=${notificationId}`, {
                method: 'post'
            });
            removeNotificationBtnEl.parentElement.parentElement.remove();
        });
    });

    tableContainer = {
        [Table.MY_ITEMS]: myItemsContainerElement,
        [Table.MY_EXCHANGE_ITEMS]: myExchangeItemsContainerElement,
        [Table.EXCHANGE_ITEMS]: exchangeItemsContainerElement,
    };

    myItemsTabElement.addEventListener('click', () => {
        fetchTable(Table.MY_ITEMS)
            .then(html => renderCurrentTable(html));
    });

    myExchangeItemsTabElement.addEventListener('click', () => {
        fetchTable(Table.MY_EXCHANGE_ITEMS)
            .then(html => renderCurrentTable(html));
    });

    exchangeItemsTabElement.addEventListener('click', async () => {
        renderCurrentTable(await fetchTable(Table.MY_ITEMS));
        renderCurrentTable(await fetchTable(Table.MY_EXCHANGE_ITEMS));
        renderCurrentTable(await fetchTable(Table.EXCHANGE_ITEMS));
    });

    hydrateMyItemsTable();

    const hiddenClass = 'hidden';
    const addThingFormElement = document.querySelector('#add-thing-form');

    const addThingModalCloseElement = document.querySelector('#add-thing-modal-close');

    const addTitleElement = document.querySelector('#add-title');
    const addImageUrlElement = document.querySelector('#add-image-url');
    const addCategoryElement = document.querySelector('#add-category');
    const addDescriptionElement = document.querySelector('#add-description');

    const addTitleErrorElement = document.querySelector('#add-title-error');
    const addImageUrlErrorElement = document.querySelector('#add-image-url-error');
    const addCategoryErrorElement = document.querySelector('#add-category-error');
    const addDescriptionErrorElement = document.querySelector('#add-description-error');

    addTitleElement.addEventListener('input', () => {
        if (!addTitleErrorElement.classList.contains(hiddenClass)) {
            addTitleErrorElement.classList.add(hiddenClass);
        }
    });
    addImageUrlElement.addEventListener('input', () => {
        if (!addImageUrlErrorElement.classList.contains(hiddenClass)) {
            addImageUrlErrorElement.classList.add(hiddenClass);
        }
    });
    addCategoryElement.addEventListener('input', () => {
        if (!addCategoryErrorElement.classList.contains(hiddenClass)) {
            addCategoryErrorElement.classList.add(hiddenClass);
        }
    });
    addDescriptionElement.addEventListener('input', () => {
        if (!addDescriptionErrorElement.classList.contains(hiddenClass)) {
            addDescriptionErrorElement.classList.add(hiddenClass);
        }
    });

    addThingFormElement.addEventListener('submit', e => {
        e.preventDefault();

        const formData = new FormData(addThingFormElement);

        let formValid = true;

        if (addTitleElement.value.trim() === '') {
            addTitleErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (addImageUrlElement.value.trim() === '') {
            addImageUrlErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (addCategoryElement.value.trim() === '') {
            addCategoryErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (addDescriptionElement.value.trim() === '') {
            addDescriptionErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (!formValid) {
            return;
        }

        makeRequest(`user-panel?table_part=${currentTable}&add_item=true`, {
            body: getUrlencodedFormData(formData),
            method: 'post'
        }).then(res => renderCurrentTable(res.data));

        addThingModalCloseElement.click();

        addTitleElement.value = '';
        addImageUrlElement.value = '';
        addCategoryElement.value = '';
        addDescriptionElement.value = '';
    });
}

function renderCurrentTable(html) {
    tableContainer[currentTable].innerHTML = html;
    tableHydrateFunction[currentTable]();
}

function fetchTable(tableName) {
    currentTable = tableName;

    return makeRequest(`user-panel?table_part=${tableName}`, {
        method: 'get'
    }).then(res => res.data);
}

function hydrateExchangeItemsTable() {
    const tableRowElements = tableContainer[Table.EXCHANGE_ITEMS].querySelectorAll('.table-row');
    const disableButtons = ![...document.querySelector('#my-things-container').querySelectorAll('.exchange')]
        .some(btn => btn.disabled);

    [...tableRowElements].forEach(tableRowElement => {
        const itemId = tableRowElement.querySelector('.thing-id').innerText.trim();
        const requestExchangeBtnElement = tableRowElement.querySelector('.request-exchange');
        const openBtnElement = tableRowElement.querySelector('.open');

        requestExchangeBtnElement.disabled = disableButtons;

        if (!disableButtons) {
            requestExchangeBtnElement.addEventListener('click', () => {
                redirect(`user-panel/exchange?rec_item_id=${itemId}`);
            });
        }

        openBtnElement.addEventListener('click', () => {
            redirect(`user-panel/thing?item_id=${itemId}`);
        });
    });
}

function hydrateMyExchangeItemsTable() {
    const tableRowElements = tableContainer[Table.MY_EXCHANGE_ITEMS].querySelectorAll('.table-row');

    [...tableRowElements].forEach(tableRowElement => {
        const removeBtnElement = tableRowElement.querySelector('.remove');
        const openBtnElement = tableRowElement.querySelector('.open');
        const itemId = tableRowElement.querySelector('.thing-id').innerText.trim();

        removeBtnElement.addEventListener('click', () => {
            makeRequest(`user-panel?table_part=${currentTable}&exchange_item_id=${itemId}`, {
                method: 'post',
            }).then(res => renderCurrentTable(res.data));
        });
        openBtnElement.addEventListener('click', () => {
            redirect(`user-panel/thing?item_id=${itemId}`);
        });
    });
}

function hydrateMyItemsTable() {
    const tableRowElements = tableContainer[Table.MY_ITEMS].querySelectorAll('.table-row');

    [...tableRowElements].forEach(tableRowElement => {
        const removeBtnElement = tableRowElement.querySelector('.remove');
        const addToExchangeBtnElement = tableRowElement.querySelector('.exchange');
        const itemId = tableRowElement.querySelector('.thing-id').innerText.trim();

        removeBtnElement.addEventListener('click', () => {
            makeRequest(`user-panel?table_part=${currentTable}&remove_item_id=${itemId}`, {
                method: 'post',
            }).then(res => renderCurrentTable(res.data));
        });

        addToExchangeBtnElement.addEventListener('click', () => {
            makeRequest(`user-panel?table_part=${currentTable}&exchange_item_id=${itemId}`, {
                method: 'post'
            }).then(res => renderCurrentTable(res.data));
        });
    });
}
