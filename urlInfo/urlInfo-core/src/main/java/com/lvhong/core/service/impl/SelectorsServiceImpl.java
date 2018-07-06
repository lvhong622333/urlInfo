package com.lvhong.core.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.lvhong.core.dao.TmDictonaryMapper;
import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;
import com.lvhong.core.service.SelectorsService;

@Service("selecetorsService")
public class SelectorsServiceImpl implements SelectorsService {
	
	@Resource
	private TmDictonaryMapper tmDictonaryMappper;
	
	@Cacheable(value="sysCache",key="#root.methodName")
	@Override
	public List<TmDictonary> querySelectorsInfo() {
		return tmDictonaryMappper.querySelectorsInfo();
	}

	@Override
	public PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search) {
		search.setPageNo(search.getLimit()*(search.getPageNo() - 1));
		PageList<TmDictonary> page = new PageList<TmDictonary>();
		Integer count = tmDictonaryMappper.urlTypeSearchCount(search);
		page.setTotal(count);
		List<TmDictonary> urlTypeSearch = tmDictonaryMappper.urlTypeSearch(search);
		page.setRows(urlTypeSearch);
		return page;
	}

}
