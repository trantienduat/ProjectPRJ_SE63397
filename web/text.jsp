<%-- 
    Document   : text
    Created on : Nov 1, 2018, 11:03:34 PM
    Author     : trant
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
        <c:forEach var="a" items="${result}">
            ${a[1]}
        </c:forEach>
    </body>
</html>
