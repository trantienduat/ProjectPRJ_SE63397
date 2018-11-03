<%-- 
    Document   : register
    Created on : Oct 26, 2018, 11:07:27 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>

    </head>
    <body>
        <c:set var="errors" value="${requestScope.ERRORS}"></c:set>
            <h1>Register Page</h1>
            <form action="register" method="post">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Username</td>
                            <td colspan="2"><input type="text" name="username" 
                                                   value="<c:if test="${empty errors.usernameLengthError}">${param.username}</c:if>"
                            </td>
                        </tr>
                    <c:if test="${not empty errors.usernameLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.usernameLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Last Name</td>
                        <td colspan="2"><input type="text" name="lastname" 
                                               value="<c:if test="${empty errors.lastnameLengthError}">${param.lastname}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.lastnameLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.lastnameLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Middle Name</td>
                        <td colspan="2"><input type="text" name="middlename" 
                                               value="<c:if test="${empty errors.middlenameLengthError}">${param.middlename}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.middlenameLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.middlenameLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>First Name</td>
                        <td colspan="2"><input type="text" name="firstname" 
                                               value="<c:if test="${empty errors.firstnameLengthError}">${param.firstname}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.firstnameLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.firstnameLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Password</td>
                        <td colspan="2"><input type="password" name="password" 
                                               value="<c:if test="${empty errors.passwordLengthError}">${param.password}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.passwordLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.passwordLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Address</td>
                        <td colspan="2"><input type="text" name="address" 
                                               value="<c:if test="${empty errors.addressLengthError}">${param.address}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.addressLengthError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.addressLengthError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Phone</td>
                        <td colspan="2"><input type="text" name="phone" 
                                               value="<c:if test="${empty errors.phoneFormatError}">${param.phone}</c:if>"/></td>
                        </tr>
                    <c:if test="${not empty errors.phoneFormatError}">
                        <tr>
                            <td colspan="2" style="color: red; font-style: italic">
                                ${errors.phoneFormatError}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="register" value="Register"/></td>
                        <td><input type="reset" value="Reset"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <c:if test="${not empty errors.duplicateUsernameError}">
        <tr>
            <td colspan="2" style="color: red; font-style: italic">
                <font style="color: red; font-style: italic">
                <h3>${errors.duplicateUsernameError}</h3>
                </font>
            </td>
        </tr>
    </c:if>
    <c:if test="${not empty requestScope.STATUS}">
        <font style="color: red">
        <h3>${requestScope.STATUS}</h3>
        </font>
    </c:if>
    </br><a href="login.jsp">Sign in</a>
</body>
</html>
