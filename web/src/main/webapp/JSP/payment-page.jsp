<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<body>
<c:choose>
    <c:when test="${paymentSuccessful == null || !paymentSuccessful}">
        <form class="checkout" id="paymentForm" method="post" action="/services/paymentPage">
            <div class="checkout-header">
                <h1 class="checkout-title">
                    Checkout
                    <fmt:setLocale value = "en_US"/>
                    <c:set var="total" value="${0}"/>
                    <c:choose>
                        <c:when test="${pay_for_all_enabled}">

                            <c:forEach var="orderToPay" items="${sessionScope.orders_for_payment}">
                                <c:set var="total" value="${total + orderToPay.amount}"/>
                            </c:forEach>
                            <c:set var="amount" value="${total / 100}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="amount" value="${order.amount / 100}"/>
                        </c:otherwise>
                    </c:choose>
                    <span class="checkout-price"><fmt:formatNumber value = "${amount}" type = "currency"/></span>



                </h1>
            </div>
            <p>
                <input name="name" type="text" class="checkout-input checkout-name" placeholder="Your name" autofocus>
                <input name="month" type="text" class="checkout-input checkout-exp" maxlength="2" placeholder="MM">
                <input name="year" type="text" class="checkout-input checkout-exp" maxlength="2" placeholder="YY">
            </p>
            <p>
                <input name="cardNumber" type="text" class="checkout-input checkout-card" placeholder="4111 1111 1111 1111">
                <input name="cvc" type="password" class="checkout-input checkout-cvc" maxlength="4" placeholder="CVC">
            </p>
            <p>
                <input type="submit" value="Place order" class="checkout-btn" formmethod="post">
                <input type="hidden" name="order_id" value="${order.id}">
            </p>
        </form>
    </c:when>
    <c:otherwise>
        <div align="center">
            <c:out value="Payment successful!"/>
            <script>
                setTimeout("location.href = '/services/main';", 5000);
            </script>
        </div>
    </c:otherwise>
</c:choose>
<c:if test="${paymentSuccessful!=null && !paymentSuccessful}">
    <div align="center"><c:out value="Something is wrong"/></div>
</c:if>

</body>

