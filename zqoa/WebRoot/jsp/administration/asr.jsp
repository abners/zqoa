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
    
    <title>行政事务登记</title>
    
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
                        <a href="javascript:void(0);">行政事务登记</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>行政事务登记</h4>
                    <form action="addId" method="post" id="addId" class="add-form">
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	<tr>
                            	<th>所属机构：</th>
                                <td><select name="zqAsrModel.deptId">
                                <s:iterator value="zqdepart">
                                		<option value="<s:property value="deptId"/>"><s:property value="depName"/></option>
                                </s:iterator>
                                	</select>
                                </td>
                            </tr>
                            <tr>
                            	<th>事件名称：</th>
                                <td><input type="text" name="zqAsrModel.name" id="name"/></td>
                            </tr>
                            <tr>

							<tr>
                            	<th>事件内容：</th>
                                <td><textarea name="zqAsrModel.content" id=""></textarea></td>
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
		
		if(confirm("是否确认添加?")){
			$.ajax({
				type : "post",
				url : "saveZqAsr.htm",
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
