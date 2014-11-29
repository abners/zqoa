<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>中勤OA管理系统</title>
	
    <meta http-equiv="keywords" content="中勤">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
  </head>
  
  <body>
    <div id="header">
    	<div id="logo">
        	<img src="../images/logo.jpg" align="Logo" title="中勤律师事务所" />
        </div><!--logo end-->
		<div id="main-nav">
        	<ul>
            	<li><a href="toIndex.htm" target="main">个人主页</a></li>
            	<li><a href="contractlist.htm" target="main">合同管理</a></li>
                <li><a href="listCase.htm" target="main">案件管理</a></li>
                <li><a href="listArchives.htm" target="main">档案管理</a></li>
                <li><a href="listCustomer.htm" target="main">客户管理</a></li>
                <li><a href="listUser.htm" target="main">人事管理</a></li>
                <li><a href="/zqoa/index.php/Asset/assetsList.htm" target="main">资产管理</a></li>
                 <li><a href="http://124.207.79.170/zqoa" target="_blank">进入旧版</a></li>
                <%--<li><a href="/zqoa/index.php/Finance/listFinance">财务管理</a></li>
            --%></ul>
        </div><!--main-nav end-->
        <div class="clean"></div>
    </div><!-- header end -->

  </body>
</html>
