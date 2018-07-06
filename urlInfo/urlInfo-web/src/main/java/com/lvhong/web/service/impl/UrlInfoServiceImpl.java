package com.lvhong.web.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lvhong.web.dao.TmUrlInfoMapper;
import com.lvhong.web.pojo.TmUrlInfo;
import com.lvhong.web.service.UrlInfoService;

@Service
public class UrlInfoServiceImpl implements UrlInfoService {
	
	@Resource
	private TmUrlInfoMapper tmUrlInfoMapper;

	@Override
	public void insertUrlInfo(TmUrlInfo urlInfo) {
		tmUrlInfoMapper.insertSelective(urlInfo);
	}

	@Override
	public void updateUrlInfo(TmUrlInfo urlInfo) {
		tmUrlInfoMapper.updateByPrimaryKeySelective(urlInfo);
	}

	@Override
	public void deleteUrlInfo(String param) {
		String[] paramList = param.split(",");
		tmUrlInfoMapper.deleteBatchByPrimaryKey(paramList);
	}

}
