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
    
    <title>修改密码</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
	<link href="<%=path %>/css/tablecloth.css" rel="stylesheet" />
	<link href="<%=path %>/css/user.css" rel="stylesheet" />
    <link href="<%=path %>/css/types.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="main">
            <div class="toolbar">
            	<p class="toolbar-shadow">&nbsp;</p>
                <div id="toolbar-content">
                    <p class="crumb">
                        <b>您的位置：</b>
                        <a href="/zqoa/index.php/toIndex.htm">首页</a> > 
                        <a href="/zqoa/index.php/User/listUser.htm">人事管理</a> >
                        <a href="javascript:void(0);">修改密码</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>修改登录密码</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	<tr>
                            	<th>姓名：</th>
                                <td><s:property value="#session.user.name"/></td>
                            </tr>
                            <tr>
                            	<th>原密码：</th>
                                <td><input type="password" name="password" id="password"/></td>
                            </tr>
                            <tr>
                            <th>新密码：</th>
                               <td><input type="password" name="newpass" id="newpass"/></td>
                            </tr>
							<tr>
                            	<th>确认新密码：</th>
                                <td><input type="password" name="tpassword" id="tpassword"/></td>
                            </tr>
							<tr>
                            	<th>&nbsp;</th>
                                <td><input id="btn-submit" type="button" name="submit" value="提交" /></td>
                            </tr>
                        </table>
                </fieldset>

  </body>
</html>
<script>
$(document).ready(function(){
	$("#btn-submit").click(function(){
		if($.trim($("#password").val())==''){
			alert("请输入原密码!");
			return false;
		}
		if($.trim($("#newpass").val())==''){
			alert("请输入新密码!");
			return false;
		}
		if($.trim($("#tpassword").val())==''){
			alert("请输入确认密码!");
			return false;
		}
		if($("#tpassword").val()!=$("#newpass").val()){
			alert("两次输入的新密码不一致，请重新输入!");
			return false;
		}
		if(confirm("是否确认修改?")){
			$.ajax({
				type : "post",
				url : "saveMyNewPass.htm",
				dataType : "json",
				data : $("#addId").serialize(),
				success : function(data) { //成功后返回的页面
					alert(data.info);
					if (data.status == '1'){
						window.location.reload();
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
