package com.lvhong.core.service;

import java.util.List;

import com.lvhong.core.pojo.PageList;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;

public interface SelectorsService {

	List<TmDictonary> querySelectorsInfo();

	PageList<TmDictonary> urlTypeSearch(TmDictonarySearch search);

	void deleteDictInfo(String dictInfos);

	void addDictInfo(TmDictonary tmDictonary);

	void updateDictInfo(TmDictonary tmDictonary);

}
