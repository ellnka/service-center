<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<jsp:include page="login.jsp"/>

<html>
<head>
    <title>Client page</title>
</head>
<body>

  <c:if test="${not empty orders}">
    <table border="1" align="center" title="Enter Order Data">
      <tr>
        <th>Order number</th>
        <th>Item type</th>
        <th>Order date</th>
        <th>Status</th>
      </tr>
      <c:forEach var="order" items="${orders}">
        <tr>
          <td>${order.id}</td>
          <td>${order.item.itemType.itemTypeName}</td>
          <td>${order.date}</td>
          <td>${order.status}</td>
        </tr>
      </c:forEach>
    </table>
  </c:if>

</body>
</html>
