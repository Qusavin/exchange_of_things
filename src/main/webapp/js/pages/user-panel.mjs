const myExchangeItemsTabElement = document.querySelector('#my-things-on-exchange-tab');
const myExchangeItemsContainerElement = document.querySelector('#my-things-on-exchange');

const myItemsTabElement = document.querySelector('#my-things-tab');
const myItemsContainerElement = document.querySelector('#my-things');

const exchangeItemsTabElement = document.querySelector('#things-on-exchange-tab');
const exchangeItemsContainerElement = document.querySelector('#things-on-exchange');

const Table = {
    MY_ITEMS: 'my_items',
    EXCHANGE_ITEMS: 'exchange_items',
    MY_EXCHANGE_ITEMS: 'my_exchange_items'
};

let currentTable = Table.MY_ITEMS;

function fetchTable(tableName) {
    currentTable = tableName;
    return fetch(`user-panel?table_part=${tableName}`, {
        method: 'get',
        credentials: 'same-origin'
    })
        .then(res => res.text());
}

myExchangeItemsTabElement.addEventListener('click', () => {
    fetchTable(Table.MY_EXCHANGE_ITEMS)
        .then(html => {
            myExchangeItemsContainerElement.innerHTML = html;
        });
});

myItemsTabElement.addEventListener('click', () => {
    fetchTable(Table.MY_ITEMS)
        .then(html => {
            myItemsContainerElement.innerHTML = html;
            hydrateMyItemsTable();
        });
});

exchangeItemsTabElement.addEventListener('click', () => {
    fetchTable(Table.EXCHANGE_ITEMS)
        .then(html => {
            exchangeItemsContainerElement.innerHTML = html;
        });
});

hydrateMyItemsTable();

function hydrateMyItemsTable() {
    const tableRowElements = myItemsContainerElement.querySelectorAll('.table-row');

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
                .then(html => {
                    myItemsContainerElement.innerHTML = html;
                    hydrateMyItemsTable();
                })
        });

        addToExchangeBtnElement.addEventListener('click', () => {
            fetch(`user-panel?table_part=${currentTable}&exchange_item_id=${itemId}`, {
                method: 'post',
                credentials: 'same-origin',
                cache: 'no-cache'
            })
                .then(res => res.text())
                .then(html => {
                    myItemsContainerElement.innerHTML = html;
                    hydrateMyItemsTable();
                })
        });
    });
}