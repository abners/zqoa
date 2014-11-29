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

		<title>添加合同附件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>
		<link rel="stylesheet" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/case.css" />
		<script src="<%=request.getContextPath()%>/js/upload/swfupload.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/swfupload.queue.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/fileprogress.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/upload/handlers.js"></script>
		<link href="<%=request.getContextPath()%>/css/default.css" rel="stylesheet" type="text/css" />

	</head>
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
				url : "addContFile.htm",
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
		}

	}
	function addContFile(){
		if($("#SWFUpload_0_0").length!=0){
			//执行上传功能
			swfu.startUpload();
			return true;
		}else{
			alert("请选择要上传的文件!");
		}
	}
</script>
	<body>
	<form method="post" enctype="multipart/form-data" id="add-case-pro-dialog" class="add-form" >
		<input type="hidden" name="fileids" id="fileids" />
		<input type="hidden" name="zqBusFileModel.busId" value="<s:property value="contId"/>"/>
		<div class="fieldset flash" id="fsUploadProgress">
			<span class="legend">添加合同附件</span>
			<div id="divStatus">
				已上传0个文件
			</div>
			<div>
				<span id="spanButtonPlaceHolder"></span>
			</div>
		</div>
		<p>
		<input class="btn-h28 btn-submit" id="submit-case-proc" onclick="addContFile()" type="button" value="添加" />
		<input class="btn-h28 btn-submit" id="submit-case-proc" type="button" value="取消" onclick="javascript:window.close();"/>
		</p>
	</form>
	</body>
</html>
