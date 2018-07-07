package com.lvhong.core.Controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.ResponseResult;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;
import com.lvhong.core.service.SelectorsService;

@RequestMapping("/urlInfo")
@Controller
public class SelectorsController {
	
	@Resource
	private SelectorsService selecetorsService;
	
	@RequestMapping("/getSelectors")
	@ResponseBody
	public List<TmDictonary> getSelectors() {
		List<TmDictonary> list = selecetorsService.querySelectorsInfo();
		return list;
	}
	
	@RequestMapping("/urlTypeSearch")
	@ResponseBody
	public PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search) {
		PageList<TmDictonary> page = selecetorsService.urlTypeSearch(search);
		return page;
	}
	
	@RequestMapping("/deleteDictInfo")
	@ResponseBody
	public String deleteDictInfo(String dictInfos) {
		selecetorsService.deleteDictInfo(dictInfos);
		return "";
	}
	@RequestMapping("/addDictInfo")
	@ResponseBody
	public ResponseResult addDictInfo(TmDictonary tmDictonary) {
		ResponseResult resp = new ResponseResult();
		try {			
			selecetorsService.addDictInfo(tmDictonary);
			resp.setStatusCode(200);
			resp.setStatusInfo("数据信息添加成功！");
			resp.setSuccessOrError("success");
			return resp;
		}catch(Exception e) {
			resp.setStatusCode(500);
			resp.setStatusInfo("数据信息添加失败！");
			resp.setSuccessOrError("fail");
			return resp;
		}
	}
	
	@RequestMapping("/updateDictInfo")
	@ResponseBody
	public ResponseResult updateDictInfo(TmDictonary tmDictonary) {
		ResponseResult resp = new ResponseResult();
		try {			
			selecetorsService.updateDictInfo(tmDictonary);
			resp.setStatusCode(200);
			resp.setStatusInfo("数据信息添加成功！");
			resp.setSuccessOrError("success");
			return resp;
		}catch(Exception e) {
			resp.setStatusCode(500);
			resp.setStatusInfo("数据信息添加失败！");
			resp.setSuccessOrError("fail");
			return resp;
		}
	}
	
}
