<%@ page import="model.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Edit User</title>

</head>
<body>
<h1>Edit User</h1>

<!--Edit button-->
<form method="post" action="${pageContext.request.contextPath}/update" enctype="multipart/form-data">

    <%
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
    %>

    <label><input type="text" name="name" value="<%=userDTO.getName()%>"></label>Name<br><br>

    <label><input type="text" name="email" value="<%=userDTO.getEmail()%>"></label>Email<br><br>

    <label><input type="text" name="username" value="<%=userDTO.getUsername()%>"></label>Username<br><br>

    <label><input type="password" name="password" value="<%=userDTO.getPassword()%>"></label>Password<br><br>

    <input type="submit" value="Edit"><br><br>
</form>

</body>
</html>