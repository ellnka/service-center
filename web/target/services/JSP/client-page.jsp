<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
  <script type="text/javascript" language="javascript" src="resources/scripts/orderAjax.js"></script>
  <title>Client page</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/screen.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/print.css" type="text/css" media="print">
  <!--[if lt IE 8]>
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/ie.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/plugins/fancy-type/screen.css" type="text/css" media="screen, projection" />

  <![endif]-->
  <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
  <!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
  <!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<link rel="stylesheet" href="resources/css/card_style.css">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
  <%--<jsp:include page="login.jsp"/>--%>
  <c:import url="login.jsp"/>
  <%--<style>
    table {font-size: 80%; color: red; font-family:
  Arial, Helvetica, sans-serif;}
  </style>--%>
  <c:if test="${not empty orders}">
      <table border="1" align="center"
           style="alignment-adjust: central; width: 70%; text-align: center; margin-left: 15%;
                  margin-right: 15%">
      <thead>
        <tr>
          <th><h4>Order number</h4></th>
          <th><h4>Item type</h4></th>
          <th><h4>Order date</h4></th>
          <th><h4>Status</h4></th>
          <th><h4>Add to total</h4></th>
          <th><h4>Amount</h4></th>
        </tr>
      </thead>
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
            <%--<td> <a href="/services/clientPage?order_id=${order.id}">Pay for order</a></td>--%>
            <td>
              <a href = "<c:url value="/clientPage?order_id=${order.id}"/>">Pay for order</a>
            </td>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${order.status == 'SHOULD_BE_PAID'}">
            <td>
              <a id="order" onclick="addOrderToTotal('${order.id}')"/>Add to total</a>
            </td>
          </c:when>
          <c:otherwise>
            <td></td>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${order.amount != 0}">
            <td>
              <fmt:setLocale value = "en_US"/>
              <c:set var="amount" value="${order.amount / 100}"/>
              <fmt:formatNumber value = "${amount}" type = "currency"/>
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
      <tr>
        <c:set var="total" value="${0}"/>
        <c:set var="count" value="${0}"/>
        <c:forEach var="orderToPay" items="${sessionScope.orders_for_payment}">
          <c:set var="total" value="${total + orderToPay.amount}"/>
          <c:set var="count" value="${count + 1}"/>
        </c:forEach>
        <td colspan="5" style="text-align:right;">
          Total Payment
          <c:if test="${count > 0}">
            (${count} orders)
          </c:if>

        </td>
        <td>
          <fmt:setLocale value = "en_US"/>
          <c:set var="total" value="${total / 100}"/>
          <c:if test="${total > 0}">
            <a href = "<c:url value="/clientPage?enable_payment=true"/>"><fmt:formatNumber value = "${total}" type = "currency"/></a>
          </c:if>

        </td>
      </tr>
    </table>

  </c:if>

  <c:if test="${order != null || pay_for_all_enabled != null}">
    <div>
     <%-- <jsp:include page="payment-page.jsp"/>--%>
      <c:import url="payment-page.jsp"/>
    </div>
  </c:if>

</body>
</html>
