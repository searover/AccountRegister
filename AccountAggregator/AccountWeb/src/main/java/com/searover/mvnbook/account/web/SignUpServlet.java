package com.searover.mvnbook.account.web;

import com.searover.mvnbook.account.service.AccountService;
import com.searover.mvnbook.account.service.AccountServiceException;
import com.searover.mvnbook.account.service.SignUpRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by searover on 7/25/15.
 */
public class SignUpServlet extends HttpServlet{

    private static final long serialVersionUID = 4784742296013868199L;

    private ApplicationContext context;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        String captchaKey = req.getParameter("captcha_key");
        String captchaValue = req.getParameter("captcha_value");

        if(id == null || id.length() == 0 || email == null || email.length() == 0 ||
                name == null || name.length() == 0 || password == null || password.length() == 0 ||
                confirmPassword == null || confirmPassword.length() == 0 ||
                captchaKey == null || captchaKey.length() == 0 || captchaValue == null || captchaValue.length() == 0){
            resp.sendError(400, "Parameter Incomplete.");
        }

        AccountService accountService = (AccountService) context.getBean("accountService");
        SignUpRequest req2 = new SignUpRequest();
        req2.setId(id);
        req2.setName(name);
        req2.setEmail(email);
        req2.setPassword(password);
        req2.setConfirmPassword(confirmPassword);
        req2.setCaptchaKey(captchaKey);
        req2.setCaptchaValue(captchaValue);

        req2.setActivateServiceUrl(getServletContext().getRealPath("/") + "activate");

        try {
            accountService.signUp(req2);
            resp.getWriter().print("Account is created, please check your email box for activation link.");
        } catch (AccountServiceException e) {
            resp.sendError(400, e.getMessage());
            return;
        }
    }
}
