<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Customers</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customers Relationship Manager</h2>
    </div>
</div>

<input type="button" value="Add Customer"
       onclick="window.location.href='form'; return false;"
       class="add-button">

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>

            <c:forEach var="tmpCustomer" items="${customers}">
                <tr>
                    <td> ${tmpCustomer.firstName}</td>
                    <td> ${tmpCustomer.lastName}</td>
                    <td> ${tmpCustomer.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
