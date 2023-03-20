<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/03/2023
  Time: 10:06 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><a href="/EmployeeServlet">Back to Employees list</a></p>

<form method="post">
    <fieldset>
        <legend>Delete</legend>
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" value="${employee.id}" readonly></td>
            </tr>            <tr>
                <td>Name</td>
                <td><input type="text" value="${employee.name}" readonly></td>
            </tr>            <tr>
                <td>Email</td>
                <td><input type="text" value="${employee.email}" readonly></td>
            </tr>            <tr>
                <td>Address</td>
                <td><input type="text" value="${employee.address}" readonly></td>
            </tr>            <tr>
                <td>Phone</td>
                <td><input type="text" value="${employee.phone}" readonly></td>
            </tr>            <tr>
                <td>Salary</td>
                <td><input type="text" value="${employee.salary}" readonly></td>
            </tr>            <tr>
                <td>Department</td>
                <td><input type="text" value="${employee.department}" readonly></td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete"></td>
            </tr>
        </table>

    </fieldset>
</form>
</body>
</html>
