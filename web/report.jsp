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
        <h1>Overzicht Velden</h1>
        <p class="alert alert-danger" ${feedback.error == null ? "style='display:none;'" : "style='display:inline-block'"}>${feedback.error}</p> 
        <p class="alert alert-success" ${feedback.success == null ? "style='display:none;'" : "style='display:inline-block'"}>${feedback.success}</p> 
        <form action="ReportServlet" method="post" class="form-horizontal"> 
               
           
        <select name="selectFields" size="30" multiple="multiple" tabindex="1">
           
            <c:forEach items="${fields}" var="item">

                <option value="${item}"> ${item}</option>

            </c:forEach>
                
        </select>
             <button type="submit" id="excel" name = "excel" class="btn btn-danger">Excel</button>
              <button type="submit" id="pdf" name="pdf" class="btn btn-danger">Pdf</button>
             
 </form>
    </body>
</html>
