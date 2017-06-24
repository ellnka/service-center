<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="login.jsp"/>

<html>
<head>
  <title>Client page</title>
  <link rel="stylesheet" href="/resources/css/all_pages.css" type="text/css">
</head>
<body>

  <c:if test="${not empty orders}">
    <table border="1" align="center">
      <tr>
        <th>Order number</th>
        <th>Item type</th>
        <th>Order date</th>
        <th>Status</th>
        <th>Amount</th>
      </tr>
      <c:forEach var="order" items="${orders}">
        <tr>
          <td>${order.id}</td>
          <td>${order.item.itemType.itemTypeName}</td>
          <td>${order.date}</td>
          <c:choose>
            <c:when test="${order.status != 'SHOULD_BE_PAID'}">
              <td>${order.status}</td>
            </c:when>
            <c:otherwise>
              <td> <a href="/services/clientPage?order_id=${order.id}">Pay for order</a></td>
            </c:otherwise>
          </c:choose>
          <c:choose>
            <c:when test="${order.amount != 0}">
              <td>
                $<fmt:formatNumber pattern="#,##" value="${order.amount}"/>
              </td>
            </c:when>
            <c:otherwise>
              <td>
                <c:out value=""/>
              </td>
            </c:otherwise>
          </c:choose>
        </tr>
      </c:forEach>
    </table>
  </c:if>

  <c:if test="${order != null}">
    <jsp:include page="payment-page.jsp"/>
  </c:if>

</body>
</html>
