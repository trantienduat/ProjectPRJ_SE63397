<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Login </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:if test="${not empty sessionScope.MEMBER}">
            <c:redirect url="searchShoe.jsp"/>
        </c:if>
        <h1>Login Page</h1>
        <form action="login" method="post">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td colspan="2"><input type="text" name="txtUsername" value=""/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td colspan="2"><input type="password" name="txtPassword" value=""/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login"/></td>
                        <td><input type="reset" value="Reset"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <a href="register.jsp">Sign Up</a>
    </body>
</html>
