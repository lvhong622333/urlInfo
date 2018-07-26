package com.lvhong.web.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lvhong.web.dao.TmFileRouteMapper;
import com.lvhong.web.pojo.TmFileRoute;
import com.lvhong.web.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	
	@Resource
	private TmFileRouteMapper tmfileRouteMapper;

	@Override
	public void insertFileInfo(List<TmFileRoute> list) {
		tmfileRouteMapper.insertFileInfo(list);
	}

	@Override
	public List<TmFileRoute> queryMyFile(Map<String, Object> map) {
		return tmfileRouteMapper.queryMyFile(map);
	}

	@Override
	public Integer queryFilePageSize(Map<String, Object> map) {
		return tmfileRouteMapper.queryFilePageSize(map);
	}

	@Override
	public void deleteFile(Long id) {
		tmfileRouteMapper.deleteByPrimaryKey(id);
	}

}
