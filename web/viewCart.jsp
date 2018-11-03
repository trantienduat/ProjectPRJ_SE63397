<%@page import="shoe.ShoeDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>View Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <div class="container">
            <c:if test="${empty sessionScope.MEMBER}">
                <c:redirect url="login.jsp"/>
            </c:if>
            <c:set var="searchValue" value="${param.searchValue}"/>
            <c:set var="cart" value="${sessionScope.CART}"/>
            <h1>Your Cart Details(<a href="logOut">LogOut</a>)</h1>
            <c:url var="searchShoeLink" value="searchShoe">
                <c:param name="searchValue" value="${searchValue}"/>
            </c:url>
            <a href="${searchShoeLink}">Back to shopping Page</a>
            <c:if test="${not empty cart and not empty cart.items}">
                <form action="removeFromCart">
                    <table border="1">
                        <thead>
                        <th>No.</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Sizes</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Action</th>
                        </thead>
                        <tbody>
                            <c:set var="TOTAL" value="${0}"/> <!--Total of the cart-->
                            <c:set var="count" value="${0}"/><!--counter variable-->
                            <c:set var="items" value="${cart.items}"/><!--get items the cart-->
                            <c:forEach var="item" items="${items}">
                                <jsp:useBean id="shoeDAO" class="shoe.ShoeDAO"/><!--call ShoeDAO bean-->
                                <c:forEach var="sizeAndQuantity" items="${item.value}" varStatus="counter">
                                    <c:set var="description" value="${shoeDAO.getDescriptionByID(item.key)}"/><!--get description via shoesID-->
                                    <c:set var="price" value="${shoeDAO.getPriceByShoeIDAndSizeID(item.key, sizeAndQuantity.key)}"/><!--get price via shoesID and size number-->
                                    <tr>
                                        <td><c:set var="count" value="${count+1}"/>${count}</td>
                                        <td>${description}</td>
                                        <td>${sizeAndQuantity.value}</td>
                                        <td>${sizeAndQuantity.key}</td>
                                        <td>${price}$</td>
                                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${sizeAndQuantity.value * price}"/>$</td>
                                        <td> 
                                            <input type="checkbox" name="chkboxRemove" value="${item.key}-${sizeAndQuantity.key}" /><!--value of chkbox: shoesID-size --> 
                                        </td>
                                    </tr>
                                    <c:set var="TOTAL" value="${price*sizeAndQuantity.value + TOTAL}"/> <!--Total = price * quantity in cart-->
                                </c:forEach><!--end of item.value(sizeAndQuantity) -->
                            </c:forEach><!--end of items-->
                            <tr>
                                <td colspan="5">TOTAL</td>
                                <td>
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${TOTAL}"/>$
                                </td>

                                <td>
                                    <input type="hidden" value="${searchValue}" name="searchValue" />
                                    <input type="submit" value="Remove" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <c:if test="${not empty requestScope.NOTIFICATION}">
                    <div style="color: red; font-style: italic">
                        ${requestScope.NOTIFICATION}
                    </div>
                </c:if>
            </c:if>
            <c:if test="${empty cart.items}">
                <h2>
                    Your Cart is empty!
                </h2>
            </c:if>
            <c:if test="${not empty cart.items}">
                <br/>
                <a href="checkOut?btAction=checkOut">Check Out</a>
            </c:if>

        </div>
    </body>
</html>
