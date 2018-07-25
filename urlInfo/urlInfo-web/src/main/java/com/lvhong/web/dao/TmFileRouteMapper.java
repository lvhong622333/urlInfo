package com.lvhong.web.dao;

import java.util.List;
import java.util.Map;

import com.lvhong.web.pojo.TmFileRoute;

public interface TmFileRouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TmFileRoute record);

    int insertSelective(TmFileRoute record);

    TmFileRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmFileRoute record);

    int updateByPrimaryKey(TmFileRoute record);

	void insertFileInfo(List<TmFileRoute> list);

	List<TmFileRoute> queryMyFile(Map<String, Integer> map);

	Integer queryFilePageSize();
}