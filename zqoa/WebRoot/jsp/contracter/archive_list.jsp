<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/WEB-INF/tld/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>合同查看</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/extremecomponents.css" />
		<link href="<%=request.getContextPath() %>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=request.getContextPath() %>/css/contract.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>		
		<script type="text/javascript" src="<%=path%>/js/extremecomponents.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>

	</head>

	<body>
		<div id="main">
			<div class="toolbar">
				<p class="toolbar-shadow">
					&nbsp;
				</p>
				<div id="toolbar-content">
					<p class="crumb">
						<b>您的位置：</b>
						<a href="toIndex.htm">首页</a> >
						<a href="javascript:void(0);">档案管理</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						档案管理
					</h4>
					<div class="list-form">
					<ec:table items="contlist" var="row"
							retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback"
							action="contractlist.htm" width="100%">

							<ec:row highlightRow="true">

								<ec:column property="number" style="height:35px;width=18%;text-align:center" title="合同编号">
								<a href="archivedDetail.htm?contId=${row.id}" ><span id="number" >${row.number}</span></a>
								</ec:column>
								<ec:column property="contName" style="height:35px;width=20%;" title="合同名称"><span id="contName">${row.contName}</span></ec:column>
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;" title="操作">
									<a href="${row.id}" title="出档">出档</a>
								</ec:column>

							</ec:row>

						</ec:table>
					</div>	
				</fieldset>
				
				
			</div>
		</div>

	</body>
</html>
<script type="text/javascript">
showTdTitle("contName",16);
showTdTitle("number",16);
showTdTitle("executetime",7);
$("a[title=出档]").click(function(){
	if(confirm("是否确认出档?")){
		$.ajax({
			type : "post",
			url : "contArchive.htm",
			dataType : "json",
			data : {
				contId:$(this).attr("href")
			},
			success : function(data) { //成功后返回的页面
				//alert(data.status);
				alert(data.info);
				if (data.status == '1') {
					window.location.reload();
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
				
			}
		});
	}
	return false;
});
</script>
