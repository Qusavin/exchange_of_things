<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
    <thead class="sticky top-0 text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
    <tr>
        <th scope="col" class="py-3 px-6">
            Name
        </th>
        <th scope="col" class="py-3 px-6">
            Username
        </th>
        <th scope="col" class="py-3 px-6">
            Password
        </th>
        <th scope="col" class="py-3 px-6">
            Status
        </th>
        <th scope="col" class="py-3 px-6">
            Role
        </th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
        <c:if test="${user.getId() != u.getId()}">
            <tr class="table-row bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <td class="id hidden">
                        ${u.getId()}
                </td>
                <td class="name py-4 px-6">
                        ${u.getName()}
                </td>
                <td class="username py-4 px-6">
                        ${u.getUsername()}
                </td>
                <td class="password py-4 px-6">
                        ${u.getPassword()}
                </td>
                <td class="is-blocked hidden">
                        ${u.getBlocked()}
                </td>
                <td class="py-4 px-6">
                    <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full ${u.getOnline() ? "bg-green-400" : "bg-red-500"} mr-2"></div>
                            ${u.getOnline() ? "Online" : "Offline"}
                    </div>
                </td>
                <td class="role py-4 px-6">
                        ${u.getRole()}
                </td>
                <td>
                    <div class="flex items-center flex-nowrap space-x-2">
                        <button type="button"
                                class="edit-btn text-lg text-purple-600 dark:text-white"
                                data-modal-toggle="edit-user-modal"
                        >
                            <span class="material-symbols-outlined font-bold">edit_note</span>
                        </button>
                        <button type="button"
                                class="delete-btn text-lg text-purple-600 dark:text-white"
                                data-modal-toggle="confirm-delete-modal"
                        >
                            <span class="material-symbols-outlined font-bold">delete</span>
                        </button>
                    </div>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
