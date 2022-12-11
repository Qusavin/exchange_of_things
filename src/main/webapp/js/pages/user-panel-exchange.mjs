const exchangeElement = document.querySelector('#exchange');

if (exchangeElement !== null) {
    main();
}

function main() {
    const selectOwnItemsElement = document.querySelector('#select-items');
    const allItemsElement = document.querySelector('#all-items');
    const myItemElement = document.querySelector('#my-item')
    const otherItemElement = document.querySelector('#other-item');
    const makeRequestBtnElement = document.querySelector('#make-request');

    const ItemSelector = {
        ImageUrl: '.image',
        Title: '.title',
        Description: '.description',
        ViewsNumber: '.views-number',
        Category: '.category'
    };

    selectOwnItemsElement.addEventListener('change', () => {
        const itemId = selectOwnItemsElement.value.trim();
        const itemElement = allItemsElement.querySelector(`div.item[data-item-id='${itemId}']`);

        const imageUrl = itemElement.querySelector(ItemSelector.ImageUrl).innerText.trim();
        const title = itemElement.querySelector(ItemSelector.Title).innerText.trim();
        const description = itemElement.querySelector(ItemSelector.Description).innerText.trim();
        const viewsNumber = itemElement.querySelector(ItemSelector.ViewsNumber).innerText.trim();
        const category = itemElement.querySelector(ItemSelector.Category).innerText.trim();

        myItemElement.setAttribute('data-item-id', itemId);
        myItemElement.querySelector(ItemSelector.ImageUrl).src = imageUrl;
        myItemElement.querySelector(ItemSelector.Title).innerHTML = title;
        myItemElement.querySelector(ItemSelector.Description).innerHTML = description;
        myItemElement.querySelector(ItemSelector.ViewsNumber).innerHTML = viewsNumber;
        myItemElement.querySelector(ItemSelector.Category).innerHTML = category;
    });

    makeRequestBtnElement.addEventListener('click', () => {
        const itemId = myItemElement.getAttribute('data-item-id').trim();
        const otherItemId = otherItemElement.getAttribute('data-item-id').trim();

        console.log('myItemId', itemId);
        console.log('otherItemId', otherItemId);
    });
}
