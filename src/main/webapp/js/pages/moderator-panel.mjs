import {getUrlencodedFormData, makeRequest} from '../util.mjs';

const moderatorPanelElement = document.querySelector('#moderator-panel-page');
const usersContainerElement = document.querySelector('#users-container');
const userThingsContainerElement = document.querySelector('#user-things-container');
const exchangeRequestsContainerElement = document.querySelector('#exchange-requests-container');

const hiddenClass = 'hidden';

const Status = {
    Accepted: 'accepted',
    Rejected: 'rejected',
    InProcess: 'in_process'
};

if (moderatorPanelElement !== null) {
    main();
}

function main() {
    hydrateExchangeRequestsTable();
    hydrateUsersTable();
    hydrateUserThingsTable();
}

function hydrateExchangeRequestsTable() {
    const tableRowElements = exchangeRequestsContainerElement.querySelectorAll('.table-row');

    [...tableRowElements].forEach(tableRowElement => {
        const senItemId = tableRowElement
            .querySelector('.sen-item-id').innerText.trim();
        const recItemId = tableRowElement
            .querySelector('.rec-item-id').innerText.trim();
        const declineBtnElement = tableRowElement
            .querySelector('.decline-exchange');

        const formData = new FormData();

        formData.set('sen_item_id', senItemId);
        formData.set('rec_item_id', recItemId);
        formData.set('status', Status.Rejected);

        declineBtnElement.addEventListener('click', () => {
            makeRequest('moderator-panel', {
                body: getUrlencodedFormData(formData),
                method: 'post',
            }).then(html => {
                exchangeRequestsContainerElement.innerHTML = html;
                hydrateExchangeRequestsTable();
            });
        });
    });
};

function hydrateUsersTable() {
    const saveChangesBtnElement = usersContainerElement.querySelector('#save-changes');

    const tableRowElements = usersContainerElement.querySelectorAll('.table-row');
    const initialState = [...tableRowElements].map(rowElement => {
        const userId = rowElement.querySelector('.id').innerText.trim();
        const isBlocked = rowElement.querySelector('.is-block-checkbox').checked;

        return {
            userId,
            isBlocked
        };
    });
    const state = initialState.map(rowState => ({...rowState}));
    const checkboxElements = usersContainerElement.querySelectorAll('.is-block-checkbox');

    let changes = 0;

    [...checkboxElements].forEach((checkboxElement, index) => {
        checkboxElement.onclick = () => {
            state[index].isBlocked = checkboxElement.checked;

            const changesCount = state.reduce(
                (acc, value, index) =>
                    value.isBlocked === initialState[index].isBlocked ? acc : acc + 1,
                0
            );

            changes = changesCount;

            if (changesCount === 0) {
                if (!saveChangesBtnElement.parentElement.classList.contains(hiddenClass)) {
                    saveChangesBtnElement.parentElement.classList.add(hiddenClass);
                }
            } else {
                saveChangesBtnElement.parentElement.classList.remove(hiddenClass);
            }
        };
    });

    saveChangesBtnElement.onclick = () => {
        if (changes === 0) {
            return;
        }

        const userIds = state
            .filter((rowState, index) =>
                rowState.isBlocked !== initialState[index].isBlocked
            )
            .map(rowState => rowState.userId.trim());

        const formData = new FormData();

        userIds.forEach(id => formData.append('id', id));

        makeRequest('moderator-panel', {
            body: getUrlencodedFormData(formData),
            method: 'post'
        }).then(() => window.location.reload());
    };
}

function hydrateUserThingsTable() {
    const tableRowElements = userThingsContainerElement.querySelectorAll('.table-row');

    [...tableRowElements].forEach(tableRowElement => {
        const itemId = tableRowElement.querySelector('.thing-id').innerText.trim();
        const removeBtnElement = tableRowElement.querySelector('.remove-btn');

        removeBtnElement.addEventListener('click', () => {
            const formData = new FormData();

            formData.set('remove_item_id', itemId);

            makeRequest('moderator-panel', {
                body: getUrlencodedFormData(formData),
                method: 'post',
            }).then(html => {
                userThingsContainerElement.innerHTML = html;
                hydrateUserThingsTable();
            });
        });
    });
}
