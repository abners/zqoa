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
                        <a href="javascript:void(0);">修改公告信息</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>公告修改</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    <input type="hidden" name="zqNoticeModel.id" value="<s:property value="zqNotice.id"/>">
                    <input type="hidden" name="zqNoticeModel.createTime" value="<s:property value="zqNotice.time"/>">
                    <input type="hidden" name="zqNoticeModel.author" value="<s:property value="zqNotice.author"/>">
                    	<table>
                   			<colgroup span="1"></colgroup>
                   			<tr>
                            	<th>公告标题：</th>
                                <td><input type="text" name="zqNoticeModel.title" id="name" value="<s:property value="zqNotice.title"/>"/></td>
                            </tr>
                            <tr>
                            <th>发布时间：</th>
                                <td>
                                <s:date name="zqNotice.time"/>
                                <input type="hidden" name="zqNoticeModel.time" value="<s:date name="zqNotice.time"/>">
                                </td>
                            </tr>

							<tr>
                            	<th>公告内容：</th>
                                <td><textarea name="zqNoticeModel.content" id=""><s:property value="zqNotice.content"/></textarea></td>
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
			alert("请填写公告标题！");
			return false;
		}
		
		if(confirm("是否确认修改?")){
			$.ajax({
				type : "post",
				url : "modifyZqNotice.htm",
				dataType : "json",
				data : $("#addId").serialize(),
				success : function(data) { //成功后返回的页面
					
					if (data.status == '1'){
						alert("修改成功!");
						window.location.href="announceList.htm";
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
