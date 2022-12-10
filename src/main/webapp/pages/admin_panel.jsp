<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         import="ru.rsreu.exchangeofthings.data.Link,java.util.List,java.util.ArrayList"
%>

<%
    List<Link> links = new ArrayList<>();

    links.add(new Link("Admin Panel", "admin_panel"));
    request.setAttribute("links", links);
%>

<html>
<head>
    <%@include file="../templates/meta.jsp" %>
    <title>Admin Panel</title>
</head>
<body class="dark:bg-slate-500">
<%@include file="../templates/header.jsp" %>
<div class="container mx-auto pt-14">
    <div class="w-3/5 mx-auto mt-8 overflow-x-auto relative drop-shadow-lg sm:rounded-lg">
        <div class="flex items-center space-x-2 p-4 bg-white dark:bg-gray-700 dark:text-gray-400">
            <button id="add-user" type="button"
                    class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700"
                    data-modal-toggle="add-user-modal"
            >Add user</button>
            <button id="delete-users" type="button"
                    class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 font-medium rounded-lg text-sm px-2.5 py-1.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">
                Delete users
            </button>
        </div>
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                <tr>
                    <th scope="col" class="p-4"></th>
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
                    <th scope="col" class="py-3 px-6">
                        Action
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-row bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                    <td class="p-4 w-4">
                        <div class="flex items-center">
                            <input type="checkbox"
                                   value=""
                                   class="delete-user-checkbox w-4 h-4 text-purple-600 bg-gray-100 rounded border-gray-300 focus:ring-purple-500"
                            >
                        </div>
                    </td>
                    <td class="id hidden">
                        123
                    </td>
                    <td class="name py-4 px-6">
                        Misha
                    </td>
                    <td class="username py-4 px-6">
                        Qusavin
                    </td>
                    <td class="password py-4 px-6">
                        12345
                    </td>
                    <td class="py-4 px-6">
                        <div class="flex items-center">
                            <div class="h-2.5 w-2.5 rounded-full bg-green-400 mr-2"></div>
                            Online
                        </div>
                    </td>
                    <td class="role py-4 px-6">
                        User
                    </td>
                    <td class="isBlocked hidden">
                        false
                    </td>
                    <td class="py-4 px-6">
                        <button type="button"
                                class="edit-btn font-medium text-blue-600 hover:underline dark:text-blue-500"
                                data-modal-toggle="edit-user-modal"
                        >Edit user</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<div id="edit-user-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-4 w-full md:inset-0 h-modal md:h-full">
    <div class="relative w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <form id="edit-user-form" action="admin_panel" class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex justify-between items-start p-4 rounded-t border-b dark:border-gray-600">
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                    Edit user
                </h3>
                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="edit-user-modal">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                </button>
            </div>
            <!-- Modal body -->
            <div class="p-6 space-y-6">
                <div>
                    <label for="edit-name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                    <input type="text" name="name" id="edit-name" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <div>
                    <label for="edit-username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Username</label>
                    <input type="text" name="username" id="edit-username" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <div>
                    <label for="edit-password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                    <input type="text" name="password" id="edit-password" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <input class="hidden" type="text" id="edit-id" name="id">
                <input class="hidden" type="text" id="edit-is-blocked" name="is_blocked">
            </div>
            <!-- Modal footer -->
            <div class="flex items-center py-3 px-6 space-x-2 rounded-b border-t border-gray-200 dark:border-gray-600">
                <button type="submit" class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900">Edit</button>
            </div>
        </form>
    </div>
</div>

<div id="add-user-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center p-4 w-full md:inset-0 h-modal md:h-full">
    <div class="relative w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <form id="add-user-form" action="admin_panel" class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex justify-between items-start p-4 rounded-t border-b dark:border-gray-600">
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                    Add user
                </h3>
                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="add-user-modal">
                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                </button>
            </div>
            <!-- Modal body -->
            <div class="p-6 space-y-6">
                <div>
                    <label for="add-name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                    <input type="text" name="name" id="add-name" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <div>
                    <label for="add-username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Username</label>
                    <input type="text" name="username" id="add-username" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <div>
                    <label for="add-password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
                    <input type="text" name="password" id="add-password" class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-600 focus:border-blue-600 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                </div>

                <div id="add-user-role" class="flex">
                    <div class="flex items-center mr-4">
                        <input checked type="radio" value="user" name="role"
                               class="w-4 h-4 text-purple-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900 dark:text-white">User</label>
                    </div>
                    <div class="flex items-center mr-4">
                        <input type="radio" value="moderator" name="role"
                               class="w-4 h-4 text-purple-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900 dark:text-white">Moderator</label>
                    </div>
                    <div class="flex items-center mr-4">
                        <input type="radio" value="admin" name="role"
                               class="w-4 h-4 text-purple-600 bg-gray-100 border-gray-300">
                        <label class="ml-2 text-sm font-medium text-gray-900 dark:text-white">Administrator</label>
                    </div>
                </div>

                <input class="hidden" type="text" id="add-id" name="id">
                <input class="hidden" type="text" id="add-is-blocked" name="is_blocked">
            </div>
            <!-- Modal footer -->
            <div class="flex items-center py-3 px-6 space-x-2 rounded-b border-t border-gray-200 dark:border-gray-600">
                <button type="submit" class="focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900">Add</button>
            </div>
        </form>
    </div>
</div>

<%@include file="../templates/scripts.jsp" %>
</body>
</html>
