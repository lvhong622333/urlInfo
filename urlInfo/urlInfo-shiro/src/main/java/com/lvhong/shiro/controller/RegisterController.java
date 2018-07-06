package com.lvhong.shiro.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lvhong.core.util.MD5Utils;
import com.lvhong.shiro.pojo.User;
import com.lvhong.shiro.service.UserService;

@Controller
@RequestMapping("/urlInfo")
public class RegisterController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/register")
	public String register() {
		return "/views/pages/register";
	}
	
	@RequestMapping("/register/info")
	public String register(@ModelAttribute User user,HttpSession session,Model model) {
		//密码加密
		try {			
			//验证用户名
			if(user.getUserName().length() < 8 || user.getUserName().length() > 12) {
				model.addAttribute("userNameError", "输入字符长度不满足条件");
				return "/views/pages/register";
			}
			//验证密码
			if(!user.getPassword().equals(user.getConfirmPassword())) {
				model.addAttribute("confirmPasswordError", "两次输入密码不一致");
				return "/views/pages/register";
			}
			//验证手机号码
			String reg = "^1[3-9]\\d{9}$";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(user.getTelePhone());
			if(!matcher.matches()) {
				model.addAttribute("telePhoneError", "号码格式不正确！");
				return "/views/pages/register";
			}
			String password = MD5Utils.encrypt(user.getUserName(),user.getPassword(), 3);
			user.setPassword(password);
			userService.addUser(user);
		}catch(Exception e) {
			if(e.getCause().toString().indexOf("AK_Key_2") != -1) {
				model.addAttribute("telePhoneError", "手机号码已经被注册！");
			}
			if(e.getCause().toString().indexOf("AK_Key_3") != -1) {
				model.addAttribute("userNameError", "用户名已经被注册！");
			}
			return "/views/pages/register";
		}
		return "/views/pages/login";
	}
	
	@RequestMapping("/checkName")
	@ResponseBody
	public String checkName(String name) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		User user = userService.findUserByphone(map);
		if(user != null) {
			return "用户名已经被注册了";
		}
		return null;
	}
	
	@RequestMapping("/checkphone")
	@ResponseBody
	public String checkphone(String telephone) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("telephone", telephone);
		User user = userService.findUserByphone(map);
		if(user != null) {
			return "手机号码已经被注册了";
		}
		return null;
	}
}
