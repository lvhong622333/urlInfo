package com.lvhong.web.service;

import com.lvhong.web.pojo.TmUrlInfo;

public interface UrlInfoService {

	void insertUrlInfo(TmUrlInfo urlInfo);

	void updateUrlInfo(TmUrlInfo urlInfo);

	void deleteUrlInfo(String param);

}
