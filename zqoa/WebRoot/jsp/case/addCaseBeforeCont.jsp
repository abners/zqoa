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

		<title>添加合同前案件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/case.css" />
		
		<link rel="stylesheet" href="<%=path%>/css/pagination.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
						<a href="/zqoa/index.php/">首页</a> >

						<a href="/zqoa/index.php/contract">案件管理</a> >
						<a href="#">添加合同前案件</a>
					</p>


				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<%--<div class="guide">
				<ul>
					<li class="steps">
						<h3>
							步骤一
						</h3>
						<p>
							添加客户
						</p>
					</li>
					<li class="arrow">
						&nbsp;
					</li>
					<li class="steps">
						<h3>
							步骤二
						</h3>
						<p>
							添加案件
						</p>
					</li>
					<li class="arrow back-arrow">
						&nbsp;
					</li>
					<li class="curt-step">
						<h3>
							步骤三
						</h3>
						<p>
							添加案件
						</p>
					</li>
					<li class="arrow curt-arrow">
						&nbsp;
					</li>
				</ul>
			</div>
			--%><!--guide end-->
			<div class="wrap">
				<fieldset id="step2-add-cont" class="forms-area">
					<h4>
						添加案件
					</h4>
					<form action="" method="post" id="Case-form" class="add-form">
						<table>
							<colgroup span="1"></colgroup>
							
							<tr>
								<th>
									案件名称：
								</th>
								<td>
									<input type="text" name="zqCaseModel.caseName" id="caseName" />
								</td>
							</tr>
							<tr>
								<th>
									案件编号：
								</th>
								<td>
									<input type="text" name="zqCaseModel.number" id="number"/>
								</td>
							</tr>
							
							<tr>
								<th>
									案件类型：
								</th>
								<td>
									<select name="zqCaseModel.typeId" id="Case-type">
										<s:iterator value="casetype">
										<option value="<s:property value="typeId"/>">
											<s:property value="typeName"/>
										</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									客户名称：
								</th>
								<td>
									<select name="zqCaseModel.custId" id="Case-type">
										<s:iterator value="cust">
										<option value="<s:property value="id"/>">
											<s:property value="name"/>
										</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									当事人身份：
								</th>
								<td>
									<select name="zqCaseModel.litigant">
										<s:iterator value="caseidentity">
										<option value="<s:property value="identityId"/>">
											<s:property value="identityName"/>
										</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									主办律师：
								</th>
								<td>
									<select name="zqCaseModel.lawyer">
									<s:iterator value="lawyer">
										<option value="<s:property value="id"/>">
										<s:property value="name"/>
										</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									协办律师：
								</th>
								<td>
									<ul class='entries-list' id="lawyers-list"></ul>
									<input id="btn-sub-lawyer" class="ui-button btn-h28"
										type="button" value="选择律师" />
								</td>
							</tr>
							<tr>
								<th>
									关联案件：
								</th>
								<td>
									<ul class='entries-list' id="relative-cont-list"></ul>
									<input id="btn-relat" class="ui-button btn-h28" type="button"
										value="选择案件" />
								</td>
							</tr>
							<tr>
								<th>
									备注：
								</th>
								<td colspan="2">
									<textarea name="zqCaseModel.notes"></textarea>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td colspan="2">
									<%--<input type="hidden" name="cid" value="338" />
								--%></td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input id="btn-add-Case" class="btn-submit" type="button"
										name="submit" value="提交" />
								</td>
							</tr>
						</table>
						<input type="hidden" name="zqCaseModel.relative" id="relative"/>
					</form>
					<!-- Case-form end -->
					
				</fieldset>
				<s:iterator value="cont">
				<input type="hidden" value="<s:property value="custName"/>" id="<s:property value="id"/>"/>
				<input type="hidden" value="<s:property value="custId"/>" id="<s:property value="id"/>s"/>
				</s:iterator>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn-sub-lawyer").click(function(){
		openwindow("getCo_counsel.htm","协办律师列表",700,400);
	});
	//关联案件
	$("#btn-relat").click(function(){
		openwindow("getRelativeCase.htm","关联案件",700,400);
	});
	$("#btn-add-Case").click(function(){
		if($.trim($("#caseName").val())==''){
			alert("请填写案件名称!");
			return ;
		}

		if(confirm("请检查所填数据,是否确认添加?")){
			var relative = "";
			var list = $("#relative-cont-list").children();
			if(list.length>0){
				list.each(function(){
					relative += $(this).find("input[name=relativeCaseId]").val()+",";
				});
			}
			//关联案件
			$("#relative").val(relative.substring(0,relative.length-1));
			$.ajax({
				type : "post",
				url : "addCase.htm",
				dataType : "json",
				data : $("#Case-form").serialize(),
				success : function(data) { //成功后返回的页面
					//alert(data.status);
					if (data.status == '0') {
						alert("添加失败，请稍后重试!");
					} else {
						alert("案件添加成功!");
						window.location.href=window.location;
					}
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
		
	});
	///取消选择
	$("#lawyers-list a[title=取消]").live("click",function(){
		$(this).parent().remove();
	});
	$("#relative-cont-list a[title=取消]").live("click",function(){
		$(this).parent().remove();
		
	});
	
});
</script>