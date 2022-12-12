import {getUrlencodedFormData, redirect} from '../util.mjs';

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

    exchangeItemsTabElement.addEventListener('click', () => {
        fetchTable(Table.EXCHANGE_ITEMS)
            .then(html => renderCurrentTable(html));
    });

    hydrateMyItemsTable();

    const addThingFormElement = document.querySelector('#add-thing-form');

    addThingFormElement.addEventListener('submit', e => {
        e.preventDefault();

        const formData = new FormData(addThingFormElement);

        fetch(`user-panel?table_part=${currentTable}&add_item=true`, {
            method: 'post',
            body: getUrlencodedFormData(formData),
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
            .then(res => res.text())
            .then(html => renderCurrentTable(html));
    });
}

function renderCurrentTable(html) {
    tableContainer[currentTable].innerHTML = html;
    tableHydrateFunction[currentTable]();
}

function fetchTable(tableName) {
    currentTable = tableName;
    return fetch(`user-panel?table_part=${tableName}`, {
        method: 'get',
        credentials: 'same-origin'
    })
        .then(res => res.text());
}

function hydrateExchangeItemsTable() {
    const tableRowElements = tableContainer[Table.EXCHANGE_ITEMS].querySelectorAll('.table-row');

    [...tableRowElements].forEach(tableRowElement => {
        const itemId = tableRowElement.querySelector('.thing-id').innerText.trim();
        const requestExchangeBtnElement = tableRowElement.querySelector('.request-exchange');
        const openBtnElement = tableRowElement.querySelector('.open');

        requestExchangeBtnElement.addEventListener('click', () => {
            redirect(`user-panel/exchange?rec_item_id=${itemId}`);
        });
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
            fetch(`user-panel?table_part=${currentTable}&exchange_item_id=${itemId}`, {
                method: 'post',
                credentials: 'same-origin',
                cache: 'no-cache'
            })
                .then(res => res.text())
                .then(html => renderCurrentTable(html));
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
            fetch(`user-panel?table_part=${currentTable}&remove_item_id=${itemId}`, {
                method: 'post',
                credentials: 'same-origin',
                cache: 'no-cache'
            })
                .then(res => res.text())
                .then(html => renderCurrentTable(html));
        });

        addToExchangeBtnElement.addEventListener('click', () => {
            fetch(`user-panel?table_part=${currentTable}&exchange_item_id=${itemId}`, {
                method: 'post',
                credentials: 'same-origin',
                cache: 'no-cache'
            })
                .then(res => res.text())
                .then(html => renderCurrentTable(html));
        });
    });
}
