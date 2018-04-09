<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users Page</title>
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h1>User Manager</h1>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <input type="button" value="Add User"
                   onclick="window.location.href='addForm'; return false;"/><br><br>

            <table class="tg" style="border: 1px solid; width: 500px; text-align:center">
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">Email</th>
                    <th width="120">Username</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>

                <c:forEach var="user" items="${users}">

                    <!--Update-->
                    <c:url var="update" value="/user/editUser">
                        <c:param name="userId" value="${user.id}" />
                    </c:url>

                    <!--Delete-->
                    <c:url var="delete" value="/user/deleteUser">
                        <c:param name="userId" value="${user.id}" />
                    </c:url>

                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.username}</td>
                    <td><a href="${update}">Edit</a>&emsp;</td>
                    <td><a href="${delete}">Delete</a>&emsp;</td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
