<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/templates/meta.jsp" %>
    <title>User Panel</title>
</head>
<body class="dark:bg-slate-500">
<%@include file="./user-panel/header.jsp" %>

<div id="user-panel" class="container mx-auto pt-14">
    <div class="mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
        <div class="mb-4 border-b border-gray-200 dark:border-gray-700">
            <ul class="flex flex-wrap -mb-px text-sm font-medium text-center" id="myTab" data-tabs-toggle="#tab-content"
                role="tablist">
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2"
                            id="my-things-tab"
                            data-tabs-target="#my-things"
                            type="button"
                            role="tab"
                            aria-controls="my-things"
                            aria-selected="false"
                    >My things</button>
                </li>
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300"
                            id="my-things-on-exchange-tab"
                            data-tabs-target="#my-things-on-exchange"
                            type="button"
                            role="tab"
                            aria-controls="my-things-on-exchange"
                            aria-selected="false"
                    >My things on exchange</button>
                </li>
                <li class="mr-2" role="presentation">
                    <button class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300"
                            id="things-on-exchange-tab"
                            data-tabs-target="#things-on-exchange"
                            type="button"
                            role="tab"
                            aria-controls="things-on-exchange"
                            aria-selected="false"
                    >Things on exchange</button>
                </li>
            </ul>
        </div>
        <div id="tab-content">
            <div class="hidden rounded-lg"
                 id="my-things"
                 role="tabpanel"
                 aria-labelledby="my-things-tab">
                <div id="my-things-container" class="mb-3">
                    <%@include file="user-panel/my_things_table.jsp" %>
                </div>
                <div>
                    <button type="button"
                            class="exchange block ml-auto text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
                            data-modal-toggle="add-thing-modal"
                    >Add thing</button>
                </div>
            </div>
            <div class="hidden rounded-lg"
                 id="my-things-on-exchange"
                 role="tabpanel"
                 aria-labelledby="my-things-on-exchange-tab">
                <div id="my-things-on-exchange-container"></div>
            </div>
            <div class="hidden rounded-lg"
                 id="things-on-exchange"
                 role="tabpanel"
                 aria-labelledby="things-on-exchange-tab">
                <div id="things-on-exchange-container"></div>
            </div>
        </div>
    </div>
</div>

<%@include file="./user-panel/add-thing.jsp" %>
<%@include file="../templates/scripts.jsp" %>
</body>
</html>
