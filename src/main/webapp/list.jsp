<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/03/2023
  Time: 08:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>
    <a href="/EmployeeServlet?action=create">Create new Employee</a>
</p>

<form action="/EmployeeServlet?action=search">
    <input type="text">
    <input type="submit" value="Search">
<table border="1">
    <tr>
        <td >Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Phone</td>
        <td>Salary</td>
        <td>Department</td>
    </tr>
    <c:forEach var="e" items="${employee}">
    <tr>
        <td>${e.getName()}</td>
        <td>${e.getEmail()}</td>
        <td>${e.getAddress()}</td>
        <td>${e.getPhone()}</td>
        <td>${e.getSalary()}</td>
        <td>${e.getDepartment()}</td>
        <td><a href="/EmployeeServlet?action=edit&id=${e.id}">Update</a></td>
        <td><a href="/EmployeeServlet?action=delete&id=${e.id}">Delete</a> </td>
    </tr>
        </c:forEach>
</table>
</form>
</body>
</html>
