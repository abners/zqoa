<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>客户列表</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/extremecomponents.js"></script>

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

						<a href="/zqoa/index.php/Customer/listCustomer.htm">客户管理</a> >
						<a href="javascript:void(0);">查看客户</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						客户列表
					</h4>
					<div class="list-form">
						<ec:table items="zqCustomerModels" var="row"
							retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback" 
							action="listCustomer.htm" width="90%">

							<ec:row highlightRow="true">

								<ec:column property="name" title="客户名称">
									<a href="custDetail.htm?cust_id=${row.id}" title="查看详情">${row.name}</a>
								</ec:column>

								<ec:column property="cust_typeValue" escapeAutoFormat="true" style="text-align:center;"
									title="客户类型">
								</ec:column>

								<ec:column property="tel" title="联系电话"
									style="text-align:center;" />

								<ec:column property="caozuo" style="text-align:center;"
									title="操作">
									<a href="modifyCustInit.htm?cust_id=${row.id}">修改</a>|<a href="javascript:void(0);" onclick="deltCust('${row.id}')" target="_self">删除</a>|<a href="javascript:void(0);" onclick="makeExpired('${row.id}')">归入过期</a>
								</ec:column>

							</ec:row>

						</ec:table>
					</div>
				</fieldset>
	</body>
</html>
<script type="text/javascript">
function deltCust(custid){
	if(confirm("删除后将无法恢复，是否确认?")){
		$.ajax({
			type : "post",
			url : "deltCustomer.htm",
			dataType : "json",
			data : {cust_id:custid},
			success : function(data) { //成功后返回的页面
				alert(data.info);
				if(data.status=='1'){
					window.location.href=window.location;
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
			}
		});
	}
}
function makeExpired(cust_id){
	if(confirm("是否确认要归入过期用户中?")){
		$.ajax({
			type : "post",
			url : "expiredCustomer.htm",
			dataType : "json",
			data : {cust_id:cust_id},
			success : function(data) { //成功后返回的页面
				if (data.status == '0') {
					alert(data.info);
				} else {
					alert("归入过期成功!");
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
