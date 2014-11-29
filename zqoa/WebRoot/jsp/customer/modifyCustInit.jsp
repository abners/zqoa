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

		<title>添加客户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link href="<%=path %>/css/base.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/addContract.css"/>
		<link href="<%=path %>/css/jquery-ui-1.8.11.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<script type="text/javascript" src="<%=path%>/js/behavious.js"></script>
		<script type="text/javascript" src="<%=path%>/js/addcontract.js"></script>
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

						<a href="/zqoa/index.php/Customer/listCustomer.htm">客户管理</a> >
						<a href="javascript:void(0);">修改客户信息</a>
					</p>
				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="guide">
				<ul>
					<li class="curt-step">
						<h3>
							步骤一
						</h3>
						<p>
							修改客户信息
						</p>
					</li>
					<li class="arrow curt-arrow">
						&nbsp;
					</li>
					<li class="steps">
						<h3>
							步骤二
						</h3>
						<p>
							确认修改
						</p>
					</li>
					<li class="arrow">
						&nbsp;
					</li>

				</ul>
			</div>
			<!--guide end-->
			<div class="wrap">
				<fieldset class="forms-area">
					<h4>
						客户信息修改
					</h4>
					<!--select-cust-type end-->
					<form id="modify" action="/zqoa/index.php/customer/updateExec/id/219" method="post" class="add-form">
					<input type="hidden" name="zqCustomerModel.id" value="<s:property value="cust_mess.id"/>"/>
					<input type="hidden" name="zqCustomerModel.isnow" value="<s:property value="cust_mess.isnow"/>"/>
					<s:if test="cust_mess.cust_type==4">
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	<tr>
                            	<th>姓名：</th>
                                <td><input type="text" id="name" name="zqCustomerModel.name" value="<s:property value="cust_mess.name"/>"/></td>
                            </tr>
                            <tr>
                            	<th>性别：</th>
                                <td>
                                	<select name="zqCustomerModel.sex">
                                    	<option value="0" <s:if test="cust_mess.sex==0">selected</s:if>>男</option>
                                        <option value="1" <s:if test="cust_mess.sex==1">selected</s:if>>女</option>
                                    </select>
                                </td>
                            </tr>
							<tr>
							<td><input type="hidden" name="zqCustomerModel.cust_type" value="<s:property value="cust_mess.cust_type"/>"></td>
							</tr>
                            <tr>
                            	<th>出生日期：</th>
                                <td><input type="text" name="zqCustomerModel.birthday" id="birthday" value="<s:property value="cust_mess.birthday"/>"/></td>
                            </tr>
                            <tr>
                            	<th>民族：</th>
                                <td><input type="text" name="zqCustomerModel.race" value="<s:property value="cust_mess.race"/>"/></td>
                            </tr>
                            <tr>
                            	<th>现住址：</th>
                                <td><input type="text" name="zqCustomerModel.pressentAddr" value="<s:property value="cust_mess.pressentAddr"/>"/></td>
                            </tr>
                            <tr>
                            	<th>身份证号码：</th>
                                <td><input type="text" name="zqCustomerModel.idCard" value="<s:property value="cust_mess.idCard"/>"/></td>
                            </tr>
                            <tr>
                            	<th>通讯地址：</th>
                                <td><input type="text" name="zqCustomerModel.address" value="<s:property value="cust_mess.address"/>"/></td>
                            </tr>
                            <tr>
                            	<th>邮政编码：</th>
                                <td><input type="text" name="zqCustomerModel.postalcode" value="<s:property value="cust_mess.postalcode"/>"/></td>
                            </tr>
                            <tr>
                            	<th>电子邮箱：</th>
                                <td><input type="text" id="email" name="zqCustomerModel.email" value="<s:property value="cust_mess.email"/>"/></td>
                            </tr>
                            <tr>
                            	<th>电话：</th>
                                <td><input type="text" name="zqCustomerModel.tel" value="<s:property value="cust_mess.tel"/>"/></td>
                            </tr>
                            <tr>
                            	<th>手机：</th>
                                <td><input type="text" name="zqCustomerModel.mobile" value="<s:property value="cust_mess.mobile"/>"/></td>
                            </tr>
                            <tr>
                            	<th>其他联系方式：</th>
                                <td><input type="text" name="zqCustomerModel.otherTel" value="<s:property value="cust_mess.otherTel"/>"/></td>
                            </tr>
                            <tr>
                            	<th>备注：</th>
                                <td><textarea name="zqCustomerModel.notes"><s:property value="cust_mess.notes"/></textarea></td>
                            </tr>
							
                            <tr>
                            	<th>&nbsp;</th>
                                <td><input onclick="javascript:window.history.back();" class="btn-submit" type="button" name="update" value="取消" />&nbsp;<input id="update-btn-submit" class="btn-submit" type="button" name="update" value="更新" /></td>
                            </tr>
						
                        </table>
                        </s:if>
                        <s:else>
                        <table>
                   			<colgroup span="1"></colgroup>
							<tr>
							<td><input type="hidden" name="zqCustomerModel.cust_type" value="<s:property value="cust_mess.cust_type"/>"></td>
							</tr>
                        	<tr>
                            	<th>名称：</th>
                                <td><input type="text" id="name" name="zqCustomerModel.name" value="<s:property value="cust_mess.name"/>"/></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>住所地：</th>
                                <td><input type="text" name="zqCustomerModel.origo" value="<s:property value="cust_mess.origo"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>实地经营地：</th>
                                <td><input type="text" name="zqCustomerModel.pressentAddr" value="<s:property value="cust_mess.pressentAddr"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>法定代表人：</th>
                                <td><input type="text" name="zqCustomerModel.chairmen" value="<s:property value="cust_mess.chairmen"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>职务：</th>
                                <td><input type="text" name="zqCustomerModel.work" value="<s:property value="cust_mess.work"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>营业执照号码：</th>
                                <td><input type="text" name="zqCustomerModel.idCard" value="<s:property value="cust_mess.idCard"/>"/></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>组织机构代码：</th>
                                <td><input type="text" name="zqCustomerModel.orgNumber" value="<s:property value="cust_mess.orgNumber"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>通讯地址：</th>
                                <td><input type="text" name="zqCustomerModel.address" value="<s:property value="cust_mess.address"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>邮政编码：</th>
                                <td><input type="text" name="zqCustomerModel.postalcode" value="<s:property value="cust_mess.postalcode"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>指定联络人：</th>
                                <td><input type="text" name="zqCustomerModel.contacter" value="<s:property value="cust_mess.contacter"/>"/></td>
                           		<td></td>
                            </tr>
                            <tr>
                            	<th>电子邮箱：</th>
                                <td><input type="text" name="zqCustomerModel.email" value="<s:property value="cust_mess.email"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>电话：</th>
                                <td><input type="text" name="zqCustomerModel.tel" value="<s:property value="cust_mess.tel"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>手机：</th>
                                <td><input type="text" name="zqCustomerModel.mobile" value="<s:property value="cust_mess.mobile"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>其他联系方式：</th>
                                <td><input type="text" name="zqCustomerModel.otherTel"  value="<s:property value="cust_mess.otherTel"/>" /></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>备注：</th>
                                <td colspan="2"><textarea name="zqCustomerModel.notes"  ><s:property value="cust_mess.notes"/></textarea></td>
                            	<td></td>
                            </tr>
                            <tr>
                            	<th>&nbsp;</th>
                                <td><input onclick="javascript:window.history.back();" class="btn-submit" type="button" name="update" value="取消" />&nbsp;<input id="update-btn-submit" class="btn-submit" type="button" name="update" value="更新" /></td>
                            </tr>
                        </table>
                        </s:else>
                        </form>
                        <script type="text/javascript">
                        $(document)
            				.ready(
            					function() {
                        		var flag = true;
                        		$("#update-btn-submit").click(function(){
                        			if ($.trim($("#email").val()) != ''
										&& !isEmail($("#email").val())) {
                        				alert("电子邮箱格式不正确!");
                        				$("#email").focus();
                        				return false;
                        			}
                        			if($.trim($("#name").val())==''){
                        				alert("名称不能为空!");
                        				$("#name").focus();
                        				return false;
                        			}
                        			if(confirm("请检查所填信息，是否确认?")){
                        				$.ajax({
											type : "post",
											url : "modifyCust.htm",
											dataType : "json",
											data : $("#modify").serialize(),
											beforeSend : function() {
												$(
														"#ajax-action-dialog")
														.html(
																"<h2>请稍候...</h2><br /><img src=\"<%=path%>/images/waiting.gif\"} />")
														.dialog(
																"open");
											},
											success : function(data) { //成功后返回的页面
												$(
												"#ajax-action-dialog")
												.html(
														"<h2>请稍候...</h2><br /><img src=\"<%=path%>/images/waiting.gif\"} />")
												.dialog(
														"close");
												if (data.status == '0') {
													alert(data.info);
												} else {
													alert(data.info);
													window.location.href="listCustomer.htm";
												}
											},
											error:function(error){
												alert("服务器异常，请重新登录后重试!");
											}
										});
                        			}
                        			
                        		});
                        		
                        	});
                        </script>
				</fieldset>
			</div>
		</div>

	</body>
</html>
