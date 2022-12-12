import {getUrlencodedFormData, makeRequest, redirect} from '../util.mjs';

const loginElement = document.querySelector('#login-page');

if (loginElement !== null) {
    main();
}

function main() {
    const hiddenClass = 'hidden';
    const loginFormElement = document.querySelector('#login-form');

    const usernameElement = document.querySelector('#username');
    const passwordElement = document.querySelector('#password');

    const usernameErrorElement = document.querySelector('#username-error');
    const passwordErrorElement = document.querySelector('#password-error');

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

    loginFormElement.addEventListener('submit', async e => {
        e.preventDefault();

        const formData = new FormData(loginFormElement);

        const response = await makeRequest('login', {
            method: 'post',
            body: getUrlencodedFormData(formData)
        });

        if (response.status === 400) {
            usernameErrorElement.classList.remove(hiddenClass);
            passwordErrorElement.classList.remove(hiddenClass);
            return;
        }

        const {redirectUrl} = response.data;

        redirect(redirectUrl);
    });
}
