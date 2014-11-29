<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>案件列表</title>

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
						<a href="toIndex.htm">首页</a> >
						<a href="listCaseBeforeCont.htm">合同前案件管理</a> >
						<a href="javascript:void(0);">合同前案件列表</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						案件管理
					</h4>
					<div class="list-form">
					<ec:table items="caselist" var="row"
							retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback"
							action="listCase.htm" width="100%">

							<ec:row highlightRow="true">

								<ec:column property="number" style="height:35px;width=18%;" title="案件编号">
								<span id="number" >${row.number}</span>
								</ec:column>
								<ec:column property="caseName" style="height:35px;width=30%;" title="案件名称"><span id="contName">${row.caseName}</span></ec:column>
								<ec:column property="typeName" style="text-align:center;height:35px;width=15%;" title="案件类型"/>
								<ec:column property="lawyerName" style="height:35px;width=20%;text-align:center;" title="负责律师"/>
								
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;width=15%;" title="操作">
									<a href="caseBeforeContDetail.htm?caseId=${row.id}">查看详情</a>|
									<s:if test="%{(#session.user.powers).indexOf('B6')>=0}"><a href="${row.id}" title="结案">结案</a></s:if>|<s:if test="%{(#session.user.powers).indexOf('B3')>=0}"><a href="${row.id}" title="删除">删除</a></s:if>
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
showTdTitle("contName",45);

$(document).ready(function(){
	<s:if test="%{(#session.user.powers).indexOf('B6')>=0}">
	$("a[title=结案]").click(function(){
		if(confirm("是否确认结案?")){
			$.ajax({
				type : "post",
				url : "closeCase.htm",
				dataType : "json",
				data : {
					caseId:$(this).attr("href")
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
	$("a[title=删除]").click(function(){
		if(confirm("删除后将无法恢复是否确认?")){
			$.ajax({
				type : "post",
				url : "deltCase.htm",
				dataType : "json",
				data : {
					caseId:$(this).attr("href")
				},
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("操作失败，请稍后重试!");
					} else {
						alert("删除成功!");
						window.location.href=window.location;
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
});

</script>
