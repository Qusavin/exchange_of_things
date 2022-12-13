import {getUrlencodedFormData, makeRequest, redirect} from '../util.mjs';

const adminPanelElement = document.querySelector('#admin-panel-page');
const Mode = {
    ADD_USER: 'add_user',
    EDIT_USER: 'edit_user',
    DELETE_USER: 'delete_user'
};
const hiddenClass = 'hidden';

if (adminPanelElement !== null) {
    main();
}

function main() {
    handleUserAdding();
    handleUserEditing();
}

function handleUserEditing() {
    const editUserFormElement = document.querySelector('#edit-user-form');

    const idElement = editUserFormElement.querySelector('#edit-id');
    const nameElement = editUserFormElement.querySelector('#edit-name');
    const usernameElement = editUserFormElement.querySelector('#edit-username');
    const passwordElement = editUserFormElement.querySelector('#edit-password');
    const isBlockedElement = editUserFormElement.querySelector('#edit-is-blocked');
    const roleElement = editUserFormElement.querySelector('#edit-role');

    const nameErrorElement = editUserFormElement.querySelector('#edit-name-error');
    const usernameErrorElement = editUserFormElement.querySelector('#edit-username-error');
    const passwordErrorElement = editUserFormElement.querySelector('#edit-password-error');

    nameElement.addEventListener('input', () => {
        if (!nameErrorElement.classList.contains(hiddenClass)) {
            nameErrorElement.classList.add(hiddenClass);
        }
    });

    usernameElement.addEventListener('input', () => {
        if (!usernameErrorElement.classList.contains(hiddenClass)) {
            usernameErrorElement.classList.add(hiddenClass);
        }
    });

    passwordElement.addEventListener('input', () => {
        if (!passwordErrorElement.classList.contains(hiddenClass)) {
            passwordErrorElement.classList.add(hiddenClass);
        }
    });

    const tableRowElements = document.querySelectorAll('.table-row');
    const confirmDeleteBtnElement = document.querySelector('#confirm-delete');
    let deleteId;

    [...tableRowElements].forEach(tableRowElement => {
        const id = tableRowElement.querySelector('.id').innerText.trim();
        const name = tableRowElement.querySelector('.name').innerText.trim();
        const username = tableRowElement.querySelector('.username').innerText.trim();
        const password = tableRowElement.querySelector('.password').innerText.trim();
        const isBlocked = tableRowElement.querySelector('.is-blocked').innerText.trim();
        const role = tableRowElement.querySelector('.role').innerText.trim();

        const editBtnElement = tableRowElement.querySelector('.edit-btn');
        const deleteBtnElement = tableRowElement.querySelector('.delete-btn');

        editBtnElement.addEventListener('click', () => {
            idElement.value = id;
            nameElement.value = name;
            usernameElement.value = username;
            passwordElement.value = password;
            isBlockedElement.value = isBlocked;
            roleElement.value = role;
        });

        deleteBtnElement.addEventListener('click', () => {
            deleteId = id;
        });
    });

    confirmDeleteBtnElement.addEventListener('click', () => {
        const formData = new FormData();

        formData.set('id', deleteId);

        fetchTable(formData, Mode.DELETE_USER)
    });

    editUserFormElement.addEventListener('submit', e => {
        e.preventDefault();

        const formData = new FormData(editUserFormElement);

        let formValid = true;

        if (nameElement.value.trim() === '') {
            nameErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (usernameElement.value.trim() === '') {
            usernameErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (passwordElement.value.trim() === '') {
            passwordErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (!formValid) {
            return;
        }

        fetchTable(formData, Mode.EDIT_USER)
    });
}

function handleUserAdding() {
    const addUserFormElement = document.querySelector('#add-user-form');

    const nameElement = addUserFormElement.querySelector('#add-name');
    const usernameElement = addUserFormElement.querySelector('#add-username');
    const passwordElement = addUserFormElement.querySelector('#add-password');

    const nameErrorElement = addUserFormElement.querySelector('#add-name-error');
    const usernameErrorElement = addUserFormElement.querySelector('#add-username-error');
    const passwordErrorElement = addUserFormElement.querySelector('#add-password-error');

    nameElement.addEventListener('input', () => {
        if (!nameErrorElement.classList.contains(hiddenClass)) {
            nameErrorElement.classList.add(hiddenClass);
        }
    });

    usernameElement.addEventListener('input', () => {
        if (!usernameErrorElement.classList.contains(hiddenClass)) {
            usernameErrorElement.classList.add(hiddenClass);
        }
    });

    passwordElement.addEventListener('input', () => {
        if (!passwordErrorElement.classList.contains(hiddenClass)) {
            passwordErrorElement.classList.add(hiddenClass);
        }
    });

    addUserFormElement.addEventListener('submit', e => {
        e.preventDefault();

        const formData = new FormData(addUserFormElement);

        let formValid = true;

        if (nameElement.value.trim() === '') {
            nameErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (usernameElement.value.trim() === '') {
            usernameErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (passwordElement.value.trim() === '') {
            passwordErrorElement.classList.remove(hiddenClass);
            formValid = false;
        }

        if (!formValid) {
            return;
        }

        fetchTable(formData, Mode.ADD_USER)
    });
}

function fetchTable(formData, mode) {
    fetch(`admin-panel?mode=${mode}`, {
        method: 'post',
        body: getUrlencodedFormData(formData),
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        }
    }).then(() => window.location.reload());
}