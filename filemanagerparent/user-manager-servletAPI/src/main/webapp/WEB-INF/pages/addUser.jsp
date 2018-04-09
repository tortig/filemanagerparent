<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Add User</title>

</head>
<body>
<h1>Add User</h1>

<!--Add button-->
<form action="${pageContext.request.contextPath}/create" method="post" enctype="multipart/form-data">

    <label><input type="text" name="name"></label>Name<br><br>

    <label><input type="text" name="email"></label>Email<br><br>

    <label><input type="text" name="username"></label>Username<br><br>

    <label><input type="password" name="password"></label>Password<br><br>

    <input type="submit" value="Create"><br><br>
</form>

</body>
</html>