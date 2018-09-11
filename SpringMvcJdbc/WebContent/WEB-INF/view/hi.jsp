<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome JSP!!!</h1>
	<h2>${msg}</h2>
	<form action="welcome" method="post">
		Name : <input type="text" name="name" /><br>
		Mobile : <input type="text" name="mobile" /><br>
		Email : <input type="text" name="name" /><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>