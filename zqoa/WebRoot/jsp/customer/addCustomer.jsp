<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加客户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<link href="<%=path %>/css/base.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/addContract.css"/>
		<link href="<%=path %>/css/jquery-ui-1.8.11.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<script
			src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
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
						<a href="toIndex.htm">首页</a> >

						<a href="listCustomer.htm">客户管理</a> >
						<a href="javascript:void(0);">添加客户</a>
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
							添加客户
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
							确认添加
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
						客户信息
					</h4>
					<div id="select-cust-type">
						<label for="cust-type">
							选择客户类型:
						</label>
						<select name="cust-type">
							<option value="0">
								请选择客户类型
							</option>
							<option value="1" title="4">
								自然人
							</option>
							<option value="2" title="5">
								法人
							</option>
							<option value="3" title="6">
								其他组织
							</option>
						</select>
					</div>
					<!--select-cust-type end-->
					<form action="#" method="post" name="private-cust-form"
						id="private-cust-form" class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									姓名：
								</th>
								<td>
									<input type="text" name="name" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									性别：
								</th>
								<td>
									<select name="sex" id="sex">
										<option value="0">
											男
										</option>
										<option value="1">
											女
										</option>
									</select>
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									出生日期：
								</th>
								<td>
									<input type="text" id="birthday" name="birthday" onclick="WdatePicker({el:'birthday'})" class="validity"/>
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									民族：
								</th>
								<td>
									<input type="text" name="race" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									籍贯：
								</th>
								<td>
									<input type="text" name="origo" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									职业：
								</th>
								<td>
									<input type="text" name="work" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									现住址：
								</th>
								<td>
									<input type="text" name="present_addr" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									身份证号码：
								</th>
								<td>
									<input type="text" name="id_card" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<input type="text" name="address" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<input type="text" name="postalcode" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<input type="text" name="email" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<input type="text" name="tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<input type="text" name="mobile" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<input type="text" name="other_tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<textarea name="notes"></textarea>
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="private-submit" class="btn-submit" type="button"
										name="submit" value="提交" />
								</td>
							</tr>
						</table>
					</form>
					<!-- private-cust-form end -->
					<form action="#" method="post" id="legal-cust-form"
						class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									名称：
								</th>
								<td>
									<input type="text" name="name" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									住所地：
								</th>
								<td>
									<input type="text" name="origo" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									实地经营地：
								</th>
								<td>
									<input type="text" name="present_addr" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									法定代表人：
								</th>
								<td>
									<input type="text" name="chairmen" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									职务：
								</th>
								<td>
									<input type="text" name="work" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									营业执照号码：
								</th>
								<td>
									<input type="text" name="id_card" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									组织机构代码：
								</th>
								<td>
									<input type="text" name="org_number" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<input type="text" name="address" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<input type="text" name="postalcode" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									指定联络人：
								</th>
								<td>
									<input type="text" name="contacter" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<input type="text" name="email" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<input type="text" name="tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<input type="text" name="mobile" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<input type="text" name="other_tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<textarea name="notes"></tEXTAREA>
								</TD>
								<TD></TD>
							</TR>
							<TR>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="legal-submit" class="btn-submit" type="button"
										name="submit" value="提交" />
								</td>
							</tr>
						</table>
					</form>
					<!-- legal-cust-form end -->
					<form action="#" method="post" id="other-cust-form"
						class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									名称：
								</th>
								<td>
									<input type="text" name="name" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									住所地：
								</th>
								<td>
									<input type="text" name="origo" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									实地经营地：
								</th>
								<td>
									<input type="text" name="present_addr" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									负责人：
								</th>
								<td>
									<input type="text" name="chairmen" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									职务：
								</th>
								<td>
									<input type="text" name="work" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									证件号码：
								</th>
								<td>
									<input type="text" name="id_card" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									组织机构代码：
								</th>
								<td>
									<input type="text" name="org_number" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<input type="text" name="address" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<input type="text" name="postalcode" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									指定联络人：
								</th>
								<td>
									<input type="text" name="contacter" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<input type="text" name="email" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<input type="text" name="tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<input type="text" name="mobile" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<input type="text" name="other_tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<textarea name="notes"></textarea>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="other-submit" class="btn-submit" type="button"
										name="submit" value="提交" />
								</td>
							</tr>
						</table>
					</form>
					<!-- other-cust-form end -->
					<div title="提示信息" id="ajax-action-dialog"></div>
				</fieldset>
			</div>
		</div>

	</body>
</html>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//添加客户操作
						$("#private-submit,#legal-submit,#other-submit")
								.click(
										function() {
											//获取客户类型id
											custType = $(
													"select[name='cust-type']")
													.children("option:selected")
													.attr("title");
											//根据 提交 按钮的id取得相应的表单项目值
											var btnName = $(this).attr("id");
											formId = "#"
													+ btnName.split("-")[0]
													+ "-cust-form";
											var name = $(
													formId
															+ " input[name='name']")
													.val();
											var sex = $(
													formId
															+ " select[name='sex']")
													.val();
											var birthday = $(
													formId
															+ " input[name='birthday']")
													.val();
											var race = $(
													formId
															+ " input[name='race']")
													.val();
											
											var origo = $(
													formId
															+ " input[name='origo']")
													.val();
											var work = $(
													formId
															+ " input[name='work']")
													.val();
											var present_addr = $(
													formId
															+ " input[name='present_addr']")
													.val();
											var id_card = $(
													formId
															+ " input[name='id_card']")
													.val();
											var address = $(
													formId
															+ " input[name='address']")
													.val();
											var postalcode = $(
													formId
															+ " input[name='postalcode']")
													.val();
											var email = $(
													formId
															+ " input[name='email']")
													.val();
											var tel = $(
													formId
															+ " input[name='tel']")
													.val();
											var mobile = $(
													formId
															+ " input[name='mobile']")
													.val();
											var other_tel = $(
													formId
															+ " input[name='other_tel']")
													.val();
											var notes = $(
													formId
															+ " textarea[name='notes']")
													.val();
											var chairmen = $(
													formId
															+ " input[name='chairmen']")
													.val();
											var org_number = $(
													formId
															+ " input[name='org_number']")
													.val();
											var contacter = $(
													formId
															+ " input[name='contacter']")
													.val();
											//数据验证
											if ($.trim(name) == '') {
												$(
														formId
																+ " input[name='name']")
														.parent()
														.siblings("td")
														.html(
																"<span class=\"tips\">该项不能为空！</span>");
												return false;
											}
											if ($.trim(email) != ''
													&& !isEmail(email)) {
												$(
														formId
																+ " input[name='email']")
														.parent()
														.siblings("td")
														.html(
																"<span class=\"tips\">电子邮箱格式不正确！</span>");
												return false;
											}
											
											var strPost = "zqCustomerModel.name=" + name
													+ "&zqCustomerModel.sex=" + sex
													+ "&zqCustomerModel.birthday=" + birthday
													+ "&zqCustomerModel.race=" + race
													+ "&zqCustomerModel.origo=" + origo
													+ "&zqCustomerModel.work=" + work
													+ "&zqCustomerModel.pressentAddr="
													+ present_addr
													+ "&zqCustomerModel.idCard=" + id_card
													+ "&zqCustomerModel.chairmen=" + chairmen
													+ "&zqCustomerModel.orgNumber="
													+ org_number + "&zqCustomerModel.address="
													+ address + "&zqCustomerModel.postalcode="
													+ postalcode + "&zqCustomerModel.email="
													+ email + "&zqCustomerModel.contacter="
													+ contacter + "&zqCustomerModel.tel=" + tel
													+ "&zqCustomerModel.mobile=" + mobile
													+ "&zqCustomerModel.otherTel=" + other_tel
													+ "&zqCustomerModel.notes=" + notes
													+ "&zqCustomerModel.cust_type=" + custType
													+ "&submit=1";
											$.ajax({
														type : "post",
														url : "saveCustomer.htm",
														dataType : "json",
														data : strPost,
														beforeSend : function() {
															$(
																	"#ajax-action-dialog")
																	.html(
																			"<h2>请稍候...</h2><br /><img src=\"<%=path%>/images/waiting.gif\"} />")
																	.dialog(
																			"open");
														},
														success : function(data) { //成功后返回的页面
															if (data.status == '0') {
																$("#ajax-action-dialog")
																		.html(
																				"<h2>"
																						+ data.info
																						+ "</h2>")
																		.dialog(
																				"open");
															} else {
																$("#ajax-action-dialog")
																		.html(
																				"<h2>"
																						+ data.info
																						+ "</h2><p>您可以：<a href='listCustomer.htm'>点此返回客户列表</a>")
																		.dialog(
																				"open");
															}
														},
														error: function(error){
															$("#ajax-action-dialog").dialog("close");
															alert("服务器异常，请联系管理员!");
														}
													});
										});
					});
</script>
