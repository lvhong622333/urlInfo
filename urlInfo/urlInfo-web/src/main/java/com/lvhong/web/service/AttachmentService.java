package com.lvhong.web.service;

import java.util.List;
import java.util.Map;

import com.lvhong.web.pojo.TmFileRoute;

public interface AttachmentService {

	void insertFileInfo(List<TmFileRoute> list);

	List<TmFileRoute> queryMyFile(Map<String, Integer> map);

	Integer queryFilePageSize();

	void deleteFile(Long id);

}
