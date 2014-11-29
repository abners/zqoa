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
    
    <title>权限管理</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
	 <link href="<%=path %>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=path %>/css/user.css" rel="stylesheet" />
        <link href="<%=path %>/css/types.css" rel="stylesheet" />
        <script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>	

  </head>
  
  <body>
    <div id="main">
            <div class="toolbar">
            	<p class="toolbar-shadow">&nbsp;</p>
                <div id="toolbar-content">
                    <p class="crumb">
                        <b>您的位置：</b>
                        <a href="/zqoa/index.php/toIndex.htm">首页</a> >
						<a href="listUser.htm">人事管理</a> >
						<a href="listUser.htm">用户组管理</a>>
						<a href="javascript:void(0);">权限管理</a>
                    </p>
                    
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
                <fieldset class="forms-area">
                    <h4>用户组权限管理</h4>
					<form class="add-form" id="editPowers"method="post" action="/zqoa/index.php/User/editPower/id/1">
					<input type="hidden" name="zqGroupModel.id" value="<s:property value="groupPowers.id"/>"/>
					<input type="hidden" name="zqGroupModel.groupName" value="<s:property value="groupPowers.groupName"/>"/>
												<h3>当前用户组：<span><s:property value="groupPowers.groupName"/></span></h3>
						<fieldset class="powerMgr">
							<legend>合同管理权限</legend>
							<ul id="contMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A1')>=0}">checked="checked"</s:if>/>
									添加合同
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A2')>=0}">checked="checked"</s:if>/>
									修改合同
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A3" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A3')>=0}">checked="checked"</s:if>/>
									删除合同
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A4" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A4')>=0}">checked="checked"</s:if>/>
									查看合同
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A5" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A5')>=0}">checked="checked"</s:if>/>
									合同类型管理
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="A6" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A6')>=0}">checked="checked"</s:if>/>
									收费登记
								</li>
								<li>
									<input class="select-all" type="checkbox" name="contMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('A1,A2,A3,A4,A5,A6')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>案件管理权限</legend>
							<ul id="caseMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B1')>=0}">checked="checked"</s:if>/>
									类型管理
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B2')>=0}">checked="checked"</s:if>/>
									添加案件
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B3" <s:if test="groupPowers.id==1">disabled</s:if>  <s:if test="%{groupPowers.power.indexOf('B3')>=0}">checked="checked"</s:if>/>
									删除案件
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B4" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B4')>=0}">checked="checked"</s:if>/>
									修改案件
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B5" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B5')>=0}">checked="checked"</s:if>/>
									查看案件
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="B6" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B6')>=0}">checked="checked"</s:if>>
									结案权限
								</li>
								<li>
									<input class="select-all" type="checkbox" name="caseMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('B1,B2,B3,B4,B5,B6')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>客户管理</legend>
							<ul id="custMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="C1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('C1')>=0}">checked="checked"</s:if>/>
									添加客户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="C2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('C2')>=0}">checked="checked"</s:if>/>
									查看客户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="C3" <s:if test="groupPowers.id==1">disabled</s:if>  <s:if test="%{groupPowers.power.indexOf('C3')>=0}">checked="checked"</s:if>/>
									编辑客户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="C4" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('C4')>=0}">checked="checked"</s:if>/>
									删除客户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="C5" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('C5')>=0}">checked="checked"</s:if>/>
									归入过期客户
								</li>
								<li>
									<input class="select-all" type="checkbox" name="custMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('C1,C2,C3,C4,C5')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>用户管理权限</legend>
							<ul id="userMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D1')>=0}">checked="checked"</s:if>/>
									添加用户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D2" <s:if test="groupPowers.id==1">disabled</s:if><s:if test="%{groupPowers.power.indexOf('D2')>=0}">checked="checked"</s:if>/>
									查看用户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D3" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D3')>=0}">checked="checked"</s:if>/>
									编辑用户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D4"<s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D4')>=0}">checked="checked"</s:if>/>
									删除用户
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D5"<s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D5')>=0}">checked="checked"</s:if>/>
									部门管理
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="D6" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D6')>=0}">checked="checked"</s:if>/>
									用户组管理
								</li>
								<li>
									<input class="select-all" type="checkbox" name="userMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('D1,D2,D3,D4,D5,D6')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>行政管理权限</legend>
							<ul id="affairMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E1')>=0}">checked="checked"</s:if>/>
									添加公告
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E2')>=0}">checked="checked"</s:if>/>
									事务申请
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E3" <s:if test="groupPowers.id==1">disabled</s:if>  <s:if test="%{groupPowers.power.indexOf('E3')>=0}">checked="checked"</s:if>/>
									事务申请管理
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E4" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E4')>=0}">checked="checked"</s:if>/>
									行政事务登记
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E5" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E5')>=0}">checked="checked"</s:if> />
									公告管理
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E6" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E6')>=0}">checked="checked"</s:if>/>
									查看公告
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="E7" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E7')>=0}">checked="checked"</s:if> />
									行政登记管理
								</li>
								<li>
									<input class="select-all" type="checkbox" name="affairMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('E1,E2,E3,E4,E5,E6,E7')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>财务管理权限</legend>
							<ul id="financeMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="F1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('F1')>=0}">checked="checked"</s:if>/>
									添加财务记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="F2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('F2')>=0}">checked="checked"</s:if>/>
									查看财务记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="F3" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('F3')>=0}">checked="checked"</s:if>/>
									删除财务记录
								</li>
								<li>
									<input class="select-all" type="checkbox" name="financeMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('F1,F2,F3')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>
						<fieldset class="powerMgr">
							<legend>资产管理权限</legend>
							<ul id="assetMgr">
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="G1" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('G1')>=0}">checked="checked"</s:if>/>
									查看资产记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="G2" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('G2')>=0}">checked="checked"</s:if>/>
									添加资产记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="G3" <s:if test="groupPowers.id==1">disabled</s:if>  <s:if test="%{groupPowers.power.indexOf('G3')>=0}">checked="checked"</s:if>/>
									编辑资产记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="G4" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('G4')>=0}">checked="checked"</s:if>/>
									删除资产记录
								</li>
								<li>
									<input type="checkbox" name="zqGroupModel.power" value="G5" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('G5')>=0}">checked="checked"</s:if>/>
									资产类型管理
								</li>
								<li>
									<input class="select-all" type="checkbox" name="assetMgr" <s:if test="groupPowers.id==1">disabled</s:if> <s:if test="%{groupPowers.power.indexOf('G1,G2,G3,G4,G5')>=0}">checked="checked"</s:if>/>
									<span>全选</span>
								</li>
							</ul>
						</fieldset>	
										
						<s:if test="groupPowers.id!=1"><input type="button" id="btn-submit" name="submit" value="提交" /></s:if>
						</form>		
                </fieldset>

  </body>
</html>
<script>
$(document).ready(function(){
	//editPower.html
	//全选
	$(".select-all").change(function(){
		var moudelName = $(this).attr("name");
		$("#"+moudelName+" input[name='zqGroupModel.power']").attr("checked",$(this).attr("checked"));
	});
	<s:if test="groupPowers.id!=1">
	$("#btn-submit").click(function(){
		if(confirm("是否确认修改?")){
			$.ajax({
				type : "post",
				url : "saveEditPower.htm",
				dataType : "json",
				data : $("#editPowers").serialize(),
				success : function(data) { //成功后返回的页面
					alert(data.info);
					if (data.status == '1'){
						window.location.href="userGroupManage.htm";
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
	});
	</s:if>
});
</script>
