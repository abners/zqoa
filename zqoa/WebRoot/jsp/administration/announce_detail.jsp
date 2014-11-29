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
    <script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
	<link rel="stylesheet" href="<%=path%>/css/jquery-ui-1.8.11.custom.css"/>
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
                        <a href="javascript:void(0);">查看登记资料</a>
                    </p>
                    <script type="text/javascript">
						$(document).ready(function() {
							$(".btn-h28").button();
							});
						</script>
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>公告详情</h4>
                    <form action="addId" method="post" id="addId" class="add-form">

                    	<table>
                   			<colgroup span="1"></colgroup>
                   			<tr>
                            	<th>公告标题：</th>
                                <td><s:property value="zqNoticeModel.title"/></td>
                            </tr>
                        	<tr>
                            	<th>发布人：</th>
                                <td>
                                <s:property value="zqNoticeModel.authorName"/>
                                </td>
                            </tr>
                            <th>发布时间：</th>
                                <td>
                                <s:date name="zqNoticeModel.time" format="yyyy-MM-dd hh:mm:ss"/>
                                </td>
                            <tr>
                            <th>公告内容：</th>
                                <td>
                                <s:property value="zqNoticeModel.content"/>
                                </td>
                            </tr>

							<tr>
                            	<th>&nbsp;</th>
                                <td><a class="btn-h28 ui-button" id="addContFile"
													href="announceList.htm">返回公告列表</a>&nbsp;&nbsp;<a class="btn-h28 ui-button" id="addContFile"
													href="modifyAnnounceInit.htm?asrId=<s:property value="zqNoticeModel.id"/>">修改登记资料</a></td>
                            </tr>
                        </table>
                </fieldset>

  </body>
</html>
<script>
$(document).ready(function(){
	$("#btn-submit").click(function(){
		
	});
});
</script>
