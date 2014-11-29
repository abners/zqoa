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

						<a href="toolsList.htm">工具管理</a> >
						<a href="javascript:void(0);">查看工具</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						工具列表
					</h4>
					<div class="list-form">
						<ec:table items="toolsList" var="row"
							action="toolsList.htm" width="90%">

							<ec:row highlightRow="true">

								<ec:column property="name" title="名称">
									
								</ec:column>

								<ec:column property="typeName" escapeAutoFormat="true" style="text-align:center;"
									title="类型">
								</ec:column>

								<ec:column property="address" title="地址"
									style="text-align:left;" ><a href="${row.address }" target="_blank" >${row.address}</a></ec:column>
								<ec:column property="f" title="附件"
									style="text-align:center;" ><a href="dowloadFile.htm?fileId=${row.fileId}" target="_blank">${row.ywjm }</a></ec:column>	
								<ec:column property="caozuo" style="text-align:center;"
									title="操作">
									<a href="modifyToolsInit.htm?toolsId=${row.id}">修改</a>|<a href="javascript:void(0);" onclick="deltCust('${row.id}')" target="_self">删除</a>
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
			url : "deltTools.htm",
			dataType : "json",
			data : {toolsId:custid},
			success : function(data) { //成功后返回的页面
				
				if(data.result=='1'){
					alert("操作成功!");
					window.location.href=window.location;
				}else{
					alert("操作失败!");
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
			}
		});
	}
}

</script>
