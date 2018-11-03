<%-- 
    Document   : orderStatus
    Created on : Oct 30, 2018, 3:15:41 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty requestScope.STATUS}">
            <c:redirect url="viewCart.jsp"/>
        </c:if>
        <h1>Status Page(<a href="logOut">LogOut</a>)</h1>
        <font color="red">
        <h1>${requestScope.STATUS}</h1></br>
        </font>
        <a href="searchShoe.jsp">Back to Search Shoes</a>

    </body>
</html>
