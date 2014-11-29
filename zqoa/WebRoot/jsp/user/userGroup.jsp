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
						<a href="listUser.htm">人事管理</a> >
						<a href="javascript:void(0);">用户组管理</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						用户组管理
					</h4>
					<div class="list-form">
						<ec:table items="grouplist" var="row" width="90%" autoIncludeParameters="false" showPagination="false">

							<ec:row highlightRow="true">

								<ec:column property="identityName" style="height:35px;text-align:center" title="名称">
								<span style="display:inline;" >${row.groupName}</span>
								<span class="hidden-field"><input class="edit-box"
											type="text" name="type-name" />
										<input class="btn-edit-cate ui-button" type="button"
											value="提交" /> <input class="btn-cancle-edit ui-button"
											type="button" value="取消" /> <input type="hidden"
											name="identityId" value="${row.id}" /> </span>
								</ec:column>
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;" title="操作">
									<a href="editPowerInit.htm?powerId=${row.id}" >权限管理</a>|<a href="${row.id}" title="删除">删除</a>
								</ec:column>

							</ec:row>

						</ec:table>
						<form id="typeForm" action="" method="post">
							<div class="cate-info">
								<label for="name">
									用户组名称:
								</label>
								<input type="text" id="identityName" name="zqGroupModel.groupName" />
								<button type="button"  class="button gray" onclick="add()">添加</button>

							</div>
						</form>
				</fieldset>
			</div>
	</body>
</html>
<script>
function add(){
	if($.trim($("#identityName").val())==''){
		alert("用户组名称不能为空!");
		return false;
	}
	if(confirm("是否确认添加?")){
		$.ajax({
			type : "post",
			url : "addUserGroup.htm",
			dataType : "json",
			data : $("#typeForm").serialize(),
			success : function(data) { //成功后返回的页面
				alert(data.info);
				if (data.status == '1'){
					window.location.reload();
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
			}
		});
	}
}
$(document).ready(
		function(){
			
			$("a[title=删除]").click(function(){
				var contractTypeId = $(this).attr("href");
				if(contractTypeId==1){
					alert("管理员用户组不能删除!");
					return false;
				}
				if(confirm("删除后将无法恢复，是否确认?")){
					$.ajax({
						type : "post",
						url : "deltUserGroup.htm",
						dataType : "json",
						data : {
							powerId:contractTypeId
						},
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
				//window.location.href=window.location;
				return false;
			});
		}
		);
</script>
