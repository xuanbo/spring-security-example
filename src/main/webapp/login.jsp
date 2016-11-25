<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<c:import url="WEB-INF/views/template/head.jsp" />

    <c:url value="/login" var="loginUrl"/>
    <c:url value="/captcha" var="captchaUrl"/>
    <form action="${loginUrl}" method="post">
        <c:if test="${error != null}">
            <p>${error}</p>
        </c:if>
        <c:if test="${logout != null}">
            <p>${logout}</p>
        </c:if>
        <p>
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" />
        </p>
        <p>
            <label for="password">密码：</label>
            <input type="password" id="password" name="password" />
        </p>
        <p>
            <label for="captcha">验证码：</label>
            <input type="text" id="captcha" name="captcha" />
            <img src="${captchaUrl}">
        </p>
        <p>
            <label for="remember-me">一周内记住我：</label>
            <input type="checkbox" id="remember-me" name="remember-me" value="true" />
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">登录</button>
    </form>
</body>
</html>
