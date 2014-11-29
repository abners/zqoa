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
    
    <title>添加每日事务记录</title>
    
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
	<link href="<%=path %>/css/tablecloth.css" rel="stylesheet" />
	<link href="<%=path %>/css/user.css" rel="stylesheet" />
    <link href="<%=path %>/css/types.css" rel="stylesheet" />
    <script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>	
    <script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
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
                        <a href="javascript:void(0);">添加每日事物</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>事物记录</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	
                            <tr>
                            	<th>时间：</th>
                                <td><s:property value="toDayDate"/></td>
                            </tr>
                            <tr>

							<tr>
                            	<th>事物记录：</th>
                                <td><textarea name="zqSerrecordModel.content" id="announce_content"></textarea></td>
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

		
		if($.trim($("#announce_content").val())==''){
			alert("事物内容不能为空！");
			return false;
		}
		
		if(confirm("是否确认添加?")){
			$.ajax({
				type : "post",
				url : "saveZqSerRecord.htm",
				dataType : "json",
				data : $("#addId").serialize(),
				beforeSend : function() {
					$(
							"#ajax-action-dialog")
							.html(
									"<h2>请稍候...</h2><br /><img src=\"<%=path%>/images/waiting.gif\"} />")
							.dialog(
									"open");
				},
				success : function(data) { //成功后返回的页面
					alert("事物添加成功!");
					if (data.status == '1'){
						window.location.reload();
					}else{
						alert("添加失败，请稍后重试!");
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
