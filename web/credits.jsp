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
                        <li><a href="HomeServlet">Home</a></li>
                        <li><a href="OverzichtTracksperuserServlet">Mijn afspeellijst</a></li>
                        <li><a href="OverzichtTracks">Koop een nummer</a></li>
                         <c:if test="${!empty(sessionScope.currentSessionArtist)}">
                        <li><a href="upload.jsp">Upload een nummer</a></li>
                        </c:if>
                    </ul>
                </nav>
            
            </div>
            <p class="alert alert-success" ${mess.credits == null ? "style='display:none;'" : ""}>${mess.credits}</p> 
            <form action="CreditServlet" method="post" class="form-horizontal"> 
            
                
            <h6> Aantal credits: </h6>
            <p> ${currentSessionUser.credits} </p>  
            
             <h6> Koop credits </h6>
            <input type="number" min="1" id="buyCredits" name="buyCredits" required> </input>
            
            <button type="submit" class="btn btn-primary">Koop</button>
        </form>
            <a href="profile.jsp">Terug naar profiel</a>
 <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>