<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户列表</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<link href="<%=request.getContextPath() %>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=request.getContextPath() %>/css/contract.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>		
		<script type="text/javascript" src="<%=path%>/js/extremecomponents.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>

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
						<a href="/zqoa/index.php/Contract/index/listUser.htm">人事管理</a> >
						<a href="javascript:void(0);">用户列表</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						人事管理
					</h4>
					<div class="list-form">
					<ec:table items="userlist" var="row"
							retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback"
							action="listUser.htm" width="100%">

							<ec:row highlightRow="true">

								<ec:column property="number" style="height:35px;width=18%;text-align:center" title="姓名">
								<a href="userDetail.htm?userId=${row.id}" title="查看用户详细信息">${row.name}</a>
								</ec:column>
								<ec:column property="caseName" style="height:35px;width=30%;" title="邮箱">${row.email}</ec:column>
								<ec:column property="currentMess" style="height:35px;width=10%;text-align:center" title="是否当前"></ec:column>
								<ec:column property="groupName" style="height:35px;width=20%;text-align:center" title="所在用户组"></ec:column>
								
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;width=25%;" title="操作">
									<input type="hidden" name="isCurrent" value="${row.isCurrent}"/>
									<s:if test="%{(#session.user.powers).indexOf('B6')>=0}"><a href="modifyUserMessInit.htm?userId=${row.id}" title="修改">修改</a></s:if>|<s:if test="%{(#session.user.powers).indexOf('B3')>=0}"><a href="${row.id}" title="停用">停用</a>|<a href="${row.id}" title="启用">启用</a></s:if>
								</ec:column>

							</ec:row>

						</ec:table>
					</div>	
				</fieldset>
				
				
			</div>
		</div>

	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test="%{(#session.user.powers).indexOf('B6')>=0}">
	$("a[title=停用]").click(function(){
		var iscurrent = $(this).parent().find("input[name=isCurrent]").val();
		if(iscurrent==0){
			alert("该账户已经停用!");
			return false;
		}
		if(confirm("是否确认停用该账号?")){
			$.ajax({
				type : "post",
				url : "closeAccount.htm",
				dataType : "json",
				data : {
					userId:$(this).attr("href")
				},
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("操作失败，请稍后重试!");
					} else {
						alert("操作成功!");
						window.location.reload();
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
					
				}
			});
		}
		return false;
	});
	$("a[title=启用]").click(function(){
		var iscurrent = $(this).parent().find("input[name=isCurrent]").val();
		if(iscurrent==1){
			alert("该账户已经启用!");
			return false;
		}
		if(confirm("是否确认启用该账号?")){
			$.ajax({
				type : "post",
				url : "openAccount.htm",
				dataType : "json",
				data : {
					userId:$(this).attr("href")
				},
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("操作失败，请稍后重试!");
					} else {
						alert("操作成功!");
						window.location.reload();
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
					
				}
			});
		}
		return false;
	});
	</s:if>
	<s:if test="%{(#session.user.powers).indexOf('B3')>=0}">
	
	</s:if>
});

</script>
