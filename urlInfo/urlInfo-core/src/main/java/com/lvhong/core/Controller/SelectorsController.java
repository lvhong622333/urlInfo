package com.lvhong.core.Controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lvhong.core.pojo.PageList;
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
	
	@RequestMapping("/urlTypeInputPage")
	public String urlTypeInputPage() {
		return "/views/pages/urlTypeInput";
	}
	
	@RequestMapping("/urlTypeSearch")
	@ResponseBody
	public PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search) {
		PageList<TmDictonary> page = selecetorsService.urlTypeSearch(search);
		return page;
	}
	
}
