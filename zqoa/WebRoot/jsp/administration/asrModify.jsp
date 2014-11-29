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
    
    <title>行政事务修改</title>
    
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
                        <a href="/zqoa/index.php/User/listAsr.htm">行政管理</a> >
                        <a href="javascript:void(0);">修改登记资料</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>行政事务登记</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    <input type="hidden" name="zqAsrModel.id" value="<s:property value="zqasr.id"/>">
                    <input type="hidden" name="zqAsrModel.deptId" value="<s:property value="zqasr.deptId"/>">
                    <input type="hidden" name="zqAsrModel.createTime" value="<s:property value="zqasr.createTime"/>">
                    <input type="hidden" name="zqAsrModel.author" value="<s:property value="zqasr.author"/>">
                    	<table>
                   			<colgroup span="1"></colgroup>
                   			<tr>
                            	<th>事件名称：</th>
                                <td><input type="text" name="zqAsrModel.name" id="name" value="<s:property value="zqasr.name"/>"/></td>
                            </tr>
                        	<tr>
                            	<th>事件类型：</th>
                                <td>
                                <s:property value="zqasr.deptName"/>
                                </td>
                            </tr>
                            <th>操作人：</th>
                                <td>
                                <s:property value="zqasr.authorName"/>
                                </td>
                            <tr>
                            <th>登记时间：</th>
                                <td>
                                <s:property value="zqasr.createTime"/>
                                </td>
                            </tr>

							<tr>
                            	<th>事件内容：</th>
                                <td><textarea name="zqAsrModel.content" id=""><s:property value="zqasr.content"/></textarea></td>
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
		if($.trim($("#name").val())==''){
			alert("请填写事件名称！");
			return false;
		}
		
		if(confirm("是否确认修改?")){
			$.ajax({
				type : "post",
				url : "modifyZqAsr.htm",
				dataType : "json",
				data : $("#addId").serialize(),
				success : function(data) { //成功后返回的页面
					
					if (data.status == '1'){
						alert("修改成功!");
						window.location.href="listAsr.htm";
					}else{
						alert("修改失败，请稍后重试!");
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
