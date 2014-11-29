<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中勤OA管理系统</title>
</head>
<frameset cols="*" rows="76, *" id="frame_main"  border="0">
    <frame src="<%=path %>/jsp/header.jsp" noresize="noresize" name="header"/>
    
    <frameset cols="190, *" name="index">
        <frame src="<%=path %>/jsp/left.jsp" name="menu" />
        <frameset rows="*,50" >
        	<frame src="<%=path %>/jsp/main.jsp" name="main" />
        	<frame src="<%=path %>/jsp/bottom.html" scrolling="no" name="bottom" />
        </frameset>
    </frameset>
    
</frameset>
</html>