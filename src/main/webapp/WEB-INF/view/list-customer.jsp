<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

<hr>

<p>
    User: <security:authentication property="principal.username"/>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>

<hr>

<input type="button" value="Add Customer"
       onclick="window.location.href='form'; return false;"
       class="add-button">

<form:form action="search" method="post">
    Search customer: <input type="text" required="required" name="searchName"/>
    <input type="submit" value="Search" class="add-button"/>
</form:form>

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tmpCustomer" items="${customers}">

                <c:url var="updateLink" value="/customer/formUpdate">
                    <c:param name="customerId" value="${tmpCustomer.id}"/>
                </c:url>

                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tmpCustomer.id}"/>
                </c:url>

                <tr>
                    <td>${tmpCustomer.firstName}</td>
                    <td>${tmpCustomer.lastName}</td>
                    <td>${tmpCustomer.email}</td>
                    <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}"
                                                                onclick="if(!(confirm('Are you sure to delete this record?'))) return false;">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<br><br>
<div>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout" class="add-button"/>
    </form:form>
    <div class="divider"></div>
    <form:form action="${pageContext.request.contextPath}/managerPage" method="get">
        <input type="submit" value="Manager's Page" class="add-button"/>
    </form:form>
    <div class="divider"></div>
    <form:form action="${pageContext.request.contextPath}/adminPage" method="get">
        <input type="submit" value="Admin's Page" class="add-button"/>
    </form:form>
</div>

</body>
</html>
