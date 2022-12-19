export function getUrlencodedFormData(formData) {
    const params = new URLSearchParams();

    for (const pair of formData.entries()) {
        const value = pair[1];

        if (typeof value === 'string' && value !== '') {
            params.append(pair[0], value);
        }
    }

    return params.toString();
}

export function redirect(path) {
    const url = [
        window.location.origin,
        'exchange-of-things',
        path
    ].join('/');

    window.location.href = url;
}

export async function makeRequest(url, options, plainText = true) {
    const body = options.body ?? {};
    const method = options.method ?? 'get';

    const response = await fetch(url, {
        method,
        body,
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    });

    const status = response.status;
    const data = plainText
        ? await response.text()
        : await response.json();

    return {status, data};
}
