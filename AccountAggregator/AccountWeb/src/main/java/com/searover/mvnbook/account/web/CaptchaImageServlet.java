package com.searover.mvnbook.account.web;

import com.searover.mvnbook.account.email.AccountEmailService;
import com.searover.mvnbook.account.service.AccountService;
import com.searover.mvnbook.account.service.AccountServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by searover on 7/25/15.
 */
public class CaptchaImageServlet extends HttpServlet {

    private ApplicationContext context;

    private static final long serialVersionUID = 5274323889605521606L;

    @Override
    public void init() throws ServletException {
        super.init();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountEmailService emailService = (AccountEmailService) context.getBean("accountEmailService");
        String key = req.getParameter("key");
        if(key == null || key.length() == 0){
            resp.sendError(400, "No Captcha Key Found");
        }else {
            AccountService service = (AccountService) context.getBean("accountService");
            resp.setContentType("image/jpeg");
            OutputStream out = resp.getOutputStream();
            try {
                out.write(service.generateCaptchaImage(key));
            } catch (AccountServiceException e) {
                resp.sendError(404, e.getMessage());
            }
        }
    }
}
