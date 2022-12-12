<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ru.rsreu.exchangeofthings.enums.UserPanelExchangeMode" %>
<html>
<head>
    <%@include file="/templates/meta.jsp" %>
    <title>Exchange</title>
</head>
<body>
<%@include file="./header.jsp" %>

<div id="exchange" class="container mx-auto pt-14">
    <div class="max-w-3/5 w-full mx-auto grid grid-cols-5 gap-6 justify-center">
        <div class="col-span-2">
            <c:if test="${mode == UserPanelExchangeMode.MAKE_REQUEST}">
                <div class="max-w-sm ml-auto">
                    <label for="select-items" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Select
                        an
                        option</label>
                    <select id="select-items"
                            name="items"
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <c:forEach items="${items}" var="item">
                            <option
                                ${senItem.getId() == item.getId() ? "selected" : ""}
                                    value="${item.getId()}"
                            >${item.getTitle()}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>
        </div>
        <div class="col-span-3"></div>
        <div class="col-span-2">
            <div id="sen-item"
                 data-item-id="${senItem.getId()}"
                 class="max-w-sm ml-auto p-6 bg-white border border-gray-200 rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700">
                <img class="image rounded-t-lg max-h-52 mx-auto" src="${senItem.getImage()}" alt="Item Image"/>
                <div class="p-5 pb-0">
                    <h5 class="title mb-2 text-3xl font-bold tracking-tight text-gray-900 dark:text-white">
                        ${senItem.getTitle()}
                    </h5>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">DESCRIPTION</div>
                        <p class="description">${senItem.getDescription()}</p>
                    </div>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">VIEWS NUMBER</div>
                        <p class="views-number">${senItem.getViewsNumber()}</p>
                    </div>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">CATEGORY</div>
                        <p class="category">${senItem.getCategory()}</p>
                    </div>
                    <div class="pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">OWNER</div>
                        <p class="owner">You</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-span-1 self-center justify-self-center">
            <span class="material-symbols-outlined text-8xl text-gray-900">swap_horiz</span>
        </div>
        <div class="col-span-2">
            <div id="rec-item"
                 data-item-id="${recItem.getId()}"
                 class="max-w-sm mr-auto p-6 bg-white border border-gray-200 rounded-lg shadow-md dark:bg-gray-800 dark:border-gray-700">
                <img class="rounded-t-lg max-h-52 mx-auto" src="${recItem.getImage()}" alt="Item Image"/>
                <div class="p-5 pb-0">
                    <h5 class="mb-2 text-3xl font-bold tracking-tight text-gray-900 dark:text-white">
                        ${recItem.getTitle()}
                    </h5>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">DESCRIPTION</div>
                        <p>${recItem.getDescription()}</p>
                    </div>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">VIEWS NUMBER</div>
                        <p>${recItem.getViewsNumber()}</p>
                    </div>
                    <div class="mb-3 pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">CATEGORY</div>
                        <p>${recItem.getCategory()}</p>
                    </div>
                    <div class="pt-2 text-gray-700 border-t border-gray-200 dark:border-gray-700 dark:text-gray-400">
                        <div class="font-bold mb-1 text-gray-900">OWNER</div>
                        <p>${recItem.getOwner().getName()}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-span-2"></div>
        <div class="col-span-1 self-center justify-self-center">
            <c:if test="${mode == UserPanelExchangeMode.MAKE_REQUEST}">
                <button type="button"
                        id="make-request"
                        class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                    Make request
                </button>
            </c:if>
            <c:if test="${mode == UserPanelExchangeMode.COMPLETE_REQUEST}">
                <div class="flex items-center justify-center space-x-2">
                    <button type="button"
                            id="accept-request"
                            class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                        Accept
                    </button>
                    <button type="button"
                            id="decline-request"
                            class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                        Decline
                    </button>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div id="all-items" class="hidden">
    <c:forEach items="${items}" var="item">
        <div class="item" data-item-id="${item.getId()}">
            <div class="image">${item.getImage()}</div>
            <div class="title">${item.getTitle()}</div>
            <div class="description">${item.getDescription()}</div>
            <div class="views-number">${item.getViewsNumber()}</div>
            <div class="category">${item.getCategory()}</div>
            <div class="owner">${item.getOwner().getName()}</div>
        </div>
    </c:forEach>
</div>

<%@include file="/templates/scripts.jsp" %>
</body>
</html>
