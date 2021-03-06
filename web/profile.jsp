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
        <link href="css/bootstrap.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
        <link href="css/home.css" rel="stylesheet" media="screen">
    </head>
    <body>
    <c:if test="${empty(sessionScope.currentSessionUser) && empty(sessionScope.currentSessionArtist) }">
        <c:redirect url="/index.jsp"/>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="span8">
                <h1>MyVibe</h1>
            </div>
            <div class="span4">
                <h4>Welkom ${currentSessionUser.firstName}${currentSessionArtist.firstName}</h4>
                <a href="LogOut" target="_parent"><button class="btn btn-danger">Log uit</button></a><a href="profile.jsp" target="_parent"><button class="btn">Profiel</button></a>
            </div>
        </div>

        <div class="row" id="menu">
            <nav role="navigation" class="navbar navbar-default">
                <ul class="nav navbar-nav">
                    <c:if test="${!empty(sessionScope.currentSessionUser)}">
                    <li><a href="HomeServlet">Home</a></li>
                    <li><a href="OverzichtTracksperuserServlet">Mijn afspeellijst</a></li>
                    <li><a href="OverzichtTracks">Koop een nummer</a></li>
                    </c:if>
                     <c:if test="${!empty(sessionScope.currentSessionArtist)}">
                        <li><a href="upload.jsp">Upload een nummer</a></li>
                        </c:if>
                </ul>
            </nav>
        </div>
 <p class="alert alert-success" ${feedback.wijziging == null ? "style='display:none;'" : ""}>${feedback.wijziging}</p> 
        <form action="ChangeProfile" method="post" class="form-horizontal"> 
            
            <h6> Username: </h6>
            <p> ${currentSessionUser.login}${currentSessionArtist.login} </p>
           
            <h6> Naam: </h6>
            <p> ${currentSessionUser.name}${currentSessionArtist.name} </p>
            
            <h6> Voornaam: </h6>
            <p> ${currentSessionUser.firstName}${currentSessionArtist.firstName} </p>
            
             <h6> Geboortedatum: </h6>
            <p> ${currentSessionUser.birthDate}${currentSessionArtist.birthDate} </p>

            <h6> Email: </h6>
            <p> ${currentSessionUser.email}${currentSessionArtist.email} </p>
            
            <h6> Telefoon: </h6>
            <input type="text" id="newPhone" name="newPhone" value="${currentSessionUser.phone}${currentSessionArtist.phone}"> </input>

            <h6> Nieuw paswoord: </h6>
            <input type="password" id ="newPassword" name="newPassword"> </input>
             <c:if test="${!empty(sessionScope.currentSessionUser)}">
            <h6> Credits </h6>
            <p> ${currentSessionUser.credits} </p>  
           
            <button type="submit" class="btn btn-primary" name="buycredits">Koop extra credits</button>
             </c:if>
            <button type="submit" class="btn btn-primary" name ="changeprofile">Bevestig wijzigingen</button>
        </form>
        <form action="deleteaccount.jsp" method="post" class="form-horizontal"> 
            <button type="submit" class="btn btn-danger">Verwijder account</button>
        </form>

    </div>
</body>
</html>
