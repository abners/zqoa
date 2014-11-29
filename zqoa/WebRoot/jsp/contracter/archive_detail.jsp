<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>合同详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link href="<%=path%>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=path%>/css/contract.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/jquery-ui-1.8.11.custom.css"/>
		<script type="text/javascript" src="<%=path%>/js/tablecloth.js"></script>
		<script type="text/javascript" src="<%=path%>/js/contract.js"></script>

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

						
						<script type="text/javascript">
						$(document).ready(function() {
							$(".btn-h28").button();
							});
						</script>
						<a href="listArchives.htm">档案管理</a> ><a href="javascript:void(0);">档案详情</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						档案详情
					</h4>
					<form id="list-form" class="add-form">
						<table class="cont-detail-list">
							<tr>
								<th>
									客户名称：
								</th>
								<td>
									<s:property value="contMess.custName"/>
								</td>
							</tr>
							<tr>
								<th>
									合同编号：
								</th>
								<td>
									<s:property value="contMess.number"/>
								</td>
							</tr>
							<tr>
								<th>
									合同名称：
								</th>
								<td>
									<s:property value="contMess.contName"/>
								</td>
							</tr>
							<th>
								合同附件：
							</th>
							<td>
								<s:iterator value="contMess.zqFileModels">
								<li>
									<a href="dowloadFile.htm?fileId=<s:property value="id"/>"
										rel="pdf" title="<s:property value="ywjm"/>" target="_blank"><s:property value="ywjm"/></a>
								</li>
							</s:iterator>
							</td>
							<tr>
								<th>
									合同类型：
								</th>
								<td>
									<s:property value="contMess.contTypeName"/>
								</td>
							</tr>
							<tr>
								<th>
									是否风险代理：
								</th>
								<td>
									<s:if test="contMess.risk==1">是</s:if><s:if test="contMess.risk==0">是</s:if>
								</td>
							</tr>
							<tr>
								<th>
									执行时间：
								</th>
								<td>
									<s:property value="contMess.executeStartTime"/>至<s:property value="contMess.executeEndTime"/>
								</td>
							</tr>
							<tr>
								<th>
									收费：
								</th>
								<td>
									<table class="pay-phase">
										<tr>
											<th>
												应收费阶段
											</th>
											<th>
												应收费时间
											</th>
											<th>
												应收费金额
											</th>
											<th>
												实收费时间
											</th>
											<th>
												实收费金额
											</th>
											<th>
												执行状态
											</th>
										</tr>
										<s:iterator value="chargetStage">
										<tr>
											<td>
												<s:property value="chargetStage.index"/>
											</td>
											<td>
												<s:property value="chargeTime"/>
											</td>
											<td>
												<s:property value="chargeMoney"/>
											</td>
											<td>
												<s:property value="realChargeTime"/>
											</td>
											<td>
												<s:property value="realChargeMoney"/>
											</td>
											<td>
												<s:if test="ispaid==1">已支付</s:if><s:else>未支付</s:else>
											</td>
										</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr>
								<th>
									负责律师：
								</th>
								<td>
									<s:property value="contMess.lawyerName"/>
								</td>
							</tr>
							<tr>
								<th>
									协办律师：
								</th>
								<td>
									<ul class="entries-list">
										<s:iterator value="coscus">
											<li>
											<s:property value="lawyerName"/>
											</li>
										</s:iterator>
									</ul>
								</td>
							</tr>
							
							<tr>
								<th>
									备注：
								</th>
								<td><s:property value="contMess.notes"/></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<table>
										<tr>
											<td>
												<a class="btn-h28 ui-button"
													href="listArchives.htm">返回档案列表</a>
											</td>
											
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
					</form>
				</fieldset>
			</div>
		</div>

	</body>
</html>
