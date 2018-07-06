package com.lvhong.shiro.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import com.lvhong.shiro.pojo.User;

public class SessionFormAuthenticationFilter extends FormAuthenticationFilter {
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		 //获取已登录的用户信息
        User user = (User) subject.getPrincipal();
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("user", user);
		return super.onLoginSuccess(token, subject, request, response);
	}
	
}
