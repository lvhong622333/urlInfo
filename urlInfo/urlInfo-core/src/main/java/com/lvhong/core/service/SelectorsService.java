package com.lvhong.core.service;

import java.util.Date;
import java.util.List;

import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;

public interface SelectorsService {

	List<TmDictonary> querySelectorsInfo(Date dictDate);

	PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search);

	void deleteDictInfo(String dictInfos);

	void addDictInfo(TmDictonary tmDictonary);

	void updateDictInfo(TmDictonary tmDictonary);

	TmDictonary queryDictInfo(String businessKey);

	String queryFlowUrl(String substring, String taskDefinitionKey);

	void agencyAdminapprove(String taskId, String approveAdvice, String processInstanceId, Boolean flags, Long dictId);

	void agencyApply(String taskId, String approveAdvice, String processInstanceId, Boolean flags,
			TmDictonary tmDictionary);

	List<TmDictonary> queryImportDictInfo();

}
