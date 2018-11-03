<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Confirm Order</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .td-title{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}"/>
        
        
        <!--redirect if cart.items is empty-->
        <c:if test="${empty cart.items}">
            <c:redirect url="viewCart.jsp"/>
        </c:if>
        <c:set var="TOTAL" value="${param.TOTAL}"/>
        <c:set var="currentDate" value="${param.currentDate}"/>
        <jsp:useBean id="custDAO" class="customer.CustomerDAO"/>
        <c:set var="custDTO" value="${sessionScope.MEMBER}"/>
        <jsp:useBean id="orderDAO" class="order.OrderDAO" />
        <div class="container">
            <form action="confirmOrder">
                <h1>Confirm Your Order(<a href="logOut">LogOut</a>)</h1>
                <table>
                    <tr>
                        <td>Your Information</td>
                    </tr>

                    <tr>
                        <td>Customer ID : </td>
                        <td>${custDTO.custID}</td>
                        <td>OrderID : </td>
                        <td>${orderDAO.generateOrderID()}</td>
                    </tr>

                    <tr>
                        <td>Customer : </td>
                        <td>${custDTO.firstName} ${custDTO.middleName} ${custDTO.lastName}</td>
                        <td>Phone : </td>
                        <td>${custDTO.phone}</td>
                    </tr>
                    <tr>
                        <td>Address : </td>
                        <td colspan="3">${param.deliveryAddress}</td>
                    <tr>
                        <td>Receiver : </td>
                        <td>${param.receiver}</td>
                        <td>Receiver's Phone : </td>
                        <td>${param.receiverPhone}</td>
                    </tr>
                    <tr>
                    <input type="hidden" value="${TOTAL}" name="TOTAL" />
                    <input type="hidden" value="${currentDate}" name="currentDate" />

                    <input type="hidden" value="${param.receiver}" name="receiver" />
                    <input type="hidden" value="${param.receiverPhone}" name="receiverPhone" />
                    <input type="hidden" value="${param.deliveryAddress}" name="deliveryAddress" />
                    <td> <input class="button-black" type="submit" value="Cancel" name="btAction"/> </td>
                    <td colspan="3"> <input type="submit" value="OK" name="btAction"/> </td>
                    </tr>
                </table>
            </form>
            <c:if test="${not empty requestScope.STATUS}">
                ${requestScope.STATUS}
            </c:if>
        </div>
    </body>
</html>
