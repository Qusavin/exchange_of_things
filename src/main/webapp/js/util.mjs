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
