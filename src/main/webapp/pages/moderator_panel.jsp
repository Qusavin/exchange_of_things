<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../templates/meta.jsp" %>
    <title>Moderator Panel</title>
</head>
<body class="dark:bg-slate-500">
<%@include file="../templates/header.jsp" %>
<div class="container mx-auto pt-14">
    <div class="w-3/5 mx-auto mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="py-3 px-6">
                        Name
                    </th>
                    <th scope="col" class="py-3 px-6">
                        Username
                    </th>
                    <th scope="col" class="py-3 px-6">
                        Is Blocked
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-row bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                    <td class="id hidden">
                        123
                    </td>
                    <td class="name py-4 px-6">
                        Misha
                    </td>
                    <td class="username py-4 px-6">
                        Qusavin
                    </td>
                    <td class="is-blocked py-4 px-6">
                        <div class="flex items-center">
                            <input
                               type="checkbox"
                               class="block-checkbox w-4 h-4 text-purple-600 bg-gray-100 rounded border-gray-300 focus:ring-purple-500 dark:focus:ring-purple-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                            >
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="px-4 py-2 bg-white dark:bg-gray-700 dark:text-gray-400">
            <button id="save-changes" type="button"
                    class="ml-auto block text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                Save changes
            </button>
        </div>
    </div>
</div>

<%@include file="../templates/scripts.jsp" %>
</body>
</html>
