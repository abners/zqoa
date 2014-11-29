<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>案件详情</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link href="<%=path%>/css/tablecloth.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=path%>/css/case.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/jquery-ui-1.8.11.custom.css"/>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>	
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>	
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
			$(document).ready(function() {
					$(".btn-h28").button();
					$(".btn-submit,.btn-edit").button();
					
			});
			//删除案件事件
			function deltProcess(processId){
				if(confirm("删除后将无法恢复，是否确认?")){
					$.ajax({
    	    			type : "post",
    	    			url : "deltCaseProcess.htm",
    	    			dataType : "json",
    	    			data : {
    	    				caseProcessId:processId
    	    			},
    	    			success : function(data) { //成功后返回的页面
    	    				alert(data.info);
    	    				if (data.status == '1'){
    	    					window.location.reload();
    	    				}
    	    			},
    	    			error:function(error){
    	    				//alert(error);
    	    				alert("网络异常，请重新登录!");
    	    			}
    	    		});
				}
			}
			//删除案件联系人
			function deltContact(contactId){
				if(confirm("此操作无法恢复，是否确认?")){
					$.ajax({
    	    			type : "post",
    	    			url : "deltCaseContact.htm",
    	    			dataType : "json",
    	    			data : {
    	    				caseContactId:contactId
    	    			},
    	    			success : function(data) { //成功后返回的页面
    	    				alert(data.info);
    	    				if (data.status == '1'){
    	    					window.location.reload();
    	    				}
    	    			},
    	    			error:function(error){
    	    				//alert(error);
    	    				alert("网络异常，请重新登录!");
    	    			}
    	    		});
				}
			}
	</script>
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
						<s:if test="casemess.contId!=null"><a href="listCase.htm">案件管理</a></s:if><s:else><a href="listCaseBeforeCont.htm">案件管理</a></s:else> >
						<a href="javascript:void(0);">案件详情</a>
					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area">

					<h4>
						案件详情
					</h4>
					<div class="case-detail">
						<fieldset>
							<legend>
								案件基本信息
							</legend>
							<table>
								<tr>
									<td>
										客户名称:
									</td>
									<td>
										<a href="custDetail.htm?cust_id=<s:property value="casemess.custId"/>"
											title="查看客户信息"><s:property value="casemess.custName"/></a>
									</td>
								</tr>
								<tr>
									<td>
										所属合同:
									</td>
									<td>
										<a href="contractDetail.htm?contId=<s:property value="casemess.contId"/>"
											title="查看合同信息" ><s:property value="casemess.contName"/></a>
									</td>
								</tr>
								<tr>
									<td>
										案件名称：
									</td>
									<td>
										<s:property value="casemess.caseName"/>
									</td>
								</tr>
								<tr>
									<td>
										案件编号：
									</td>
									<td>
										<s:property value="casemess.number"/>
									</td>
								</tr>
								<tr>
									<td>
										案件类型：
									</td>
									<td>
										<s:property value="casemess.typeName"/>
									</td>
								</tr>
								<tr>
									<td>
										当事人身份：
									</td>
									<td>
										<s:property value="casemess.identityName"/>
									</td>
								</tr>
								<tr>
									<td>
										主办律师：
									</td>
									<td>
										<s:property value="casemess.lawyerName"/>
									</td>
								</tr>
								<tr>
									<td>
										协办律师：
									</td>
									<td>
										<ul>
										<s:iterator value="coscus">
											<li><s:property value="lawyerName"/></li>
										</s:iterator>
										</ul>
									</td>
								</tr>
								<tr>
									<td>
										关联案件：
									</td>
									<td>
										<ul>
										<s:iterator value="casemess.relativeCaseNames" >
											<li><a href="javascript:void(0)"><s:property value="caseName"/></a></li>
										</s:iterator>
										</ul>
									</td>
								</tr>
								<tr>
									<td>
										备注：
									</td>
									<td>
									<s:property value="casemess.notes"/>
									</td>
								</tr>
							</table>
							<p>
								<s:if test="%{(#session.user.powers).indexOf('B4')>=0}"><a class="btn-edit" href="modifyCaseMessInit.htm?caseId=<s:property value="casemess.id" />">修改案件基本信息</a></s:if>
							</p>
						</fieldset>
						<a name="caseProcess"></a>
						<fieldset>
							<legend>
								案件事件流程
							</legend>
							<table class="case-process">
								<thead>
									<tr>
										<th class="w90">
											时间
										</th>
										<th>
											事件
										</th>
										<th class="w90">
											操作人
										</th>
										<th style="width: 320px;">
											附件
										</th>
										<th class="w120">
											操作
										</th>
									</tr>
								</thead>
								<tbody>
								<s:iterator value="casemess.zqCaseprocessModels">
								<tr>
									<td><s:property value="processTime" /></td>
									<td><s:property value="content"/></td>
									<td><s:property value="createrName"/></td>
									<td>
										<s:iterator value="zqprocessFileList">
										<li><a href="dowloadFile.htm?fileId=<s:property value="id"/>" target="_blank" title="点击下载"><s:property value="ywjm"/></a></li>
										</s:iterator>
									</td>
									<td><a href="<s:property value="id"/>" title="修改案件事件">修改</a>|<a href="javascript:void(0);" onclick="deltProcess('<s:property value="id"/>')">删除</a></td>
								</tr>
								</s:iterator>
								</tbody>
							</table>
							
							<p>
								<a href="javascript:void(0)" class="btn-edit" id="add-case-proc">添加案件流程</a>
							</p>
						</fieldset>
						<fieldset>
							<legend>
								案件联系人信息表
							</legend>
							<table class="case-process">
								<thead>
									<tr>
										<th class="w120">
											姓名或名称
										</th>
										<th class="w90">
											称谓
										</th>
										<th class="w90">
											联系电话
										</th>
										<th class="w90">
											手机
										</th>
										<th class="w120">
											电子邮箱
										</th>
										<th>
											通讯地址
										</th>
										<th>
											操作
										</th>
									</tr>
								</thead>
								<tbody>
								<s:iterator value="casemess.zqCasecontactModels">
									<tr>
										<td><s:property value="name"/></td>
										<td><s:property value="roleName"/></td>
										<td><s:property value="tel"/></td>
										<td><s:property value="mobile"/></td>
										<td><s:property value="email"/></td>
										<td><s:property value="address"/></td>
										<td><a href="<s:property value="id"/>" title="修改联系人信息">修改</a>|<a href="javascript:void(0)"  onclick="deltContact('<s:property value="id"/>')">删除</a></td>
									</tr>
								</s:iterator>
								</tbody>
							</table>
							
							<p>
								<a href="javascript::" class="btn-edit" id="add-case-contact">添加案件联系人</a>
								<a href="exportCaseContact.htm?caseId=<s:property value="casemess.id"/>" class="btn-edit"
									id="export-contact">导出案件联系人</a>
								
								
							</p>
						</fieldset>
					</div>
					<!--case-detail end-->
				</fieldset>
				<!--form area end-->
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#add-case-proc").click(function(){
		openwindow("addCaseProcessInit.htm?caseId=<s:property value="casemess.id"/>","添加案件事件","800","600");
	});
	$("#add-case-contact").click(function(){
		openwindow("addCaseContactInit.htm?caseId=<s:property value="casemess.id"/>","添加案件联系人","600","400");
	});
	//修改案件事件信息
	$("a[title=修改案件事件]").click(function(){
		openwindow("modifyCaseProcessInit.htm?processId="+$(this).attr("href"),"修改案件事件","800","600");
		return false;
	});
	//修改案件联系人信息
	$("a[title=修改联系人信息]").click(function(){
		openwindow("modifyCaseContacterInit.htm?caseContactId="+$(this).attr("href"),"修改案件联系人","600","400");
		return false;
	});
	$("#import-contact").click(function(){
		//导入案件联系人
		openwindow("importCaseContactInit.htm?caseId=<s:property value="casemess.id"/>","添加案件联系人","600","400");
	});
	
});



</script>