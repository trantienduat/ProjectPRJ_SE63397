<%@page import="customer.CustomerDAO"%>
<%@page import="customer.CustomerDTO"%>
<%@page import="cart.CartObj"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Check Out</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .td-title{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${empty cart.items}">
                <c:redirect url="viewCart.jsp"/>
            </c:if>
            <h1>Finishing your order(<a href="logOut">LogOut</a>)</h1>

            <%--<c:set var="custID" value="${cart.custID}"/>--%>
            <%--<jsp:useBean id="custDAO" class="customer.CustomerDAO"/>--%>
            <c:set var="custDTO" value="${sessionScope.MEMBER}"/>
            <jsp:useBean id="currentDate" class="java.util.Date"/>

            <!----------------------------Order table---------------------------->
            <table border="1">
                <thead>
                <th>No.</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
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
                                <td>${item.key}</td>
                                <td>${sizeAndQuantity.value}</td>
                                <td>${price}$</td>
                                <td>${sizeAndQuantity.value * price}$</td>
                            </tr>
                            <c:set var="TOTAL" value="${price*sizeAndQuantity.value + TOTAL}"/> <!--Total = price * quantity in cart-->
                        </c:forEach><!--end of item.value(sizeAndQuantity) -->
                    </c:forEach><!--end of items-->
                    <tr>
                        <td colspan="4">TOTAL</td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${TOTAL}"/>$
                        </td>

                    </tr>
                </tbody>
            </table>
            <!--End of order table-->





            <!----------------------------Customer Information form----------------->
            <h2>Customer Information</h2>
            <jsp:useBean id="orderDAO" class="order.OrderDAO" />
            <c:set var="errors" value="${requestScope.ERRORS}"/>
            <form action="checkOut">
                <table>
                    <tr>
                        <td>OrderId : </td>
                        <td>${orderDAO.generateOrderID()}</td>
                        <td>Date : </td>
                        <td>
                            <fmt:formatDate value="${currentDate}" pattern="YYYY-MM-dd" /> <fmt:formatDate value="${currentDate}" pattern="HH:mm" /> 
                            <input type="hidden" value="<fmt:formatDate value="${currentDate}" pattern="YYYY-MM-dd" />" name="currentDate" />
                        </td>
                    </tr>
                    <tr>
                        <td>Customer : </td>
                        <td>${custDTO.firstName} ${custDTO.middleName} ${custDTO.lastName}</td>
                        <td>Phone : </td>
                        <td>${custDTO.phone}</td>
                    </tr>
                    <tr>
                        <td>Address*</td>
                        <td colspan="3"> <input type="text" name="deliveryAddress" 
                                                value="<c:if test="${not empty param.deliveryAddress}">${param.deliveryAddress}</c:if>" 
                                                    placeholder="address for delivery..."/> </td>
                        </tr>
                    <c:if test="${not empty errors.addressDeliveryLengthError}">
                        <tr>
                            <td colspan="4" style="color: red">
                                ${errors.addressDeliveryLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Receiver*</td>
                        <td> <input type="text" name="receiver" 
                                    value="<c:if test="${not empty param.receiver}">${param.receiver}</c:if>"/> </td>
                            <td>Receiver's Phone*</td>
                            <td> <input type="text" name="receiverPhone" 
                                        value="<c:if test="${not empty param.receiverPhone}">${param.receiverPhone}</c:if>"/> </td>
                        </tr>
                    <c:if test="${not empty errors.receiverLengthError
                                  or not empty errors.receiverPhoneNumberFormatError}">
                          <tr>
                              <td colspan="2" style="color: red">
                                  ${errors.receiverLengthError}
                              </td>
                              <td colspan="2" style="color: red">
                                  ${errors.receiverPhoneNumberFormatError}
                              </td>
                          </tr>
                    </c:if>
                    <tr>
                        <td></td>
                        <td> 
                            <input type="submit" value="Back" name="btAction"/> 
                        </td>
                        <td> 
                            <input type="hidden" value="<fmt:formatNumber type="number"  groupingUsed="false" maxFractionDigits="2" value="${TOTAL}"/>" name="TOTAL" />
                            <input type="submit" value="Confirm" name="btAction"/> 
                        </td>
                        <td> 
                            <input type="hidden" value="" name="btAction" id="isExit"/>
                            <input type="submit" onclick="myFunction()" value="Exit" /> 
                        </td>
                    </tr>

                </table>
            </form>
            <!--End Customer Information form-->
        </div>

        <!----------------------------JS--------------------------->
        <script>
            function myFunction() {
                var isExit;
                var evt = confirm("All your cart will be removed. Are you sure?");
                if (evt == true) {
                    document.getElementById("isExit").value = "DestroyCart";
                } else {
                    document.getElementById("isExit").value = "Cancel";
                }
            }
        </script>
    </body>
</html>
