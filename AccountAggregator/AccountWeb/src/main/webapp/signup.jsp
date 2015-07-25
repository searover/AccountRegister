<%--
  Created by IntelliJ IDEA.
  User: searover
  Date: 7/25/15
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.searover.mvnbook.account.service.*,
                  org.springframework.context.ApplicationContext,
                  org.springframework.web.context.support.WebApplicationContextUtils" %>
<html>
<head>
    <title></title>
    <style type="text/css">

    </style>
</head>
<body>
    <%
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AccountService accountService = (AccountService)context.getBean("accountService");
        String captchaKey = accountService.generateCaptchaKey();
    %>
    <div class="text-field">
        <h2>注册新账户</h2>
        <form name="signup" action="signup" method="post">
            <label>账户ID：</label><input type="text" name="id"><br/>
            <label>Email：</label><input type="text" name="email"><br/>
            <label>显示名称：</label><input type="text" name="name"><br/>
            <label>密码：</label><input type="text" name="password"><br/>
            <label>确认密码：</label><input type="text" name="confirm_password"><br/>
            <label>验证码：</label><input type="text" name="captcha_value"><br/>
            <input type="hidden" name="captcha_key" value="<%= captchaKey%>"/>
            <img src="<%= request.getContextPath()%>/captcha_image?key=<%= captchaKey%>"/>
            <br/>
            <button>确认并提交</button>
        </form>
    </div>
</body>
</html>
