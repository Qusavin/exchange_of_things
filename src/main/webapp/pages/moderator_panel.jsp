<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../templates/meta.jsp" %>
    <title>Moderator Panel</title>
</head>
<body id="moderator-panel-page" class="dark:bg-slate-500">
<%@include file="/templates/header.jsp" %>
<div class="container mx-auto pt-14">
    <div class="w-3/5 mx-auto mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
        <div class="mb-4 border-b border-gray-200 dark:border-gray-700">
            <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" id="myTab" data-tabs-toggle="#tab-content"
                role="tablist">
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2"
                            id="users-tab"
                            data-tabs-target="#users"
                            type="button"
                            role="tab"
                            aria-controls="users"
                            aria-selected="false"
                    >Users</button>
                </li>
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300"
                            id="user-things-tab"
                            data-tabs-target="#user-things"
                            type="button"
                            role="tab"
                            aria-controls="user-things"
                            aria-selected="false"
                    >User things</button>
                </li>
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300"
                            id="exchange-requests-tab"
                            data-tabs-target="#exchange-requests"
                            type="button"
                            role="tab"
                            aria-controls="exchange-requests"
                            aria-selected="false"
                    >Exchange requests</button>
                </li>
            </ul>
        </div>
        <div id="tab-content">
            <div class="hidden rounded-lg"
                 id="users"
                 role="tabpanel"
                 aria-labelledby="my-things-tab">
                <div id="users-container" class="mb-3">
                    <%@include file="/pages/moderator-panel/users-table.jsp" %>
                </div>
            </div>
            <div class="hidden rounded-lg"
                 id="user-things"
                 role="tabpanel"
                 aria-labelledby="my-things-on-exchange-tab">
                <div id="user-things-container">
                    <%@include file="/pages/moderator-panel/things-table.jsp" %>
                </div>
            </div>
            <div class="hidden rounded-lg"
                 id="exchange-requests"
                 role="tabpanel"
                 aria-labelledby="things-on-exchange-tab">
                <div id="exchange-requests-container">
                    <%@include file="/pages/moderator-panel/exchange-requests-table.jsp" %>
                </div>
            </div>
        </div>
   </div>
</div>

<%@include file="/templates/scripts.jsp" %>
</body>
</html>
