<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../templates/meta.jsp" %>
    <title>Admin Panel</title>
</head>
<body id="admin-panel-page" class="dark:bg-slate-500">
<%@include file="../templates/header.jsp" %>
<div class="container mx-auto pt-14">
    <div class="w-3/5 mx-auto mt-8 overflow-hidden relative drop-shadow-lg sm:rounded-lg">
        <div class="bg-white dark:bg-gray-700 dark:text-gray-400">
            <div id="table-container" class="overflow-y-auto max-h-[66%]">
                <%@include file="/pages/admin-panel/table.jsp" %>
            </div>
            <div class="py-2 px-4 ">
                <button id="add-user" type="button"
                        class="flex items-center ml-auto text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
                        data-modal-toggle="add-user-modal"
                >
                    <span class="material-symbols-outlined text-base font-bold mr-1">add</span>
                    Add user
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/pages/admin-panel/add-user-modal.jsp" %>
<%@include file="/pages/admin-panel/edit-user-modal.jsp" %>
<%@include file="/pages/admin-panel/confirm-delete-modal.jsp" %>

<%@include file="/templates/scripts.jsp" %>
</body>
</html>
