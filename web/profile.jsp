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
                    <li><a href="download.html">Koop een nummer</a></li>
                    <li><a href="upload.html">Upload een nummer</a></li>
                </ul>
            </nav>
            <div class="input-append">
                <input class="span2" id="appendedInputButtons" type="text">
                <button class="btn" type="button">Search</button>
            </div>
        </div>

        <div class="row"> 
            <form class="form-horizontal" method="post">

                <input type="text" id="inputVoornaam" name ="inputFirstName"class="input-small" placeholder="Voornaam" required>

                <input type="text" id="inputNaam" name="inputName" class="input-small" placeholder="Naam" required>

                <input type="email" id="inputEmail" name="inputEmail" class="input-xlarge" placeholder="Jouw e-mailadres" required >

                <input type="email" id="inputConfEmail" name="inputConfEmail" class="input-xlarge" placeholder="Bevestig e-mailadres" required>

                <input type="text" id="inputUsername" name="inputUsername" class="input-xlarge" placeholder="Kies een gebruikersnaam" required>

                <input type="password" id="inputPassword" name="inputPassword" class="input-xlarge" placeholder="Wachtwoord" required>


                <div class="input-xlarge">
                    <label>Geboortedatum</label>
                    <select name="day" id="formday" required>
                        <option value="" selected >Dag</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>

                    <select name="month" id="formmonth" required>
                        <option value="" selected>Maand</option>
                        <option value="1">Januari</option>
                        <option value="2">Februari</option>
                        <option value="3">Maart</option>
                        <option value="4">April</option>
                        <option value="5">Mei</option>
                        <option value="6">Juni</option>
                        <option value="7">Juli</option>
                        <option value="8">Augustus</option>
                        <option value="9">September</option>
                        <option value="10">Oktober</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>

                    <select name="year" id="formyear" required>
                        <option value="" selected>Jaar</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                    </select>
                </div>

                <input type="text" id="inputPhonenumber" name="inputPhonenumber" class="input-xlarge" placeholder="Telefoonnummer" required>

                <div>
                    <button class="btn btn-primary">Registreer</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
