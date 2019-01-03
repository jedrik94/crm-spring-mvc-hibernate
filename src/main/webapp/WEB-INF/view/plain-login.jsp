<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Login Form</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Login Page - CRM (Customers Relationship Manager)</h2>
    </div>
</div>

<div id="content">
    <h3>Login Form</h3>
    <form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
        <c:if test="${param.error != null}">
            <i class="failed">Username or password is incorrect. Please try again!</i>
        </c:if>
        <table>
            <tbody>
            <tr>
                <td><label>Username: </label></td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td><label>Password: </label></td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Login"></td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>

</body>
</html>
