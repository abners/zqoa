<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=path%>/css/tablecloth.css" rel="stylesheet" />
<link href="<%=path%>/css/index.css" rel="stylesheet" />
<link href="<%=path%>/css/main.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/tablecloth.js"></script>
<script type="text/javascript" src="<%=path%>/js/index.js"></script>

</head>
<body style='overflow: scroll; overflow-x: hidden'>
	<div id="main">
		<div class="toolbar">
			<p class="toolbar-shadow">&nbsp;</p>
			<div id="toolbar-content">
				<p class="crumb">
					<b>您的位置：</b> <a href="toIndex.htm">首页</a> ><a
						href="javascript:void(0);">站内搜索</a>>

				</p>

			</div>
			<a id="toolbar-opt" class="option eject-blue"
				href="javascript:void(0);return false;">&nbsp;</a>
		</div>
		<!-- toolbar end-->
		<div class="wrap">
			<fieldset class="forms-area" id="notices-area">
				<h4>
					<span>站内搜索</span> <a class="btn-toggle" href="javascript:void(0);"
						title="展开/收缩"></a>
				</h4>
				<form id="searcher" action="fullSearch.htm" method="post">
				<div class="content" style="margin: 0 auto;">
					<ul style="padding: 15px 30px">
						<span style="padding-left: 20px">
							<table>
								<tr>
									<th>请输入内容：</th>
									<td><input name="searchContent" id="searchContent"
										class="" />&nbsp;&nbsp;<input id="searchNeirong"
										class="btn-submit" type="button" value="搜索" /></td>
								</tr>
							</table>
						</span>
					</ul>
				</div>
				</form>

			</fieldset>

			<fieldset class="forms-area" id="work-area">
				<h4>
					<span>未结案件</span> <a class="btn-toggle" href="javascript:void(0);"
						title="展开/收缩"></a>
				</h4>
				<div class="content work-ct">
					<s:if test="index.zqNoOverCaseModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办案件记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">案件编号</th>
								<th>案件名称</th>
								<th class="w180">案件类型</th>
								<th class="w180">主办律师</th>
							</tr>
							<s:iterator value="index.zqNoOverCaseModels">
								<tr>
									<td><a class="detail-link"
										href="<s:if test="contId!=null">caseDetail.htm</s:if><s:else>caseBeforeContDetail.htm</s:else>?caseId=<s:property value="id"/>"
										title=""><s:property value="number" /></a></td>
									<td><s:property value="caseName" /></td>
									<td><s:property value="typeName" /></td>
									<td><s:property value="userName" /></td>

								</tr>
							</s:iterator>
						</table>
					</s:else>
				</div>
			</fieldset>
			<!--work-area end-->
			<fieldset class="forms-area" id="work-area">
				<h4>
					<span>已结案件</span> <a class="btn-toggle" href="javascript:void(0);"
						title="展开/收缩"></a>
				</h4>
				<div class="content work-ct">
					<s:if test="index.zqOverCaseModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办案件记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">案件编号</th>
								<th>案件名称</th>
								<th class="w180">案件类型</th>
								<th class="w180">主办律师</th>
							</tr>
							<s:iterator value="index.zqOverCaseModels">
								<tr>
									<td><a class="detail-link"
										href="<s:if test="contId!=null">caseDetail.htm</s:if><s:else>caseBeforeContDetail.htm</s:else>?caseId=<s:property value="id"/>"
										title=""><s:property value="number" /></a></td>
									<td><s:property value="caseName" /></td>
									<td><s:property value="typeName" /></td>
									<td><s:property value="userName" /></td>

								</tr>
							</s:iterator>
						</table>
					</s:else>
				</div>
			</fieldset>
			<!--work-area end-->

			<fieldset class="forms-area" id="work-area">
				<h4>
					<span>未结合同</span> <a class="btn-toggle" href="javascript:void(0);"
						title="展开/收缩"></a>
				</h4>
				<div class="content work-ct">
					<s:if test="index.zqNoOverContractModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办合同记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">合同编号</th>
								<th>合同名称</th>
								<th class="w180">客户名称</th>
								<th class="w180">主办律师</th>
								<%--<th class="w60">操作</th>
							--%>
							</tr>
							<s:iterator value="index.zqNoOverContractModels">
								<tr>
									<td><a class="detail-link"
										href="contractDetail.htm?contId=<s:property value="id"/>"
										title=""><s:property value="number" /></a></td>
									<td><s:property value="contName" /></td>
									<td><s:property value="custName" /></td>
									<td><s:property value="lawyerName" /></td>

								</tr>
							</s:iterator>
						</table>
					</s:else>
				</div>
			</fieldset>
			<!--work-area end-->
			<fieldset class="forms-area" id="work-area">
				<h4>
					<span>已结合同</span> <a class="btn-toggle" href="javascript:void(0);"
						title="展开/收缩"></a>
				</h4>
				<div class="content work-ct">
					<s:if test="index.zqOverContractModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办合同记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">合同编号</th>
								<th>合同名称</th>
								<th class="w180">客户名称</th>
								<th class="w180">主办律师</th>
								<%--<th class="w60">操作</th>
							--%>
							</tr>
							<s:iterator value="index.zqOverContractModels">
								<tr>
									<td><a class="detail-link"
										href="contractDetail.htm?contId=<s:property value="id"/>"
										title=""><s:property value="number" /></a></td>
									<td><s:property value="contName" /></td>
									<td><s:property value="custName" /></td>
									<td><s:property value="lawyerName" /></td>

								</tr>
							</s:iterator>
						</table>
					</s:else>
				</div>
			</fieldset>
			<!--work-area end-->
		</div>
		<!--wrap end-->
	</div>
	<!-- main-->
	<div class="clean"></div>
	</div>
	<!--content end-->
</body>
<script>
	$(document).ready(function() {
		$("#searchNeirong").click(function() {
			if ($.trim($("#searchContent").val())==''){
				
			}else{
				$("#searcher").submit();
			}
		});
	});
</script>
</html>