<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save User</title>
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>User Manager</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save User</h3>
	
		<form:form action="saveUser" modelAttribute="user" method="POST">

			<!-- need to associate this data with customer id -->
			<form:hidden path="id" />
					
			<table>
				<tbody>

					<tr>
						<td><label>Name    :</label></td>
						<td><form:input path="name" /></td>
					</tr>
				
					<tr>
						<td><label>Email   :</label></td>
						<td><form:input path="email" /></td>
					</tr>

					<tr>
						<td><label>Username:</label></td>
						<td><form:input path="username" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><form:input path="password" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>

		</form:form>
		
		<p>
			<a href="${pageContext.request.contextPath}/user/list">Back to List</a>
		</p>
	
	</div>

</body>

</html>










