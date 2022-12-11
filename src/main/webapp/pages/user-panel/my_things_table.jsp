<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
    <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
            <th scope="col" class="py-3 px-6">
                <span class="sr-only">Image</span>
            </th>
            <th scope="col" class="py-3 px-6">
                Title
            </th>
            <th scope="col" class="py-3 px-6">
                Category
            </th>
            <th scope="col" class="py-3 px-6">
                Description
            </th>
            <th scope="col" class="py-3 px-6"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${items}" var="item">
            <tr class="table-row bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <td class="p-4 w-32">
                    <img src="https://flowbite.com/docs/images/products/apple-watch.png" alt="Apple Watch">
                </td>
                <td class="hidden thing-id py-4 px-6 font-semibold">
                        ${item.getId()}
                </td>
                <td class="title py-4 px-6 font-semibold">
                        ${item.getTitle()}
                </td>
                <td class="category py-4 px-6">
                        ${item.getCategory()}
                </td>
                <td class="description py-4 px-6">
                        ${item.getDescription()}
                </td>
                <td class="py-4 px-6">
                    <div class="flex flex-col justify-center space-y-2">
                        <button type="button"
                                class="exchange text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                            Add to Exchange
                        </button>
                        <button type="button"
                                class="remove text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                            Remove
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
