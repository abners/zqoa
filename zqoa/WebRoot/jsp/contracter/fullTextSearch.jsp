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

		<title>合同类型</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<link href="<%=path%>/css/types.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>		
		<script type="text/javascript" src="<%=path%>/js/extremecomponents.js"></script>
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
						<a href="toIndex.htm">首页</a> >
						<a href="contractlist.htm">合同管理</a> >
						<a href="javascript:void(0);">合同类型</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			 <div class="wrap">
            	<fieldset class="forms-area" id="search-area">
                	<h4>
						<span>高级检索</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content" style="margin:0 auto;">
						<ul style="padding:15px 30px">
							<span style="padding-left:20px">
							<form action="fullTextSearch.htm" method="post" id="cont-form" class="add-form">
							<table>
								<tr id="sty8">
									<td>请输入关键字</td>
									<td><input type="text" name="condition" /></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input id="btn-submit" type="submit" name="submit" value="搜索" /></td>
								</tr>
							
							</table>
				</fieldset>
			</div>
	</body>
</html>
<script>
function add(){
	if($.trim($("#contractTypeName").val())==''){
		alert("类型名称不能为空!");
		return false;
	}
	if(confirm("是否确认添加?")){
		$.ajax({
			type : "post",
			url : "addContractType.htm",
			dataType : "json",
			data : $("#typeForm").serialize(),
			success : function(data) { //成功后返回的页面
				alert(data.info);
				if (data.status == '1'){
					window.location.href=window.location;
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
			}
		});
	}
}
		
</script>
