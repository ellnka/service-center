<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Checkout Form</title>
    <link rel="stylesheet" href="resources/css/card_style.css">
    <script type="text/javascript" language="javascript" src="resources/scripts/timeoutRedirect.js"></script>
    <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<body>
<c:choose>
    <c:when test="${paymentSuccessful == null || !paymentSuccessful}">
        <form class="checkout" id="paymentForm" method="post" action="/services/paymentPage">
            <div class="checkout-header">
                <h1 class="checkout-title">
                    Checkout
                    <span class="checkout-price">$<fmt:formatNumber pattern="#,##" value="${order.amount}"/></span>
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
        <c:out value="Payment successful!"/>
        <script type="text/javascript" language="JavaScript">
            redirectFunction();
        </script>
    </c:otherwise>
</c:choose>
<c:if test="${paymentSuccessful!=null && !paymentSuccessful}">
    <div align="center"><c:out value="Something is wrong"/></div>
</c:if>

</body>

