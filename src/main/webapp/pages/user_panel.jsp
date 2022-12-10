<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         import="ru.rsreu.exchangeofthings.data.Link,java.util.List,java.util.ArrayList"
%>

<%
    List<Link> links = new ArrayList<>();

    links.add(new Link("Moderator Panel", "moderator_panel"));
    request.setAttribute("links", links);
%>

<html>
<head>
    <%@include file="../templates/meta.jsp" %>
    <title>User Panel</title>
    <%--    title image viewsNumber category isAvailable owner--%>
</head>
<body class="dark:bg-slate-500">
<%@include file="./user-panel/header.jsp" %>

<div class="container mx-auto pt-14">
    <div class="w-3/5 mx-auto mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
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
                <%@include file="user-panel/things_table.jsp" %>
            </div>
            <div class="hidden rounded-lg"
                 id="my-things-on-exchange"
                 role="tabpanel"
                 aria-labelledby="my-things-on-exchange-tab">
                <%@include file="user-panel/things_table.jsp" %>
            </div>
            <div class="hidden rounded-lg"
                 id="things-on-exchange"
                 role="tabpanel"
                 aria-labelledby="things-on-exchange-tab">
                <%@include file="user-panel/things_table.jsp" %>
            </div>
        </div>
    </div>
</div>

<%@include file="../templates/scripts.jsp" %>
</body>
</html>
