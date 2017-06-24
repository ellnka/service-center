<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="login.jsp"/>
<html>
<head>
    <title>Service-Center</title>
</head>
<body>
<form action="/services/orderManagement" method="post">
    <table border="0" align="center">
        <tr>
            <td width="80">
            <button type="submit" value="create_order" name="create_order" title="Create order"
                    style="background-color:transparent; border-color:transparent;">
                <img src="./resources/images/CreateOrder.jpg" height="80" width="80"/>
            </button>
            </td>
            <td>
                <input type="text" name="order_number" title="Enter order number" height="80"/>
            </td>
            <td width="80">
            <button type="submit" value="search_for_order" name="search_for_order" title="Search order"
                    style="background-color:transparent; border-color:transparent;">
                <img src="./resources/images/Search.jpg" height="80" width="80"/>
            </button>
            </td>
        </tr>
    </table>
</form>
<form action="/services/orderManagement" method="post">
    <c:choose>
        <c:when test="${create_order}">
            <table border="0" align="center" title="Enter Order Data">
                <tr>
                    <td>Name</td>
                    <td>
                        <input type="text" name="name"/>
                    </td>
                </tr>
                <tr>
                    <td>LastName</td>
                    <td>
                        <input type="text" name="lastName"/>
                    </td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td>
                        <input type="text" name="login"/>
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>Serial Number</td>
                    <td>
                        <input type="text" name="serial_number"/>
                    </td>
                </tr>
                <tr align="center">
                    <td colspan="2">
                        <button type="submit" value="order_creation" name="order_creation" title="Create"
                                style="background-color:transparent; border-color:transparent;">
                            <img src="./resources/images/CreateOrder.jpg" height="80" width="80"/>
                        </button>
                    </td>
                </tr>
            </table>
        </c:when>
    </c:choose>
</form>


<c:choose>
    <c:when test="${order != null}">
        <table border="0" align="center" title="Enter Order Data">
            <tr>
                <td>Name:</td>
                <td>
                   <c:out value="${order.user.name}"/>
                </td>

                <td>Item:</td>
                <td>
                    <c:out value="${order.item.itemType.itemTypeName}"/>
                </td>
            </tr>
            <tr>
                <td>LastName:</td>
                <td>
                    <c:out value="${order.user.lastName}"/>
                </td>

                <td>Manufacture:</td>
                <td>
                    <c:out value="${order.item.manufacture.manufactureName}"/>
                </td>
            </tr>
            <tr>
                <td>login</td>
                <td>
                    <c:out value="${order.user.login}"/>
                </td>

                <td>Serial Number:</td>
                <td>
                    <c:out value="${order.item.serialNumber}"/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <c:out value="${order.user.password}"/>
                </td>

                <td>Order status:</td>
                <td>
                    <c:out value="${order.status}"/>
                </td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>
                    <c:out value="${order.user.email}"/>
                </td>

                <td>Order number:</td>
                <td>
                    <c:out value="${order.id}"/>
                </td>
            </tr>
        </table>
    </c:when>
</c:choose>
</body>
</html>
