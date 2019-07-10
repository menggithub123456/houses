package com.team.house.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取请求地址
        String url = request.getRequestURI();//page/login.jsp
        //System.out.println(url);
        String path = url.substring(url.lastIndexOf("/" )+1);
        //放行 让用户可用进入登录页
        if(path.equals("login.jsp")||path.equals("loginUser")
                ||path.equals("regs.jsp")||path.equals("insertUser")||path.equals("checkName")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
           //判断有没有登录
            HttpSession session = request.getSession();
            Object o = session.getAttribute("loginInfo");
            if (o==null){
                response.sendRedirect("login.jsp");
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
