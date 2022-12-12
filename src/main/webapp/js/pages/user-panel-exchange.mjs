import {getUrlencodedFormData, redirect} from '../util.mjs';

const exchangeElement = document.querySelector('#exchange');

const Status = {
    Accepted: 'accepted',
    Rejected: 'rejected',
    InProcess: 'in_process'
};

if (exchangeElement !== null) {
    main();
}

function main() {
    const selectOwnItemsElement = document.querySelector('#select-items');
    const allItemsElement = document.querySelector('#all-items');
    const senItemElement = document.querySelector('#sen-item')
    const recItemElement = document.querySelector('#rec-item');
    const makeRequestBtnElement = document.querySelector('#make-request');
    const acceptRequestBtnElement = document.querySelector('#accept-request');
    const declineRequestBtnElement = document.querySelector('#decline-request');

    const ItemSelector = {
        ImageUrl: '.image',
        Title: '.title',
        Description: '.description',
        ViewsNumber: '.views-number',
        Category: '.category'
    };

    selectOwnItemsElement?.addEventListener('change', () => {
        const itemId = selectOwnItemsElement.value.trim();
        const itemElement = allItemsElement.querySelector(`div.item[data-item-id='${itemId}']`);

        const imageUrl = itemElement.querySelector(ItemSelector.ImageUrl).innerText.trim();
        const title = itemElement.querySelector(ItemSelector.Title).innerText.trim();
        const description = itemElement.querySelector(ItemSelector.Description).innerText.trim();
        const viewsNumber = itemElement.querySelector(ItemSelector.ViewsNumber).innerText.trim();
        const category = itemElement.querySelector(ItemSelector.Category).innerText.trim();

        senItemElement.setAttribute('data-item-id', itemId);
        senItemElement.querySelector(ItemSelector.ImageUrl).src = imageUrl;
        senItemElement.querySelector(ItemSelector.Title).innerHTML = title;
        senItemElement.querySelector(ItemSelector.Description).innerHTML = description;
        senItemElement.querySelector(ItemSelector.ViewsNumber).innerHTML = viewsNumber;
        senItemElement.querySelector(ItemSelector.Category).innerHTML = category;
    });

    const changeRequestStatus = status => {
        const senItemId = senItemElement.getAttribute('data-item-id').trim();
        const recItemId = recItemElement.getAttribute('data-item-id').trim();

        const formData = new FormData();

        formData.set('sen_item_id', senItemId);
        formData.set('rec_item_id', recItemId);
        formData.set('status', status);

        fetch('user-panel/exchange', {
            method: 'post',
            body: getUrlencodedFormData(formData),
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        })
            .then(res => res.text())
            .then(() => redirect('user-panel'));
    };

    makeRequestBtnElement?.addEventListener('click', () => changeRequestStatus(Status.InProcess));
    acceptRequestBtnElement?.addEventListener('click', () => changeRequestStatus(Status.Accepted));
    declineRequestBtnElement?.addEventListener('click', () => changeRequestStatus(Status.Rejected));
}
