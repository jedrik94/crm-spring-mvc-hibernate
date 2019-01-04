<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Unauthorized action - access denied</h2>
    </div>
</div>

<hr>

<p>
    <form:form action="${pageContext.request.contextPath}/customer/list" method="get">
        <input type="submit" value="Go Home!" class="add-button"/>
    </form:form>
</p>
</body>
</html>
