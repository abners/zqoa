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

		<title>添加合同</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/contract.css" />
		<link href="<%=path%>/css/jquery-ui-1.8.11.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path%>/js/addcontract.js"></script>
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
						<a href="toIndex.htm">首页</a> >
						<a href="listArchives.htm">档案管理</a> >
						<a href="javascript:void(0);">添加档案</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<%--<div class="guide">
				<ul>
					<li class="steps">
						<h3>
							步骤一
						</h3>
						<p>
							添加客户
						</p>
					</li>
					<li class="arrow back-arrow">
						&nbsp;
					</li>
					<li class="curt-step">
						<h3>
							步骤二
						</h3>
						<p>
							添加合同
						</p>
					</li>
					<li class="arrow curt-arrow">
						&nbsp;
					</li>
					<li class="steps">
						<h3>
							步骤三
						</h3>
						<p>
							添加案件
						</p>
					</li>
					<li class="arrow">
						&nbsp;
					</li>
				</ul>
			</div>
			<!--guide end-->
			--%><div class="wrap">
				<fieldset id="step2-add-cont" class="forms-area">
					<h4>
						合同信息
					</h4>
					<form action="" method="post" id="cont-form" class="add-form"
						enctype="multipart/form-data">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									合同编号：
								</th>
								<td>
									<input type="text" name="zqContractModel.number" id="number"/>
								</td>
							</tr>
							<tr>
								<th>
									合同名称：
								</th>
								<td>
									<input type="text" name="zqContractModel.contName" id="contName1"/>
								</td>
							</tr>
							<tr>
								<th>
									合同类型：
								</th>
								<td>
									<select name="zqContractModel.typeId" id="typeId">
										
									<s:iterator value="conttype">
										<option value="<s:property value="contractTypeId"/>"><s:property value="contractTypeName"/></option>
									</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									合同客户：
								</th>
								<td>
									<select name="zqContractModel.custId" id="custId">
									<option value="-1">--请选择--</option>
									<s:iterator value="cust">
										<option value="<s:property value="id"/>"><s:property value="name"/></option>
									</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									是否风险代理：
								</th>
								<td>
									<select name="zqContractModel.risk">
										<option value="0">
											否
										</option>
										<option value="1">
											是
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									负责律师：
								</th>
								<td>
									<select name="zqContractModel.lawyer" id="lawyer">
									<s:iterator value="users">
										<option value="<s:property value="id"/>"><s:property value="name"/></option>
									</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									协办律师：
								</th>
								<td>
									<ul class='entries-list' id="lawyers-list">
									</ul>
									<input id="btn-sub-lawyer" class="ui-button btn-h28 btn-relat" type="button" value="选择律师" />
								</td>
							</tr>
							
							<tr>
								<th>
									执行时间：
								</th>
								<td>
									<table>
										<tr>
											<td>
												<input type="text" readonly name="zqContractModel.executeStartTime" id="starttime" onclick="WdatePicker({el:'starttime'})" class="validity" />
											</td>
											<td>
												至
											</td>
											<td>
												<input type="text" readonly name="zqContractModel.executeEndTime" id="endtime" onclick="WdatePicker({el:'endtime'})" class="validity" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th>
									收费：
								</th>
								<td>
									<table class="pay-phase">
										<thead>
											<tr>
												<th class="w60">
													收费阶段
												</th>
												<th>
													应收费时间
												</th>
												<th>
													应收费金额
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="w60">
													1
												</td>
												<td>
													<input type="text" name="payTime" id="payTime1" readonly onclick="WdatePicker({el:'payTime1'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay1"/>
												</td>
											</tr>
											<tr >
												<td class="w60">
													2
												</td>
												<td>
													<input type="text" name="payTime" id="payTime2" readonly onclick="WdatePicker({el:'payTime2'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay2"/>
												</td>
											</tr>
											<tr >
												<td class="w60">
													3
												</td>
												<td>
													<input type="text" name="payTime" id="payTime3" readonly onclick="WdatePicker({el:'payTime3'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay3"/>
												</td>
											</tr>
											<tr >
												<td class="w60">
													4
												</td>
												<td>
													<input type="text" name="payTime"  id="payTime4" readonly onclick="WdatePicker({el:'payTime4'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay4"/>
												</td>
											</tr>
										</tbody>
										<%--<tfoot>
											<tr>
												<td colspan="3">
													<input id="add-pay-phase" class="btn-h28" type="button"
														value="添加收费阶段" />
												</td>
											</tr>
										</tfoot>
									--%></table>
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<textarea name="zqContractModel.notes"></textarea>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="btn-add-cont" class="btn-submit" type="button"
										name="addCont" value="提交" />
								</td>
							</tr>
						</table>
					</form>
					<!-- private-cust-form end -->
					<%--<div title="提示信息" id="ajax-action-dialog"></div>
					<div id="cont-dialog-form" title="选择关联合同">
						<h2>
							数据库里还没添加合同！
						</h2>

						<table class="hidden-conts" id="hidden-conts">
							<thead>
								<tr class="tb-header">
									<th>
										合同名称
									</th>
									<th>
										客户名称
									</th>
									<th>
										选择
									</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<!-- dialog-form end-->
					
				--%></fieldset>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//选择协办律师
	$("#btn-sub-lawyer").click(function(){
		openwindow("getCo_counsel.htm","",700,400);
		
	});
	//取消选择
	$("#lawyers-list a[title=取消]").live("click",function(){
		$(this).parent().remove();
	});
	$("#btn-add-cont").click(function(){
		//alert($("input[name=lawyerId]").val());
		if($.trim($('#number').val())==''){
			alert("请填写合同编号!");
			$('#number').focus();
			return ;
		}
		if($.trim($('#contName1').val())==''){
			alert("请填写合同名称!");
			$('#contName1').focus();
			return ;
		}
		if($("#custId").val()=='-1'){
			alert("请为该合同选择客户!");
			return ;
		}
		if($("#starttime").val()==''){
			alert("合同起始执行时间不能为空!");
			$("#starttime").focus();
			return ;
		}
		if($("#custId").val()!='-1'){
			$("#cont-form").append("<input type='hidden' name='zqContractModel.custName' value='"+$("#custId option:selected").text()+"' />");
		}
		$("#cont-form").append("<input type='hidden' name='zqContractModel.contTypeName' value='"+$("#typeId option:selected").text()+"' />");
		$("#cont-form").append("<input type='hidden' name='zqContractModel.lawyerName' value='"+$("#lawyer option:selected").text()+"' />");
		//if($.trim(''))
		if(confirm("是否确认添加该档案?")){
			$.ajax({
				type : "post",
				url : "addArchive.htm",
				dataType : "json",
				data : $("#cont-form").serialize(),
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("添加失败，请稍后重试!");
					}else if(data.status== '2'){
						alert("合同编号已存在，请更换!");
					}
					else {
						alert("档案添加成功!");
						window.location.href=window.location;
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
		
	});
});

</script>