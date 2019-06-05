package websale.sale.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MangerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("mangerinterceptor");
        if (request.getSession().getAttribute("mid")!=null){
            return true;
        }
        try{
            response.sendRedirect("/index/0");
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
