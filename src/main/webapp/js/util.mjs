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

export function getUrl(relativePath) {
    return [
        window.location.origin,
        'exchange-of-things',
        relativePath
    ].join('/');
}

export function redirect(path) {
    window.location.href = getUrl(path);
}

export async function makeRequest(relativePath, options, plainText = true) {
    const method = options.method ?? 'get';

    const response = await fetch(getUrl(relativePath), {
        method,
        body: options.body,
        credentials: 'same-origin',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
    });

    const status = response.status;
    const data = plainText
        ? await response.text()
        : await response.json();

    return {status, data};
}
