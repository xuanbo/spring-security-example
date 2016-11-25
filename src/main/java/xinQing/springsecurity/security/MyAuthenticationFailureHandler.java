package xinQing.springsecurity.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证失败处理，请求转发到某个页面
 * 如果登录使用Ajax提交的，那么可以将onAuthenticationFailure()方法修改为JSON异常信息返回
 *
 * Created by xuan on 16-11-24.
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    // 默认请求转发到/login.jsp
    private String forwardUrl = "/login.jsp";

    /**
     * 修改默认的请求转发url
     *
     * @param forwardUrl    请求转发url
     */
    public MyAuthenticationFailureHandler(String forwardUrl) {
        if (forwardUrl != null && !forwardUrl.isEmpty()) {
            this.forwardUrl = forwardUrl;
        }
    }

    /**
     * 自定义身份认证失败处理
     *
     * @param httpServletRequest    HttpServletRequest
     * @param httpServletResponse   HttpServletResponse
     * @param e AuthenticationException，身份认证异常
     * @throws IOException  I/O exception
     * @throws ServletException Servlet Exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 获取登录认证失败异常信息
        String error = e.getMessage();
        httpServletRequest.setAttribute("error", error);
        // 请求转发
        httpServletRequest.getRequestDispatcher(forwardUrl).forward(httpServletRequest, httpServletResponse);
    }
}
