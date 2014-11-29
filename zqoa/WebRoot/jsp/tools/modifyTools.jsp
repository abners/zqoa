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
                        <a href="/zqoa/index.php/User/listUserInfo/toolsList.htm">工具管理</a> >
                        <a href="javascript:void(0);">修改工具</a>
                    </p>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>工具信息</h4>
                    <form action="updateTools.htm" method="post" id="addId" class="add-form" enctype="multipart/form-data">
                    <input type="hidden" name="zqToolsModel.fileId" value="<s:property value="zqToolsModel.fileId"/>"/>
                    <input type="hidden" name="zqToolsModel.ywjm" value="<s:property value="zqToolsModel.ywjm"/>"/>
                    <input type="hidden" name="zqToolsModel.id" value="<s:property value="zqToolsModel.id"/>"/>
                    	<table>
                   			<colgroup span="1"></colgroup>
                        	<tr>
                            	<th>名称：</th>
                                <td><input type="text" value="<s:property value="zqToolsModel.name"/>" name="zqToolsModel.name" id="name"/></td>
                            </tr>
                            <tr>
                            	<th>类型：</th>
                                <td><select name="zqToolsModel.typeId">
                                	<s:iterator value="toolsType">
                                	<option <s:if test="zqToolsModel.typeId==id">selected="selected"</s:if> value="<s:property value="id"/>"><s:property value="typeName"/></option>
                                	</s:iterator>
                                		
                                	</select></td>
                            </tr>
                            <tr>
                            	<th>网址：</th>
                                <td><input type="text" value="<s:property value="zqToolsModel.address"/>" name="zqToolsModel.address"/></td>
                            </tr>
                            <tr>
                            <tr>
                            	<th>附件：</th>
                            	<td><s:property value="zqToolsModel.ywjm"/></br>
                            		<input type="file" name="upload"/>
                            	</td>
                            </tr>
							<tr>
                            	<th>备注：</th>
                                <td><textarea name="zqToolsModel.notes" id="announce_content"><s:property value="zqToolsModel.notes"/></textarea></td>
                            </tr>
							<tr>
                            	<th>&nbsp;</th>
                                <td><input id="btn-submit" type="button" name="tj" value="提交" /><span id="uploading" style="color:red;"></span></td>
                            </tr>
                        </table>
                        </form>
                </fieldset>

  </body>
</html>
<script>
$(document).ready(function(){
	$("#btn-submit").click(function(){
		if($.trim($("#name").val())==''){
			alert("请填写名称！");
			return false;
		}
		if(confirm("是否确认修改?")){
			$("#uploading").text("文件上传中，请稍后.....");
			$("#addId").submit();
			return true;
		}
		return false;
	});
});
</script>
