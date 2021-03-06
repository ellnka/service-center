<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
  <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/screen.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/print.css" type="text/css" media="print">
  <!--[if lt IE 8]>
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/ie.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/plugins/fancy-type/screen.css" type="text/css" media="screen, projection" />
  <![endif]-->
</head>
<body>
<c:choose>
  <c:when test="${user == null || user.name.equals('anonymous')}">
    <form action="/services/login" method="post">
      <div class="container" style="width: 15%; text-align: right; margin-left: 85%;
                  margin-right: 0%">
       <div class="table-view">
        <table border="0" align="right">
        <tr>
          <td>Login:</td>
          <td> <input type="text" name="login"/></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td> <input type="password" name="password"/></td>
        </tr>
        <tr>
          <td>
            <c:if test="${loginFailed}">
              <div style="display: ruby-text; color: crimson">Login failed</div>
            </c:if>
          </td>
        </tr>
        <tr>
        <tr>
          <td colspan="1" align="center">
            <input type="submit" value="login" name="login" />
          </td>
          <td colspan="1" align="center">
            <input type="submit" value="register" name="register" />
          </td>
        </tr>
      </table>
      </div>
      </div>
    </form>
  </c:when>
  <c:otherwise>
    <div align="right">
      <c:out  value="Welcome, ${user.name} ${user.lastName}!"/>
    </div>
    <form action="/services/login" method="post">
      <div align="right">

        <button type="submit" value="logout" name="logout" title="Logout"
                style="background-color:transparent; border-color:transparent;">
          <img src="./resources/images/logout.jpg" height="20" width="80"/>
        </button>

      </div>
    </form>
  </c:otherwise>
</c:choose>
</body>
</html>
</body>