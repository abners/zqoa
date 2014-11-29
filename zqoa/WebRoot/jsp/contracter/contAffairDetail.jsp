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
		<link href="<%=request.getContextPath()%>/css/tablecloth.css"
			rel="stylesheet" />
		<link href="<%=request.getContextPath()%>/css/contract.css"
			rel="stylesheet" />
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
						<a href="contAffairList.htm">查看合同事务记录</a>>
						<a href="javascript:void(0);">合同事务详情</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						事务列表
					</h4>

					<s:if test="contaffair.size==0">
						<div class="help-tips">
							<h2>
								<span class="info">此合同尚无事务记录！</span>
							</h2>
							<h2 style="margin: 10px 0; font-size: 12px;">
								您可以
								<a href="/zqoa/index.php/contract/contractaffair">点此登记事务</a> .
							</h2>
						</div>
					</s:if>
					<s:else>
						<div class="list-form">
							<ec:table items="contaffair" var="row"
								action="contAffairView.htm" width="100%" showPagination="false">

								<ec:row highlightRow="true">

									<ec:column property="operateTime" cell="date" format="yyyy-MM-dd hh:mm:ss" style="height:35px;text-align:center;"
										title="登记时间" />
									<ec:column property="contName" style="height:35px;width=20%;"
										title="登记人">
										<span id="contName">${row.creater}</span>
									</ec:column>
									<ec:column property="affairContent"
										style="text-align:left;width=30%;height:35px;" title="事务详情" />

								</ec:row>

							</ec:table>
						</div>
					</s:else>


				</fieldset>


			</div>
		</div>

	</body>
</html>
<script type="text/javascript">
	showTdTitle("contName", 25);
</script>
