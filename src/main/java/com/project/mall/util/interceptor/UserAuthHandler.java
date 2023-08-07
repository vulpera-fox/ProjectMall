package com.project.mall.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project.mall.command.UserVO;

public class UserAuthHandler implements HandlerInterceptor{

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("작동함");
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userVO") == null) {
	        response.sendRedirect(request.getContextPath() + "/user/login");
	        return false;
	    }
	    return true;	
	}
}
