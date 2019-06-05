package com.how2java.tmall.interceptor;

import com.how2java.tmall.pojo.Manager;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.ManagerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

public class ManagerLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ManagerService managerService;
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] noNeedAuthPage = new String[]{
           "managerlogin"
        };

        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        System.out.println(uri);
        if(uri.startsWith("/admin")){
            String method = StringUtils.substringAfterLast(uri,"/admin" );
            if(!Arrays.asList(noNeedAuthPage).contains(method)){
                Manager manager=(Manager)session.getAttribute("manager");
                if(null==manager){
                    response.sendRedirect("managerlogin");
                    return false;
                }
            }
        }

        return true;
    }
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
