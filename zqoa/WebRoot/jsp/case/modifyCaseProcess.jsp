<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加案件流程事件</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/case.css" />
		<script src="<%=request.getContextPath()%>/js/calendar/WdatePicker.js"></script>
		<script src="<%=request.getContextPath()%>/js/upload/swfupload.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/swfupload.queue.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/fileprogress.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/handlers.js"></script>
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<script type="text/javascript">
	// This event comes from the Queue Plugin
	function queueComplete(numFilesUploaded) {
		var status = document.getElementById("divStatus");
		status.innerHTML = "已上传" + numFilesUploaded + "个文件.";
		//上传成功
		if (numFilesUploaded != null && numFilesUploaded != 0) {
			//window.location.href="2332.htm";
			//添加案件事件
			$.ajax({
				type : "post",
				url : "modifyCaseProcess.htm",
				dataType : "json",
				data : $("#add-case-pro-dialog").serialize(),
				success : function(data) { //成功后返回的页面
					alert(data.info);
					if (data.status == '1') {
						window.opener.location.reload();
						window.close();
					}
				},
				error : function(error) {
					//alert(error.status);
					alert("网络异常，请重新登录!");
				}
			});
		} else {
			$("#fileids").val("");
		}

	}
	function addProcess() {
		if ($("#process").val() == '') {
			alert("请选择时间!");
			return false;
		}
		//alert($("#cb-remind").attr("checked"));
		//事件提醒
		if ($("#cb-remind").attr("checked")) {
			if ($.trim($("#remind-ct").val()) == '') {
				alert("请填写提醒内容!");
				return;
			}
			if ($.trim($("#remind-time").val()) == '') {
				alert("请选择提醒结束时间!");
				return false;
			}
			$("#isTimeRemender").val("1");

		} else {
			//没有定时提醒
			$("#isTimeRemender").val("0");
		}
		if (confirm("请检查所填内容，是否确认修改?")) {
			if ($("#SWFUpload_0_0").length != 0) {
				//执行上传功能
				swfu.startUpload();
				return true;
			} else {
				//添加案件事件
				$.ajax({
					type : "post",
					url : "modifyCaseProcess.htm",
					dataType : "json",
					data : $("#add-case-pro-dialog").serialize(),
					success : function(data) { //成功后返回的页面
						alert(data.info);
						if (data.status == '1') {
							window.opener.location.reload();
							window.close();
						}
					},
					error : function(error) {
						//alert(error);
						alert("网络异常，请重新登录!");
					}
				});
			}

		}

	}
	//ondragstart="window.event.returnValue=false" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false"
</script>
	</head>

	<body>
		<fieldset id="step2-add-cont" class="forms-area">
			<form action="" method="post" enctype="multipart/form-data"
				id="add-case-pro-dialog" class="add-form">
				<input type="hidden" name="zqCaseprocessModel.caseId" id="caseId"
					value="<s:property value="caseProcess.caseId"/>" />
				<input type="hidden" name="fileids" id="fileids" value=""/>
				<input type="hidden" name="zqCaseprocessModel.isTimeRemender"
					id="isTimeRemender" value="" />
				<input type="hidden" name="zqCaseprocessModel.id"
					 value="<s:property value="caseProcess.id"/>" />
				<p>
					时间: &nbsp;&nbsp;&nbsp;
					<input type="text" readonly="readonly"
						value="<s:property value="caseProcess.processTime"/>" name="zqCaseprocessModel.processTime" id="process" class="validity" onclick="WdatePicker({el:'process'})" class="validity"/>
				</p>
				<p>
					事件:
				</p>
				<p>
					<textarea name="zqCaseprocessModel.content" id="content"><s:property value="caseProcess.content" /></textarea>
				</p>
				<p>
					提醒：
					<input name="remind" id="cb-remind" type="checkbox"
						<s:if test="caseProcess.isTimeRemender==1">checked</s:if> />
					定时提醒
				</p>
				<div id="remind-detail"
					<s:if test="caseProcess.isTimeRemender==1">style="display:block"</s:if>>
					<p>
						提醒结束时间： &nbsp;&nbsp;&nbsp;
						<input id="remind-time" readonly="readonly"
							value="<s:property value="caseProcess.remTime"/>"
							onclick="WdatePicker({el:'remind-time'})"
							name="zqCaseprocessModel.remTime" type="text" class="validity" />
					</p>
					<p>

						提醒内容：
					</p>
					<p>
						<textarea id="remind-ct" name="zqCaseprocessModel.remContent"><s:property value="caseProcess.remContent" /></textarea>
					</p>

				</div>
				<p>
									附件: <s:iterator value="caseProcess.zqprocessFileList">
											<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"><s:property value="ywjm"></s:property></a>&nbsp;&nbsp;<a href="<s:property value="id"/>" title="删除">删除</a></p>
										 </s:iterator>
								</p>
								<p>
								</p>
				<p>
				<div class="fieldset flash" id="fsUploadProgress">
					<span class="legend">上传文件</span>
					<div id="divStatus">
						已上传0个文件
					</div>
					<div>
						<span id="spanButtonPlaceHolder"></span>
					</div>
				</div>

				</p>
				<p>
					<input class="btn-h28 btn-submit" id="submit-case-proc"
						onclick="addProcess()" type="button" value="提交" />
					<input class="btn-h28 btn-submit" id="submit-case-proc"
						type="button" value="取消" onclick="javascript:window.close();" />
				</p>

			</form>
		</fieldset>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//删除附件标签
	$("a[title=删除]").click(function(){
		$(this).parents("p").remove();
		$("#add-case-pro-dialog").append("<input type='hidden' name='deltFileIds' value='"+$(this).attr("href")+"'/>");
		return false;
	});
});
</script>