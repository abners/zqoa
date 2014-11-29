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

		<title>用户详情</title>
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
						<a href="/zqoa/index.php/toIndex.htm">首页</a> >

						
						<script type="text/javascript">
						$(document).ready(function() {
							$(".btn-h28").button();
							});
						</script>
						<a href="/zqoa/index.php/contract/index/listUser.htm">人事管理</a> >
						<a href="javascript:void(0);">用户信息</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						用户详情
					</h4>
					<form id="list-form" class="add-form">
						<table class="cont-detail-list">
							<tr>
								<th>
									用户姓名:
								</th>
								<td>
									<s:property value="userinfo.name"/>
								</td>
							</tr>
							<tr>
								<th>
									性别：
								</th>
								<td>
									<s:if test="userinfo.zqUserinfoModel.sex==0">男</s:if>
									<s:else>女</s:else>
								</td>
							</tr>
							<tr>
								<th>
									邮箱：
								</th>
								<td>
									<s:property value="userinfo.email"/>
								</td>
							</tr>
							<tr>
							<th>
								用户组：
							</th>
							<td>
								<s:property value="userinfo.groupName"/>
							</td>
							</tr>
							<tr>
								<th>
									员工类型：
								</th>
								<td>
									工薪制
								</td>
							</tr>
							<tr>
								<th>
									生日:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.birthday"/>
								</td>
							</tr>
							<tr>
								<th>
									籍贯:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.origo"/>
								</td>
							</tr>
							<tr>
								<th>
									教育程度:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.education"/>
								</td>
							</tr>
							<tr>
								<th>
									手机:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.mobile"/>
								</td>
							</tr>
							<tr>
								<th>
									电话:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.tel"/>
								</td>
							</tr>
							<tr>
								<th>
									部门:
								</th>
								<td>
									<s:property value="userinfo.zqUserinfoModel.zqDepartment.depName"/>
								</td>
							</tr>
							<tr>
                                <th>&nbsp;</th>
                                <td>
                                	<table>
                                    	<tr>
                                        	<td><a class="btn-h28 ui-button" href="listUser.htm">返回用户列表</a></td>
                                            <td><a class="btn-h28 ui-button" href="modifyUserMessInit.htm?userId=<s:property value="userinfo.id"/>">修改用户资料</a></td>
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
