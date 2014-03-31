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
                        <li><a href="playlist.jsp">Mijn afspeellijst</a></li>
                        <li><a href="OverzichtTracks">Koop een nummer</a></li>
                        <li class="active"><a href="#">Upload een nummer</a></li>
                    </ul>
                </nav>
                <div class="input-append">
                    <input class="span2" id="appendedInputButtons" type="text">
                    <button class="btn" type="button">Search</button>
                </div>
            </div>

            <form method="post" action="uploadServlet" class="form-horizontal" enctype="multipart/form-data">
                <input type="text" class="input-xlarge" placeholder="Trackname" name="trackName"/>

                <input type="text" class="input-xlarge" placeholder="Price" name="trackPrice"/>

                <input type="file" class="input-xlarge" name="trackAudioFile"/>

                <input type="submit" value="Upload">
            </form>

        </div>
    </body>
</html>
