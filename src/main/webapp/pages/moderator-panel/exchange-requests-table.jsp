<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
    <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
    <tr>
        <th scope="col"></th>
        <th scope="col" class="py-3 text-center">
            First item
        </th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col" class="py-3 text-center">
            Second item
        </th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    <tr>
        <th scope="col" class="py-3 px-6">
            Image
        </th>
        <th scope="col" class="py-3 px-6">
            Title
        </th>
        <th scope="col" class="py-3 px-6">
            Owner
        </th>
        <th scope="col" class="py-3 px-6">
            Image
        </th>
        <th scope="col" class="py-3 px-6">
            Title
        </th>
        <th scope="col" class="py-3 px-6">
            Owner
        </th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${exchangeRequests}" var="exchangeRequest">
        <tr class="table-row bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
            <td class="hidden sen-item-id">${exchangeRequest.getSenderItem().getId()}</td>
            <td class="hidden rec-item-id">${exchangeRequest.getReceiverItem().getId()}</td>
            <td class="hidden id py-4 px-6 font-semibold">${exchangeRequest.getId()}</td>
            <td class="p-4 w-32">
                <img src="${exchangeRequest.getSenderItem().getImage()}" alt="Item image">
            </td>
            <td class="py-4 px-6 font-semibold">${exchangeRequest.getSenderItem().getTitle()}</td>
            <td class="py-4 px-6">${exchangeRequest.getSenderItem().getOwner().getName()}</td>
            <td class="p-4 w-32">
                <img src="${exchangeRequest.getReceiverItem().getImage()}" alt="Item image">
            </td>
            <td class="py-4 px-6 font-semibold">${exchangeRequest.getReceiverItem().getTitle()}</td>
            <td class="py-4 px-6">${exchangeRequest.getReceiverItem().getOwner().getName()}</td>
            <td>
                <button type="button"
                        class="decline-exchange text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
                >Decline</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
