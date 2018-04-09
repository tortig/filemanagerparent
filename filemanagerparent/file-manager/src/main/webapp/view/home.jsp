<%--
  Created by IntelliJ IDEA.
  User: Tigran
  Date: 2/13/2018
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/downloadZip">

    <%
        @SuppressWarnings("unchecked")
        List<String> userFilesNames = (List<String>) session.getAttribute("userFilesNames");
        for (String fileName : userFilesNames) {
    %>

        <p><label>
            <input type="checkbox" name="fileName" value="<%=fileName%>"/>
        </label><%=fileName%></p>

        <a href="${pageContext.request.contextPath}/delete?fileName=<%=fileName%>">Delete</a>&emsp;

        <a href="${pageContext.request.contextPath}/download?fileName=<%=fileName%>">Download</a><hr>

    <%
    }
    %>

        <br><br>
        <input type="submit" value="Zip"><br>
</form>

<!--Add button-->
<form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
    <input type="file" name="file">&emsp;
    <input type="submit" name="submit" value="Add">
</form>

<!--Logout button-->
<form method="post" action="${pageContext.request.contextPath}/home">
    <input type="submit" value="Logout">
</form>

</body>
</html>
