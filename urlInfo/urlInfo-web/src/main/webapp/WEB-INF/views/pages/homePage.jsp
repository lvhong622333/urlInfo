<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../module/pageModule.jsp"%>
<title>主页</title>
<style type="text/css">
img {
	height: 520px !important;
	display: inline-block!important;
}
.item{
	margin-left: 122px!important;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#urlPage").addClass("active");
		$("#urlSearch").removeClass("active");
		$("#urlInput").removeClass("active");
		$("#urlTypeInput").removeClass("active");
	});
</script>
</head>
<body>
	<%@include file="../module/header.jsp"%>
	<%@include file="../module/urlTag.jsp"%>
	<div id="myCarousel" class="carousel slide" style="height: 520px;">
		<!-- 轮播（Carousel）指标 -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<!-- 轮播（Carousel）项目 -->
		<div class="carousel-inner">
			<div class="item active">
				<a href="https://www.baidu.com"> <img
					src="http://s10.sinaimg.cn/mw690/006LDoUHzy7auXEvyzLd9&690"
					alt="First slide">
					<img alt="" src="http://www.hinews.cn/pic/0/12/18/96/12189608_673256.jpg">
				</a>
			</div>
			<div class="item">
				<a href="https://www.sohu.com">				
					<img
						src="http://s8.sinaimg.cn/middle/8ee3e0acxb0171b491f27&690"
						alt="Second slide">
					<img alt="" src="http://i2.sinaimg.cn/ent/j/2013-12-11/U1345P28T3D4058821F329DT20131211175800.jpg">
				</a>
			</div>
			<div class="item">
				<a href="https://v.qq.com/">				
					<img
						src="http://img.mp.sohu.com/upload/20170511/0e772de531824af09ea29dcb94055d48_th.png"
						alt="Third slide">
					<img alt="" src="http://s2.sinaimg.cn/middle/64fa6e0ag7fa0f4f18db1&690">
				</a>
			</div>
		</div>
		<!-- 轮播（Carousel）导航 -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<%@include file="../module/footer.jsp"%>
</body>
</html>