<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>客户详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />

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


					<a href="/zqoa/index.php/Customer/listCustomer.htm">客户管理</a> >
					<a href="javascript:void(0);">查看客户详情</a>
				</p>

			</div>
			<a id="toolbar-opt" class="option eject-blue"
				href="javascript:void(0);return false;">&nbsp;</a>
		</div>
		<!--guide end-->

		<div class="wrap">
			<fieldset class="forms-area">
				<h4>
					客户信息
				</h4>
				<form id="add-form" action=""
					method="post" class="add-form">
					<s:if test="cust_mess.cust_type==4">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									姓名：
								</th>
								<td>
									<s:property value="cust_mess.name" />
								</td>
							</tr>
							<tr>
								<th>
									性别：
								</th>
								<td>

									<s:if test="cust_mess.sex==0">男</s:if>
									<s:else>女</s:else>
								</td>
							</tr>
							<tr>
								<th>
									出生年月：
								</th>
								<td>
									<s:property value="cust_mess.birthday" />
								</td>
							</tr>
							<tr>
								<th>
									民族：
								</th>
								<td>
									<s:property value="cust_mess.race" />
								</td>
							</tr>
							<tr>
								<th>
									现住址：
								</th>
								<td>
									<s:property value="cust_mess.pressentAddr" />
								</td>
							</tr>
							<tr>
								<th>
									身份证号码：
								</th>
								<td>
									<s:property value="cust_mess.idCard" />
								</td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<s:property value="cust_mess.address" />
								</td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<s:property value="cust_mess.postalcode" />
								</td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<s:property value="cust_mess.email" />
								</td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<s:property value="cust_mess.tel" />
								</td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<s:property value="cust_mess.mobile" />
								</td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<s:property value="cust_mess.otherTel" />
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td>
									<s:property value="cust_mess.notes" />
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="btn-submit" type="button" name="back" onclick="javascript:window.history.back();" value="返回" />
								</td>
							</tr>

						</table>
					</s:if>
					<s:elseif test="cust_mess.cust_type=5">
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									名称：
								</th>
								<td>
									<s:property value="cust_mess.name" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									住所地：
								</th>
								<td>
									<s:property value="cust_mess.origo" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									实地经营地：
								</th>
								<td>
									<s:property value="cust_mess.pressentAddr" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									法定代表人：
								</th>
								<td>
									<s:property value="cust_mess.chairmen" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									职务：
								</th>
								<td>
									<s:property value="cust_mess.work" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									营业执照号码：
								</th>
								<td>
									<s:property value="cust_mess.idCard" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									组织机构代码：
								</th>
								<td>
									<s:property value="cust_mess.orgNumber" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<s:property value="cust_mess.address" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<s:property value="cust_mess.postalcode" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									指定联络人：
								</th>
								<td>
									<s:property value="cust_mess.contacter" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<s:property value="cust_mess.email" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<s:property value="cust_mess.tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<s:property value="cust_mess.mobile" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<s:property value="cust_mess.otherTel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<s:property value="cust_mess.notes" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="legal-submit" class="btn-submit" type="button"
										name="button" onclick="javascript:window.history.back();" value="返回" />
								</td>
							</tr>
						</table>
					</s:elseif>
					<s:else>
						<table>
							<colgroup span="1"></colgroup>
							<tr>
								<th>
									名称：
								</th>
								<td>
									<s:property value="cust_mess.name" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									住所地：
								</th>
								<td>
									<s:property value="cust_mess.origo" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									实地经营地：
								</th>
								<td>
									<s:property value="cust_mess.pressent_addr" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									负责人：
								</th>
								<td>
									<s:property value="cust_mess.chairmen" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									职务：
								</th>
								<td>
									<s:property value="cust_mess.work" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									证件号码：
								</th>
								<td>
									<s:property value="cust_mess.id_card" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									组织机构代码：
								</th>
								<td>
									<s:property value="cust_mess.org_number" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									通讯地址：
								</th>
								<td>
									<s:property value="cust_mess.address" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									邮政编码：
								</th>
								<td>
									<s:property value="cust_mess.postalcode" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									指定联络人：
								</th>
								<td>
									<s:property value="cust_mess.contactor" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电子邮箱：
								</th>
								<td>
									<s:property value="cust_mess.email" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<s:property value="cust_mess.tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<s:property value="cust_mess.moblie" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									其他联系方式：
								</th>
								<td>
									<s:property value="cust_mess.other_tel" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<s:property value="cust_mess.notes" />
								</td>
								<td></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="legal-submit" class="btn-submit" type="button"
										name="submit" onclick="returnBack();" value="返回"/>
								</td>
							</tr>
						</table>
					</s:else>
				</form>
			</fieldset>
	</body>
</html>
<script>
function returnBack(){
	window.location.href='listCustomer.htm';
}
</script>
