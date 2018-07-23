package com.lvhong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/urlInfo")
@Controller
public class AttachmentController {
	
	@RequestMapping("/attachment")
	public String attachment() {
		return "/views/pages/attachment";
	}
	
	@RequestMapping("/attachment/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] file) {
		for (MultipartFile multipartFile : file) {			
			System.out.println(multipartFile.getOriginalFilename());
		}
		return "上传成功";
	}
	
}
