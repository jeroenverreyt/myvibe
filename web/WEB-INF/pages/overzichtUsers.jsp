<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overzicht Users</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/inlog.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <h1>Overzicht Users</h1>

        <table class="table table-condensed">
            <tr>
                <th>First Name</th>
                <th>Name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Birth Date</th>
                <th>Phone</th>
                <th>Credits</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.name}</td>
                    <td>${user.login}</td>
                    <td>${user.pass}</td>
                    <td>${user.email}</td>  
                    <td>${user.birthDate}</td>
                    <td>${user.phone}</td>
                    <td>${user.credits}</td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>
