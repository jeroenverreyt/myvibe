<%-- 
    Document   : profile
    Created on : 27-mrt-2014, 11:37:37
    Author     : Jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                    <li><a href="OverzichtTracks">Koop een nummer</a></li>
                    <li><a href="upload.jsp">Upload een nummer</a></li>
                </ul>
            </nav>
            <div class="input-append">
                <input class="span2" id="appendedInputButtons" type="text">
                <button class="btn" type="button">Search</button>
            </div>
        </div>

        <form action="ChangeProfile" method="post" class="form-horizontal"> 
            <h6> Username: </h6>
            <p> ${currentSessionUser.login} </p>

            <h6> Password: </h6>
            <input type="text" id ="newPassword" name="newPassword"> </input>

            <h6> Naam: </h6>
            <p> ${currentSessionUser.name} </p>

            <h6> Voornaam: </h6>
            <p> ${currentSessionUser.firstName} </p>

            <h6> Geboortedatum: </h6>
            <p> ${currentSessionUser.birthDate} </p>

            <h6> Email: </h6>
            <p> ${currentSessionUser.email} </p>

            <h6> Phone: </h6>
            <input type="text" id="newPhone" name="newPhone"> </input>

            <h6> Credits </h6>
            <p> ${currentSessionUser.credits} </p>  
            
            <button type="submit" class="btn btn-primary">Bevestig wijzigingen</button>
        </form>
    </div>
</body>
</html>