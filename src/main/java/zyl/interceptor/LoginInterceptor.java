package zyl.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.*;
import java.io.IOException;


public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        HttpSession session =request.getSession(false);
        if (session==null || session.getAttribute("user")==null){
            request.getSession().setAttribute("from",request.getRequestURI());
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
