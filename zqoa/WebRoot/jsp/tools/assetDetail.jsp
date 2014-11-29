<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>资产详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />

	</head>

	<body>
		<div class="toolbar">
			<p class="toolbar-shadow">
				&nbsp;
			</p>
			<div id="toolbar-content">
				<p class="crumb">
					<b>您的位置：</b>
					<a href="/zqoa/index.php/toIndex.htm">首页</a> >


					<a href="/zqoa/index.php/Customer/assetsList.htm">资产管理</a> >
					<a href="javascript:void(0);">查看资产详情</a>
				</p>

			</div>
			<a id="toolbar-opt" class="option eject-blue"
				href="javascript:void(0);return false;">&nbsp;</a>
		</div>
		<!--guide end-->

		<div class="wrap">
			<fieldset class="forms-area">
				<h4>
					资产信息
				</h4>
				<form id="add-form" action=""
					method="post" class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									资产编号：
								</th>
								<td>
									<s:property value="asset.sn" />
								</td>
							</tr>
							<tr>
								<th>
									资产名称：
								</th>
								<td>

									<s:property value="asset.assetName" />
								</td>
							</tr>
							<tr>
								<th>
								资产类型：
								</th>
								<td>
									<s:property value="asset.typeName" />
								</td>
							</tr>
							<tr>
								<th>
									入库时间：
								</th>
								<td>
									<s:date name="asset.inTime" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<th>
									出库时间：
								</th>
								<td>
									<s:date name="asset.outTime" format="yyyy-MM-dd"/>
								</td>
							</tr>
							
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="btn-submit" type="button" name="back" onclick="returnBack();" value="返回" />
								</td>
							</tr>

						</table>
					
				</form>
			</fieldset>
	</body>
</html>
<script>
function returnBack(){
	window.location.href='assetsList.htm';
}
</script>
