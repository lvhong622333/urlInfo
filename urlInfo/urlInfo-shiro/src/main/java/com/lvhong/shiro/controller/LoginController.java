package com.lvhong.shiro.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lvhong.shiro.pojo.User;
import com.lvhong.shiro.service.UserService;

@Controller
@RequestMapping("/urlInfo")
public class LoginController {
	
	@Resource
	UserService userService;
	
//	@RequestMapping("/login/{login}")
//	public String login(@PathVariable String login) {
//		String url = "/views/pages/" + login;
//		return url;
//	}
	
	@RequestMapping("/login")
	public String login(String userName ,String password,Model model,HttpSession session) {
		if(userName == null || "".equals(userName)) {
			model.addAttribute("userNameError", "用户名不能为空！");
            return "/views/pages/login";
		}
		if(password == null || "".equals(password)) {
			model.addAttribute("passwordError", "密码不能为空！");
            return "/views/pages/login";
		}
		Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
		if(!subject.isAuthenticated()) {			
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			try {
				subject.login(token);		
			}catch(AuthenticationException e) {
				model.addAttribute("passwordError", "用户名或密码不正确！");
				return "/views/pages/login";
			}
		}
		User user = userService.queryUserByUserName(userName);
		subject.getSession().setAttribute("user", user);
		return "/views/pages/homePage";
	}
}
