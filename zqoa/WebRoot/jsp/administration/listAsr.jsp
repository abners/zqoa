<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>案件类型管理</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<link href="<%=path%>/css/types.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>		
		<script type="text/javascript" src="<%=path%>/js/extremecomponents.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<div id="main">
			<div class="toolbar">
				<p class="toolbar-shadow">
					&nbsp;
				</p>
				<div id="toolbar-content">
					<p class="crumb">
						<b>您的位置：</b>
						<a href="/zqoa/index.php/toIndex.htm">首页</a> > 
                        <a href="/zqoa/index.php/User/listAsr.htm">行政管理</a> >
						<a href="javascript:void(0);">登记管理</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						登记管理
					</h4>
					<div class="list-form">
						<ec:table items="zqAsrList" var="row"
							action="listDeparts.htm" width="90%" autoIncludeParameters="false" showPagination="false">

							<ec:row highlightRow="true">

								<ec:column property="name" style="height:35px;text-align:center" title="标题"><a href="asrDetail.htm?asrId=${row.id }" title="查看详情">${row.name }</a>
								</ec:column>
								<ec:column property="authorName" style="height:35px;text-align:center" title="登记人">
								</ec:column>
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;" title="操作">
									<a href="modifyAsrInit.htm?asrId=${row.id}" title="修改">修改</a>|<a href="${row.id}" title="删除">删除</a>
								</ec:column>

							</ec:row>

						</ec:table>

				</fieldset>
			</div>
	</body>
</html>
<script>
$(document).ready(
		function(){
			
			$("a[title=删除]").click(function(){
				var contractTypeId = $(this).attr("href");
				if(confirm("删除后将无法恢复，是否确认?")){
					$.ajax({
						type : "post",
						url : "deltAsr.htm",
						dataType : "json",
						data : {asrId:contractTypeId
						},
						success : function(data) { //成功后返回的页面
							
							if (data.status == '1'){
								alert("删除成功!");
								window.location.reload();
							}else{
								alert("删除失败!");
							}
						},
						error:function(error){
							alert("网络异常，请重新登录!");
						}
					});
				}
				//window.location.href=window.location;
				return false;
			});
		}
		);
</script>
