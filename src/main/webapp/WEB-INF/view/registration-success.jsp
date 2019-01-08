<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <title>Registration Success</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div>

    <div id="loginbox" style="margin-top: 50px;"
         class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="form-group">
            <div class="col-xs-15">
                <div>

                    <!-- Check for registration error -->
                    <c:if test="${registrationSuccess != null}">

                        <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                ${registrationSuccess}
                        </div>

                    </c:if>

                    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary" role="button"
                       aria-pressed="true">Home Page</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
