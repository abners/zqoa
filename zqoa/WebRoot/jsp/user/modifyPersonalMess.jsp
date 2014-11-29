<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加案件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/case.css" />
		<script
			src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/pagination.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>
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
						<a href="/zqoa/index.php/toIndex.htm">首页</a> >

						<a href="/zqoa/index.php/contract/listUser.htm">人事管理</a> >
						<a href="javascript:void(0);">用户信息修改</a>
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
					<li class="arrow">
						&nbsp;
					</li>
					<li class="steps">
						<h3>
							步骤二
						</h3>
						<p>
							添加案件
						</p>
					</li>
					<li class="arrow back-arrow">
						&nbsp;
					</li>
					<li class="curt-step">
						<h3>
							步骤三
						</h3>
						<p>
							添加案件
						</p>
					</li>
					<li class="arrow curt-arrow">
						&nbsp;
					</li>
				</ul>
			</div>
			--%><!--guide end-->
			<div class="wrap">
				<fieldset id="step2-add-cont" class="forms-area">
					<h4>
						修改用户资料
					</h4>
					<form action="" method="post" id="Case-form" class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							
							<tr>
								<th>
									姓名：
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
									<select name="zqUserModel.zqUserinfoModel.sex" >
									<option value="0" <s:if test="userinfo.zqUserinfoModel.sex==0">selected=selected</s:if>>男</option>
									<option value="1" <s:if test="userinfo.zqUserinfoModel.sex==1">selected=selected</s:if>>女</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<th>
									邮箱：
								</th>
								<td>
									<input type="text" name="zqUserModel.email" id="email" value="<s:property value="userinfo.email"/>"/>
								</td>
							</tr>

							
							<tr>
								<th>
									员工类型：
								</th>
								<td>
									<select name="d">
										<option value="2">工薪制</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									生日：
								</th>
								<td>
									<input type="text" name="zqUserModel.zqUserinfoModel.birthday" id="birthday" onclick="WdatePicker({el:'birthday'})" class="validity" value="<s:property value="userinfo.zqUserinfoModel.birthday"/>"/>
								</td>
							</tr>
							<tr>
								<th>
									籍贯：
								</th>
								<td>
									<input type="text" name="zqUserModel.zqUserinfoModel.origo" id="birthday" value="<s:property value="userinfo.zqUserinfoModel.origo"/>"/>
								</td>
							</tr>
							<tr>
								<th>
									教育程度：
								</th>
								<td>
									<input type="text" name="zqUserModel.zqUserinfoModel.education" id="birthday" value="<s:property value="userinfo.zqUserinfoModel.education"/>"/>
								</td>
							</tr>
							<tr>
							<tr>
								<th>
									手机：
								</th>
								<td>
									<input type="text" name="zqUserModel.zqUserinfoModel.mobile" id="mobile" value="<s:property value="userinfo.zqUserinfoModel.mobile"/>"/>
								</td>
							</tr>
							<tr>
							<tr>
								<th>
									电话：
								</th>
								<td>
									<input type="text" name="zqUserModel.zqUserinfoModel.tel" id="tel" value="<s:property value="userinfo.zqUserinfoModel.tel"/>"/>
								</td>
							</tr>
							<tr>
							<tr>
								<th>
									部门：
								</th>
								<td>
									<select name="zqUserModel.zqUserinfoModel.zqDepartment.deptId">
										<s:iterator value="depart">
										<option value="<s:property value="deptId"/>" <s:if test="userinfo.zqUserinfoModel.zqDepartment.deptId==deptId">selected="selected"</s:if>>
											<s:property value="depName"/>
										</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td colspan="2">
									<%--<input type="hidden" name="cid" value="338" />
								--%></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="btn-add-Case" class="btn-submit" type="button"
										name="submit" value="提交" />
								</td>
							</tr>
						</table>
						<input type="hidden" name="zqUserModel.id" value="<s:property value="userinfo.id"/>"/>
						<input type="hidden" name="zqUserModel.zqUserinfoModel.uid" value="<s:property value="userinfo.zqUserinfoModel.uid"/>"/>
						<input type="hidden" name="zqUserModel.groupId" value="<s:property value="userinfo.groupId"/>"/>
						<input type="hidden" name="zqUserModel.name" value="<s:property value="userinfo.name"/>"/>
					</form>
					<!-- Case-form end -->
					
				</fieldset>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn-add-Case").click(function(){
		
		if($.trim($("#tel")).length>15){
			alert("电话号码的长度不能大于15位!");
			return ;
		}
		if($.trim($("#mobile")).length>15){
			alert("手机号码的长度不能大于15位!");
			return ;
		}
		if(confirm("请检查所填数据,是否确认?")){
			
			$.ajax({
				type : "post",
				url : "modifyUserMess.htm",
				dataType : "json",
				data : $("#Case-form").serialize(),
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					alert(data.info);
					if (data.status == '1') {
						window.location.reload();
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
		
	});
	///取消选择
	$("#lawyers-list a[title=取消]").live("click",function(){
		$(this).parent().remove();
	});
	$("#relative-cont-list a[title=取消]").live("click",function(){
		$(this).parent().remove();
		
	});
});
</script>

