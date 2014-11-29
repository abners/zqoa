<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>协办律师选择</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
	</head>

	<body>
		<ec:table items="users" var="row"
			action="getCo_counsel.htm" width="90%" 
	showPagination="false">

			<ec:row highlightRow="true">

				<ec:column property="name" title="律师名称">
				</ec:column>

				<ec:column property="cust_typeValue" style="text-align:center;" title="操作"><a href="${row.id}" title="选择">选择</a>
				</ec:column>
			</ec:row>

		</ec:table>
		<!-- lawyer-dialog-form end-->
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("a[title=选择]").click(function(){
		$(window.opener.document.getElementById("lawyers-list"))
				.append("<li>"+$(this).parent("td").prev().text()
						+"<a href='javascript:void(0);' title='取消'>取消</a><input type='hidden' name='lawyerId' value='"
						+$(this).attr("href")+"'/><input type='hidden' name='lawyerName' value='"+$(this).parent("td").prev().text()+"'/> </li>");
		window.close();
		return false;
	});
});
</script>
