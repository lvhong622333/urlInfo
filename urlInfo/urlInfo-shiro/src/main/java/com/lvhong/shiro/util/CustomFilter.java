package com.lvhong.shiro.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义shiro权限过滤器
 * @author lvhong
 */
public class CustomFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//获取subject主题
		Subject subject = getSubject(request, response);
		String[] roles = (String[]) mappedValue;
		if(roles == null || roles.length == 0) {
			return true;
		}else {
			for(String role : roles) {
				if(subject.hasRole(role)) {
					return true;
				}
			}
		}
		return false;
	}

}
