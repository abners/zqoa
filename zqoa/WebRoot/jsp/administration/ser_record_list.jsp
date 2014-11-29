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

		<title>事物列表</title>

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
						<a href="javascript:void(0);">查看事物记录</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						事物记录列表
					</h4>
					<div class="list-form">
						<ec:table items="serRecList" var="row"
							action="listSerRecord.htm" width="90%" autoIncludeParameters="false" showPagination="false">

							<ec:row highlightRow="true">

								<ec:column property="authorName" style="height:35px;text-align:center" title="登记人">
								</ec:column>
								<ec:column property="createTime" style="height:35px;text-align:center" title="登记时间">
								</ec:column>
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;" title="操作">
									<a href="serRecordDetail.htm?asrId=${row.id}">查看详情</a>
								</ec:column>

							</ec:row>

						</ec:table>

				</fieldset>
			</div>
	</body>
</html>