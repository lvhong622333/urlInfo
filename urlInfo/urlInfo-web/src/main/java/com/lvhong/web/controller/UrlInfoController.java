package com.lvhong.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/urlInputPage")
	public String urlInputPage() {
		return "/views/pages/urlInput";
	}
	
	@RequestMapping("/urlInput")
	public String urlInput(TmUrlInfo urlInfo,Model model,HttpSession session) {
		User user = (User) session.getAttribute("user");
		urlInfo.setCreateBy(user.getId());
		urlInfoService.insertUrlInfo(urlInfo);
		return "redirect:/urlInfo/urlSearchPage";
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
