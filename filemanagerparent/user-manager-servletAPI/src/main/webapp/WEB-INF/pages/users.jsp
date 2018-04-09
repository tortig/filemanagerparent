<%@ page import="java.util.List" %>
<%@ page import="model.UserDTO" %>
<html>
<head>
    <title>Users Page</title>
</head>
<body>

<h1>Users List</h1>

<table class="tg" style="border: 1px solid; width: 500px; text-align:center">
    <tr>
        <th width="80">ID</th>
        <th width="120">Name</th>
        <th width="120">Email</th>
        <th width="120">Username</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <%
        @SuppressWarnings("unchecked")
        List<UserDTO> listUsers = (List<UserDTO>) session.getAttribute("listUsers");
        for (UserDTO user : listUsers) {
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <td><a href="${pageContext.request.contextPath}/update?userID=<%=user.getId()%>">Edit</a>&emsp;</td>
        <td><a href="${pageContext.request.contextPath}/delete?userID=<%=user.getId()%>">Delete</a>&emsp;</td>
    </tr>
    <%
        }
    %>
</
>

<a href="${pageContext.request.contextPath}/create">Add User</a><br><br>

<form action="${pageContext.request.contextPath}/get" method="get" >
    <label><input type="number" name="id"></label>ID<br>
    <input type="submit" value="Search by ID"><br><br>
</form>

<h1>Search result : <%=session.getAttribute("searchedUserName")%></h1><br>

</body>
</html>
