<%-- 
    Document   : download
    Created on : 27-mrt-2014, 14:04:43
    Author     : Jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>MyVibe Music Store</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="css/home.css" rel="stylesheet" media="screen">
    </head>
    <body>
        <c:if test="${empty(sessionScope.currentSessionUser)}">
            <c:redirect url="/index.jsp"/>
        </c:if>
        <div class="container">
            <div class="row">
                <div class="span8">
                    <h1>MyVibe</h1>
                </div>
                <div class="span4">
                    <h4>Welkom ${currentSessionUser.firstName}</h4>
                    <a href="LogOut" target="_parent"><button class="btn btn-danger">Log uit</button></a><a href="profile.jsp" target="_parent"><button class="btn">Profiel</button></a>
                </div>
            </div>

            <div class="row" id="menu">
                <nav role="navigation" class="navbar navbar-default">
                    <ul class="nav navbar-nav">
                        <li><a href="home.jsp">Home</a></li>
                        <li><a href="playlist.html">Mijn afspeellijst</a></li>
                        <li class="active"><a href="#">Koop een nummer</a></li>
                        <li><a href="upload.jsp">Upload een nummer</a></li>
                    </ul>
                </nav>
                <div class="input-append">
                    <input class="span2" id="appendedInputButtons" type="text">
                    <button class="btn" type="button">Search</button>
                </div>
            </div>

            <table class="table table-condensed">
                <tr>
                    <th>Track Name</th>
                    <th>Artist</th>
                    <th>Release date</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${tracks}" var="track">
                    <form action="downloadServlet" method="post">
                        <tr>
                            <td>${track.trackname}</td>
                            <td>${track.trackreleasedate}</td>
                            <td>${track.trackprice}</td>
                            <td>${track.artist_artistid}</td>
                            <td><input type="submit" value="Koop"></td>
                        </tr>
                    </form>
                </c:forEach>
            </table>

        </div>
    </body>
</html>
