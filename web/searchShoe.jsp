<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="shoe.ShoeDTO"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Search</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <c:if test="${empty sessionScope.MEMBER}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <div>
            <h3>Search Page</h3>
            <h1 style="color: blue; font-style: italic">
                Welcome ${sessionScope.MEMBER.lastName} 
                (<a href="logOut">LogOut</a>)
            </h1>
            <c:set var="searchValue" value="${param.searchValue}"/>
            <form action="searchShoe" method="get">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Description</td>
                            <td colspan="2"><input type="text" name="searchValue" value="<c:if test="${not empty searchValue}">${searchValue}</c:if>"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input class="button-gray" type="reset" value="Reset"/></td>
                                <td><input type="submit" value="Search"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <!--Search result-->
        <c:set var="list" value="${requestScope.SEARCHRESULT}"/>
        <c:if test="${not empty list}">
            <div>
                <table border="1">
                    <thead>
                    <th>No.</th>
                    <th>Description</th>
                    <!--<th>Price</th>-->
                    <th>Sizes - Price</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <c:forEach var="shoeDTO" items="${list}" varStatus="counter">
                        <form action="addToCart">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${shoeDTO.description}</td>
                                <c:set var="sizeAndPrice" value="${shoeDTO.sizeAndPrice}"/>
                                <!--<td></td>-->
                                <td> <select name="selectedSize" >
                                        <c:forEach var="entry" items="${sizeAndPrice}">
                                            <option value="${entry.key}">${entry.key} - ${entry.value}$</option>
                                        </c:forEach>
                                    </select> </td>
                                <td>   
                                    <input type="hidden" value="${searchValue}" name="searchValue" />
                                    <input type="hidden" value="${shoeDTO.shoesID}" name="shoesID" />
                                    <input type="submit" value="Add To Cart" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </c:if>
        <c:if test="${not empty searchValue and empty list}">
            <h1>No Record is matched!!</h1>
        </c:if>

        <!--View Cart-->
        <br/>
        <c:url var="viewCartLink" value="viewCart.jsp">
            <%--<c:if test="${not empty searchValue}">--%>
            <c:param name="searchValue" value="${searchValue}"/>
            <%--</c:if>--%>
        </c:url>
        <a href="${viewCartLink}">View Cart</a>
    </body>
</html>
