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

		<title>收费登记</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link href="<%=path%>/css/main.css" rel="stylesheet" />
		<link href="<%=path%>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=path%>/css/contract.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
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
						<a href="/zqoa/index.php/">首页</a> >
						<a href="/zqoa/index.php/contract/index">合同管理</a> >
						<a href="/zqoa/index.php/contract/pay">收费登记</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						合同收费登记
					</h4>
					<form id="list-form" class="add-form">
					
						<table>
						<s:iterator value="chargestage" status="sta">
							<tr>
								<th>
									应收费时间<s:property value="#sta.index+1"/>：
								</th>
								<td>
									<s:property value="chargeTime"/>
								</td>
							</tr>
							<tr>
								<th>
									应收费金额<s:property value="#sta.index+1"/>：
								</th>
								<td>
									<s:property value="chargeMoney"/>
								</td>
							</tr>
							<s:if test="realChargeMoney<chargeMoney">
							<input type="hidden" name="chargeMoney" value="<s:property value="chargeMoney"/>"/>		
							<input type="hidden" name="chargeTime" value="<s:property value="chargeTime"/>"/>
							
							<tr>
								<th>
									实收费时间<s:property value="#sta.index+1"/>：
								</th>
								<td>
									<input type="text" name="payTime" value="<s:property value="realChargeTime"/>" id="payTime<s:property value="#sta.index+1"/>" class="validity" readonly="readonly" onclick="WdatePicker({el:'payTime<s:property value="#sta.index+1"/>'})"/>
								</td>
							</tr>
							<tr>
								<th>
									实收费金额<s:property value="#sta.index+1"/>：
								</th>
								<td>
									<input type="text" name="payMoney" id="payMoney<s:property value="#sta.index+1"/>" value="<s:property value="realChargeMoney"/>"/>
									<input type="hidden" name="chargeId" value="<s:property value="id"/>"/>
								</td>
							</tr>
							<tr>
								<th>
									执行情况：
								</th>
								<td>
									未支付
								</td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<th>
									实收费时间<s:property value="#sta.index+1"/>：
								</th>
								<td>
								<s:property value="chargeTime"/>
								</td>
							</tr>
							<tr>
								<th>
									实收费金额<s:property value="#sta.index+1"/>：
								</th>
								<td>
								<s:property value="chargeMoney"/>
								</td>
							</tr>
							<tr>
								<th>
									执行情况：
								</th>
								<td>
									已支付
								</td>
							</tr>
							</s:else>
							</s:iterator>
							<tr>
								<th>
									<input type="hidden" name="phase" value="1" />
								</th>
								<td>
									<input type="button" value="提交" name="submit" id="sub" />
								</td>
							</tr>
							<tr class="dash-line"></tr>
							<tr>
								<td>
									<input type="hidden" name="cid" value="336" id="txt-cid" />
								</td>
							</tr>
						</table>
					<input type="hidden" name="contId" value="<s:property value="contId"/>" />
					</form>
				</fieldset>
			</div>
		</div>
<script>
$(document).ready(function(){
	$("#sub").click(function(){
		var reg = /^[0-9]+(.[0-9]{1,2})?$/ ;
		var flag = true;
		var length = 0;
		$("input[name=payMoney]").each(function(){
			if($.trim($(this).val())!=''){
				if(!reg.test($(this).val())){
					alert("实收费金额必须为非负的数字,请检查您填写的内容!");
					flag = false;
					$(this).focus();
					return false;
				}
			}else{
				length++;
			}
		});
		//实收费金额校验通过
		if(flag){
			//用户没有填写任何内容
			if(length==$("input[name=payMoney]").length){
				alert("收费金额不能全为空!请至少填写一个再进行此操作!");
				return false;
			}else{
				if(confirm("是否确认提交?")){
					$.ajax({
						type : "post",
						url : "regFees.htm",
						dataType : "json",
						data : $("#list-form").serialize(),
						success : function(data) { //成功后返回的页面
							//alert(data.status);
							if (data.status == '0') {
								alert("收费登记失败，请稍后重试!");
							} else if(data.status == '2'){
								alert("所要登记的收费阶段不存在!");
							} else {
								alert("收费登记成功!");
								window.location.href="<%=basePath%>contractDetail.htm?contId=<s:property value="contId"/>";
							}
						},
						error:function(error){
							alert("网络异常，请重新登录!");
						}
					});
				}
			}
			
			
		}
		
	});
});
</script>
	</body>
</html>
