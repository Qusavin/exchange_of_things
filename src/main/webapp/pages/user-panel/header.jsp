<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="bg-purple-500 py-2 text-white fixed top-0 left-0 right-0 dark:bg-purple-900 z-20">
    <div class="container mx-auto xl:max-w-7xl flex items-center justify-between">
        <div class="text-lg font-medium">Exchange of things</div>
        <div class="flex items-center">
            <button id="dropdown-notification-btn" data-dropdown-toggle="dropdownNotification"
                    class="${exchangeRequests.size() > 0 ? "" : "cursor-default"} relative p-1 inline-flex items-center text-sm text-center text-white hover:bg-purple-700 focus:outline-none focus:ring-1 focus:ring-purple-300 rounded-lg"
                    type="button">
                <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z"></path>
                </svg>
                <c:if test="${exchangeRequests.size() > 0}">
                    <div class="inline-flex absolute top-1 right-1 w-3 h-3 bg-red-500 rounded-full border-2 border-purple-500 dark:border-purple-900"></div>
                </c:if>
            </button>

            <a class="p-1 ml-1 text-lg text-white hover:bg-purple-700 focus:outline-none focus:ring-1 focus:ring-purple-300 rounded-lg"
               href="/exchange-of-things/logout">
                <span class="material-symbols-outlined">logout</span>
            </a>
            <!-- Dropdown menu -->

            <c:if test="${exchangeRequests.size() > 0}">
                <div id="dropdownNotification"
                     class="hidden z-20 w-full max-w-sm bg-white rounded overflow-hidden divide-y divide-gray-100 shadow dark:bg-gray-800 dark:divide-gray-700"
                     aria-labelledby="dropdown-notification-btn">
                    <div class="block py-2 px-4 font-medium text-center text-gray-700 bg-gray-50 dark:bg-gray-800 dark:text-white">
                        Notifications
                    </div>
                    <div class="divide-y divide-gray-100 dark:divide-gray-700">
                        <c:forEach items="${exchangeRequests}" var="exchangeRequest">
                            <a href="user-panel/exchange?sen_item_id=${exchangeRequest.getReceiverItem().getId()}&rec_item_id=${exchangeRequest.getSenderItem().getId()}"
                               class="flex py-3 px-4 hover:bg-gray-100 dark:hover:bg-gray-700">
                                <div class="pl-3 w-full">
                                    <div class="text-gray-500 text-sm mb-1.5 dark:text-gray-400">
                                        Request to exchange your <span
                                            class="font-semibold">${exchangeRequest.getReceiverItem().getTitle()}</span>
                                        to <span
                                            class="font-semibold">${exchangeRequest.getSenderItem().getTitle()}</span>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <button id="theme-toggle" type="button"
                    class="text-white hover:bg-purple-700 focus:outline-none focus:ring-1 focus:ring-purple-300 rounded-lg text-sm p-1">
                <svg id="theme-toggle-dark-icon" class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"></path>
                </svg>
                <svg id="theme-toggle-light-icon" class="hidden w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"
                          fill-rule="evenodd" clip-rule="evenodd"></path>
                </svg>
            </button>
        </div>
    </div>
</header>
