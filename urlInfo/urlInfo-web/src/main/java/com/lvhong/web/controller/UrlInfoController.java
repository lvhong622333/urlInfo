package com.lvhong.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lvhong.core.pojo.ResponseResult;
import com.lvhong.shiro.pojo.User;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.service.UrlInfoService;

@Controller
@RequestMapping("/urlInfo")
public class UrlInfoController {
	
	@Resource
	private UrlInfoService urlInfoService;
	
	@RequestMapping("/page/{urlInputPage}")
	public String urlInputPage(@PathVariable String urlInputPage , @ModelAttribute("inputFailInfo") String inputFailInfo , Model model) {
		String page = "/views/pages/" + urlInputPage;
		if(inputFailInfo != null && inputFailInfo != "") {
			model.addAttribute("inputFailInfo", inputFailInfo);
		}
		return page;
	}
	
	@RequestMapping("/urlInput")
	public String urlInput(TmUrlInfo urlInfo,Model model,HttpSession session) {
		try {			
			User user = (User) session.getAttribute("user");
			urlInfo.setCreateBy(user.getId());
			urlInfoService.insertUrlInfo(urlInfo);
			return "redirect:/urlInfo/page/urlSearch";
		}catch(Exception e) {
			model.addAttribute("inputFailInfo", "对不起，你输入的网址信息已存在！");
			return "redirect:/urlInfo/page/urlInput";
		}
	}
	
	@RequestMapping("/updateUrlInfo")
	@ResponseBody
	public ResponseResult urlInfoUpdate(TmUrlInfo urlInfo) {
		ResponseResult result = new ResponseResult();
		try {
			urlInfoService.updateUrlInfo(urlInfo);
			result.setStatusCode(200);
			result.setStatusInfo("更新成功");
			result.setSuccessOrError("success");
			return result;
		}catch(Exception e) {
			result.setStatusCode(400);
			result.setStatusInfo("更新失败");
			result.setSuccessOrError("fail");
			return result;
		}
	}
	@RequestMapping("/deleteUrlInfo")
	@ResponseBody
	public ResponseResult deleteUrlInfo(String param) {
		ResponseResult result = new ResponseResult();
		urlInfoService.deleteUrlInfo(param);
		return result;
	}
}
