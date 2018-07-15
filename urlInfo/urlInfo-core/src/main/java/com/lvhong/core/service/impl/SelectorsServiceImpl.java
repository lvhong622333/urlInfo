package com.lvhong.core.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lvhong.core.dao.TmDictonaryMapper;
import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;
import com.lvhong.core.service.SelectorsService;

@Service("selecetorsService")
public class SelectorsServiceImpl implements SelectorsService {
	
	@Resource
	private TmDictonaryMapper tmDictonaryMappper;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private TaskService taskService;
	
	@Cacheable(value="sysCache",key="#root.methodName + ':' + #dictDate")
	@Override
	public List<TmDictonary> querySelectorsInfo(Date dictDate) {
		List<TmDictonary> querySelectorsInfo = tmDictonaryMappper.querySelectorsInfo();
		return querySelectorsInfo;
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

	@Override
	@Transactional
	public void deleteDictInfo(String dictInfos) {
		String[] infos = dictInfos.split(",");
		tmDictonaryMappper.deleteDictInfo(infos);
	}

	@Override
	@Transactional
	public void addDictInfo(TmDictonary tmDictonary) {
		tmDictonary.setIsvalid("0");
		tmDictonaryMappper.insertSelective(tmDictonary);
	}

	@Override
	@Transactional
	public void updateDictInfo(TmDictonary tmDictonary) {
		tmDictonaryMappper.updateByPrimaryKeySelective(tmDictonary);
	}

}
