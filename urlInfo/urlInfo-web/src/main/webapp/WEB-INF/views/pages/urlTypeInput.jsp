<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../module/pageModule.jsp"%>
<title>数据字典</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/urlTypeInput.js"></script>
<script type="text/javascript">
	$(function(){
		window.ctx = '${pageContext.request.contextPath}';
		$("#urlInput").removeClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlTypeInput").addClass("active");
		setTableInfo();
	});
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp" %>
	<div class="container">
		<div class="row" style="overflow: scroll; height: 522px;"
			class="pre-scrollable">
			<table id="urlType_table" data-toolbar="#typeToolbar" data-search="true"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true">
			</table>
		</div>
		<div id="typeToolbar" class="btn-group">
			<button id="btn-add" type="button" class="btn"
				onclick="addUrlType()" data-toggle="modal" data-target="">
				<span aria-hidden="true" class="icon icon-plus-sign"></span>增加
			</button>
			<button id="btn-add" type="button" class="btn"
				onclick="updateUrlType()" data-toggle="modal" data-target="">
				<span aria-hidden="true" class="icon icon-plus-sign"></span>更新
			</button>
			<button id="btn-del" type="button" class="btn"
				onclick="deleteUrlType()">
				<span aria-hidden="true" class="icon icon-remove-sign"></span>删除
			</button>
		</div>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>