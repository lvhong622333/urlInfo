package com.lvhong.web.service;

import java.util.List;
import java.util.Map;

import com.lvhong.web.pojo.TmFileRoute;

public interface AttachmentService {

	void insertFileInfo(List<TmFileRoute> list);

	List<TmFileRoute> queryMyFile(Map<String, Object> map);

	Integer queryFilePageSize(Map<String, Object> map);

	void deleteFile(Long id);

}
