<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Services : register page</title>
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/screen.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/print.css" type="text/css" media="print">
  <!--[if lt IE 8]>
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/ie.css" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="resources/css/blueprint-css-master/blueprint/plugins/fancy-type/screen.css" type="text/css" media="screen, projection" />
  <![endif]-->
</head>
<body>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" language="javascript" src="resources/scripts/validate.js"></script>
<div class="container">
  <div id="table-view"/>
  <form action="/services/register" method="post" name="register" onsubmit="return  validateForm()">
    <table border="0" align="right"
           style="alignment-adjust: central; width: 30%; text-align: center; margin-left: 35%;
                  margin-right: 35%">
      <tr>
        <td>Name:
        </td>
        <td>
          <input type="text" name="name" value="${param.name}"/>
        </td>
      </tr>
      <tr>
        <td>
          LastName:
        </td>
        <td>
          <input type="text" name="lastName" value="${param.lastName}"/>
        </td>
      </tr>
      <tr>
        <td>
          Email:
        </td>
        <td>
          <input type="email" name="email" value="${param.email}"/>
        </td>
        <td>
          <div style="color: red"> ${emailError} </div>
        </td>

      </tr>
      <tr>
        <td>
          Login:
        </td>
        <td>
          <input  id="login" type="login" name="login" value="${param.login}"/>
        </td>
        <td>
          <div style="color: red"> ${loginError} </div>
        </td>
      </tr>
      <tr>
        <td>
          Password:
        </td>
        <td>
          <input id="password" type="password" name="password" onchange="validatePwd()" onkeyup="validatePwd()"/>
        </td>
      </tr>
      <tr>
        <td>
          Repeat password:
        </td>
        <td>
          <input id="passwordRepeat" type="password" name="passwordRepeat" onchange="validatePwd()" onkeyup="validatePwd()" />
        </td>
        <td>
          <div id="pwdRepeat" style="color: red; display: none"> Passwords are not equal! </div>
        </td>
      </tr>
      <tr>
        <td>
          Date of birth:
        </td>
        <td>
          <input type="date" dataformatas="yyyy-MM-dd" name="dateOfBirth" onchange="hideDateErrorMessage()" value="${param.dateOfBirth}"/>
        </td>
        <td>
          <div id="dateError" style="color: red"> ${dateError} </div>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="register" name="register" formmethod="post"/>
        </td>

      </tr>
    </table>
  </form>
  </div>
</div>
</body>
</html>
