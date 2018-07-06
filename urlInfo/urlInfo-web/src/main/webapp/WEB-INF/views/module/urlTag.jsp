<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs" style="margin-top: 10px; margin-left: 40px;">
	<li id="urlSearch" class="active"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/urlSearchPage'">网址检索</a></li>
	<li id="urlInput"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/urlInputPage'">网址录入</a></li>
	<li id="urlTypeInput"><a href="#"
		onclick="window.location.href='${pageContext.request.contextPath}/urlInfo/urlTypeInputPage'">数据字典</a></li>
</ul>