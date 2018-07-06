package com.lvhong.core.dao;

import java.util.List;
import com.lvhong.core.pojo.TmDictonary;
import com.lvhong.core.pojo.TmDictonarySearch;

public interface TmDictonaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TmDictonary record);

    int insertSelective(TmDictonary record);

    TmDictonary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TmDictonary record);

    int updateByPrimaryKey(TmDictonary record);

	List<TmDictonary> querySelectorsInfo();

	List<TmDictonary> urlTypeSearch(TmDictonarySearch search);

	Integer urlTypeSearchCount(TmDictonarySearch search);
}