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
						<a href="contractlist.htm">合同管理</a> >
						<a href="javascript:void(0);">合同列表</a>
					</p>
				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						合同管理
					</h4>
					<div class="list-form">
					<ec:table items="contlist" var="row"
							retrieveRowsCallback="org.extremecomponents.table.callback.LimitCallback"
							action="contractlist.htm" width="100%">

							<ec:row highlightRow="true">

								<ec:column property="number" style="height:35px;width=18%;" title="合同编号">
								<a href="contractDetail.htm?contId=${row.id}" ><span id="number" >${row.number}</span></a>
								</ec:column>
								<ec:column property="contName" style="height:35px;width=20%;" title="合同名称"><span id="contName">${row.contName}</span></ec:column>
								<ec:column property="contTypeName" style="text-align:center;height:35px;" title="合同类型"/>
								<ec:column property="custName" style="height:35px;" title="客户名称"/>
								<ec:column property="sj" style="height:35px;" title="执行时间"><span id="executetime">${row.executeStartTime}~${row.executeEndTime}</span></ec:column>
								<ec:column property="lawyerName" style="height:35px;" title="负责律师"/>
								<ec:column property="cust_typeValue" escapeAutoFormat="true"
									style="text-align:center;height:35px;" title="操作">
									<s:if test="%{(#session.user.powers).indexOf('A2')>=0}"><a href="modifyContractInit.htm?contId=${row.id}" title="修改">修改</a>|</s:if><a href="${row.id}" title="归档">归档</a><s:if test="%{(#session.user.powers).indexOf('A3')>=0}">|<a href="#" onclick="deltCont('${row.id}');return false;" title="删除" >删除</a></s:if>
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
<s:if test="%{(#session.user.powers).indexOf('A3')>=0}">
function deltCont(contId){
	if(confirm("删除后将无法恢复是否确认?")){
		$.ajax({
			type : "post",
			url : "deltContract.htm",
			dataType : "json",
			data : {
				contId:contId
			},
			success : function(data) { //成功后返回的页面
				//alert(data.status);
				if (data.status != '1') {
					alert(data.info);
				} else {
					alert("删除成功!");
					window.location.href=window.location;
				}
			},
			error:function(error){
				alert("网络异常，请重新登录!");
				
			}
		});
	}
	
}
</s:if>
$("a[title=归档]").click(function(){
	if(confirm("是否确认归档?")){
		$.ajax({
			type : "post",
			url : "arachiveCont.htm",
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
