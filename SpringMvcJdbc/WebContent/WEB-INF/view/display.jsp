<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="insert">New Entry</a><br>
	<c:forEach var="item" items="${users}">
    <td>
       ${item.getId()}
    </td>
    <td>
       ${item.getName()}
    </td>
    <td>
       ${item.getMobile()}
    </td>
    <td>Edit</td>
    <td>Delete</td>
</c:forEach>
</body>
</html>