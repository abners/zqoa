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
    
    <title>添加公告</title>
    
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
                        <a href="javascript:void(0);">事务申请</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>事务申请</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	
                            <tr>
                            	<th>事务类型：</th>
                                <td><select name="">
                                		<option value="15">财务申请</option>
                                		<option value="16">假期申请</option>
                                	</select></td>
                            </tr>
                            <tr>

							<tr>
                            	<th>备注：</th>
                                <td><textarea name="zqNoticeModel.content" id="announce_content"></textarea></td>
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
		
		if($.trim($("#announce_content").val())==''){
			alert("公告内容不能为空！");
			return false;
		}
		
		if(confirm("是否确认添加?")){
			$.ajax({
				type : "post",
				url : "saveZqAnnounce.htm",
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
					alert("公告添加成功!");
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
