<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/templates/meta.jsp" %>
    <title>Thing</title>
</head>
<body>
<%@include file="./header.jsp" %>

<div class="container mx-auto pt-14">
    <div class="max-w-sm mx-auto p-6 bg-white border border-gray-200 rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700">
        <img class="rounded-t-lg" src="${item.getImage()}" alt="Item Image"/>
        <div class="p-5 pb-0">
            <h5 class="mb-2 text-3xl font-bold tracking-tight text-gray-900 dark:text-white">
                ${item.getTitle()}
            </h5>
            <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                <div class="font-bold mb-1 text-gray-900">DESCRIPTION</div>
                <p>${item.getDescription()}</p>
            </div>
            <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                <div class="font-bold mb-1 text-gray-900">VIEWS NUMBER</div>
                <p>${item.getViewsNumber()}</p>
            </div>
            <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                <div class="font-bold mb-1 text-gray-900">CATEGORY</div>
                <p>${item.getCategory()}</p>
            </div>
            <div class="pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                <div class="font-bold mb-1 text-gray-900">OWNER</div>
                <p>${item.getOwner().getName()}</p>
            </div>
        </div>
    </div>
</div>

<%@include file="/templates/scripts.jsp" %>
</body>
</html>
