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

		<title>修改合同信息</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/contract.css" />
		<link href="<%=path%>/css/jquery-ui-1.8.11.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
		
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
$(document).ready(function(){
	$("#typeId").val('<s:property value="contMess.typeId"/>');
	$("#custId").val('<s:property value="contMess.custId"/>');
	$("#risk").val('<s:property value="contMess.risk"/>');
	$("#lawyer").val('<s:property value="contMess.lawyer"/>');
});
</script>
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
						<a href="contractlist.htm">合同管理</a> >
						<a href="javascript:void(0);">修改合同</a>
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
						合同信息修改
					</h4>
					<form action="" method="post" id="cont-form" class="add-form"
						enctype="multipart/form-data">
						<input type="hidden" name="zqContractModel.id" value="<s:property value="contMess.id"/>"/>
						<input type="hidden" name="zqContractModel.creater" value="<s:property value="contMess.creater"/>"/>
						<input type="hidden" name="zqContractModel.createTime" value="<s:property value="contMess.createTime"/>"/>
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									合同编号：
								</th>
								<td>
									<input type="text" name="zqContractModel.number" value="<s:property value="contMess.number"/>" id="number"/>
								</td>
							</tr>
							<tr>
								<th>
									合同名称：
								</th>
								<td>
									<input type="text" name="zqContractModel.contName" value="<s:property value="contMess.contName"/>" id="contName1"/>
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
								<th>合同附件:</th>
								<td>
								<s:iterator value="contMess.zqFileModels">
								<li>
									<a href="dowloadFile.htm?fileId=<s:property value="id"/>"
										rel="pdf" title="<s:property value="ywjm"/>" target="_blank"><s:property value="ywjm"/></a><a href=""></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:property value="id"/>" title="删除" >删除</a>
								</li>
								</s:iterator>
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
										<option value="<s:property value="id"/>"><s:property value="name" /></option>
									</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									是否风险代理：
								</th>
								<td>
									<select name="zqContractModel.risk" id="risk">
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
										<s:iterator value="coscus">
											<li>
											<s:property value="lawyerName"/><a href="javascript:void(0);" title="取消">取消</a>
											<input type="hidden" name="coscusid" value="<s:property value="id"/>"/>
											</li>
										</s:iterator>
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
												<input type="text" readonly name="zqContractModel.executeStartTime" value="<s:property value="contMess.executeStartTime"/>" id="starttime" onclick="WdatePicker({el:'starttime'})" class="validity" />
											</td>
											<td>
												至
											</td>
											<td>
												<input type="text" readonly name="zqContractModel.executeEndTime" value="<s:property value="contMess.executeEndTime"/>" id="endtime" onclick="WdatePicker({el:'endtime'})" class="validity" />
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
									<table class="pay-phase" id="stage">
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
												<th>
													操作
												</th>
											</tr>
										</thead>
										<tbody>
										<s:iterator value="chargestage" status="ch">
											<tr>
												<td class="w60">
													<s:property value="#ch.index+1"/>
												</td>
												<td>
													<input type="text" name="payTime" id="payTime<s:property value="#ch.index+1"/>" value="<s:property value="chargeTime"/>" readonly onclick="WdatePicker({el:'payTime<s:property value="#ch.index+1"/>'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay<s:property value="#ch.index+1"/>" value="<s:property value="chargeMoney"/>"/>
												</td>
												<td>
												<input type="hidden" name="chargeId" value="<s:property value="id"/>"/>
												<a href="javascript:void(0)" title="清空">清空</a>
												</td>
											</tr>
									     </s:iterator>
									     <s:if test="chargestage.size==0">
									     	<tr >
												<td class="w60">
													1
												</td>
												<td>
													<input type="text" name="payTime" id="payTime1" readonly onclick="WdatePicker({el:'payTime1'})" class="validity"/>
												</td>
												<td>
													<input type="text" name="pay" id="pay1"/>
												</td>
												<td><a href="javascript:void(0)" title="清空">清空</a></td>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
												</td>
											</tr>
									     </s:if>
									     <s:if test="chargestage.size==1">
									     	
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
												</td>
											</tr>
									     </s:if>
									     <s:if test="chargestage.size==2">
									     	
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
												</td>
											</tr>
									     </s:if>
									     <s:if test="chargestage.size==3">
											
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
												<td>
												<a href="javascript:void(0)" title="清空">清空</a>
												</td>
											</tr>
									     </s:if>
											
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
		//删除数据记录
		if($(this).parent().find("input[name=coscusid]").val()!=undefined){
			var parentNode = $(this).parent();
			$.ajax({
				type : "post",
				url : "deltCoscus.htm",
				dataType : "json",
				data : {
					coscusid:$(parentNode).find("input[name=coscusid]").val()
				},
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						$(parentNode).append("<font color='red'>取消失败请重试!</font>");
					} else {
						$(parentNode).remove();
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
					
				}
			});
		}else{
			$(this).parent().remove();
		}
		
	});
	$("a[title=清空]").click(function(){
		$(this).parent("td").prev().find("input[name=pay]").val("");
		$(this).parent("td").prev().prev().find("input[name=payTime]").val("");
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
		if($("#custId").val()!='-1'){
			//防止出现重复input，判断是否已提交过，一下均同
			if($("#custName").val()!=undefined){
				$("#custName").val($("#custId option:selected").text());
			}else{
				$("#cont-form").append("<input type='hidden' name='zqContractModel.custName' id='custName' value='"+$("#custId option:selected").text()+"' />");
			}
		}else{
			if($("#custName").val()!=undefined){
				$("#custName").val("");
			}else{
				$("#cont-form").append("<input type='hidden' name='zqContractModel.custName' id='custName' value='' />");
			}
		}
		
		if($("#contTypeName").val()!=undefined){
			$("#contTypeName").val($("#typeId option:selected").text());
		}else{
			$("#cont-form").append("<input type='hidden' name='zqContractModel.contTypeName' id='contTypeName' value='"+$("#typeId option:selected").text()+"' />");
		}
		if($("#lawyerName").val()!=undefined){
			$("#lawyerName").val($("#lawyer option:selected").text());
		}else{
			$("#cont-form").append("<input type='hidden' name='zqContractModel.lawyerName' id='lawyerName' value='"+$("#lawyer option:selected").text()+"' />");
		}
		//if($.trim(''))
		if(confirm("请检查所做修改，是否确认?")){
			$.ajax({
				type : "post",
				url : "updateContract.htm",
				dataType : "json",
				data : $("#cont-form").serialize(),
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("修改失败，请稍后重试!");
					} else {
						alert("合同修改成功!");
						window.location.href="<%=basePath%>contractlist.htm";
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
		
	});
	$("a[title=删除]").click(function(){
		if(confirm("删除后将无法恢复，是否确认?")){
			$.ajax({
				type : "post",
				url : "deltContFile.htm",
				dataType : "json",
				data : {fileId:$(this).attr("href")},
				success : function(data) { //成功后返回的页面
					if (data.status == '0') {
						alert("删除失败，请稍后重试!");
					} else {
						alert("合同附件删除成功!");
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
});

</script>