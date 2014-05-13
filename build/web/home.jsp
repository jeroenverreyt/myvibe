<%-- 
    Document   : home
    Created on : 21-mrt-2014, 17:13:04
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
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
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
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="OverzichtTracksperuserServlet">Mijn afspeellijst</a></li>
                        <li><a href="OverzichtTracks">Koop een nummer</a></li>
                        <li><a href="upload.jsp">Upload een nummer</a></li>
                    </ul>
                </nav>
            </div>

            <div class="row" id="content">
                <div class="span4">
                    <h5><i class="icon-music"></i> Mijn aangekochte nummers</h5>
                    <ul>
                       <c:forEach  var="track" items="${userTracks}">
                            <li> ${track.artist} - ${track.name} </li> 
                         </c:forEach>
                    </ul>
                    <a href="#">Bekijk mijn afspeellijst</a>
                </div>
                <div class="span4">
                    <h5><i class="icon-heart"></i> Meest aangekochte nummers</h5>
                    <ul>
                         <c:forEach  var="track" items="${topUpload}">
                            <li> ${track.artist} - ${track.name} </li> 
                         </c:forEach>
             
                    </ul>
                        
                    <a href="#">Ga naar de muziekstore</a>
                </div>
                <div class="span4">
                    <h5><i class="icon-star"></i> Recent upgeloade nummers</h5>
                    <ul>
                        <c:forEach  var="track" items="${topRecent}">
                            <li> ${track.artist} - ${track.name} </li> 
                         </c:forEach>
                    </ul>
                    <a href="#">Ga naar de muziekstore</a>
                </div>
            </div>
        </div>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
