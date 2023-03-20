<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/03/2023
  Time: 09:40 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="/EmployeeServlet">Back Employees list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Update</legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" value="${employee.name}" name="name"></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" value="${employee.email}" name="email"></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" value="${employee.address}" name="address"></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><input type="text" value="${employee.phone}" name="phone"></td>
            </tr>
            <tr>
                <td>Salary</td>
                <td><input type="text" value="${employee.salary}" name="salary"></td>
            </tr>
            <tr>
                <td>Department</td>
                <td><input type="text" value="${employee.department}" name="department"></td>
            </tr>
            <tr>
                <td><input type="submit" value="update"></td>
            </tr>


        </table>
    </fieldset>

</form>
</body>
</html>
