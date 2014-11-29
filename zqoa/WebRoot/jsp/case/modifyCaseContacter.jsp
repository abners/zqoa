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

		<title>添加案件联系人</title>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
		<link rel="stylesheet" href="<%=path%>/css/case.css"/>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/case.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			
		});
		function addContact(){
			if($.trim($("#contact").val())==''){
				alert("联系人姓名为必填项!");
				return ;
			}
			if($.trim($("#tel").val())=='' && $.trim($("#mobile").val())==''){
				alert("联系电话和手机须填一项!");
			}
			if(confirm("请检查所填内容，是否确认?")){
				$.ajax({
	    			type : "post",
	    			url : "modifyCaseContact.htm",
	    			dataType : "json",
	    			data : $("#add-case-pro-dialog").serialize(),
	    			success : function(data) { //成功后返回的页面
	    				alert(data.info);
	    				if (data.status == '1'){
	    					
	    					window.opener.location.reload();
	    					window.close();
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
	
		<fieldset id="step2-add-cont" class="forms-area">
			<form action="" method="post"
				id="add-case-pro-dialog" class="add-form" >
				<input type="hidden" name="zqCasecontactModel.id" value="<s:property value="contacter.id"/>"/>
				<input type="hidden" name="zqCasecontactModel.caseId" value="<s:property value="contacter.caseId"/>"/>
				<table>
					<colgroup span="1"></colgroup>
					<tr>
						<td>
							<label for="name">
								姓名或名称 (
								<span class="require">*</span>) ：
							</label>
						</td>
						<td>
							<input type="text" id="contact" name="zqCasecontactModel.name" value="<s:property value="contacter.name"/>"/>
						</td>
						<td>
							<span class="require"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label for="role">
								称谓：
							</label>
						</td>
						<td>
							<select name="zqCasecontactModel.roleid" id="sel-role">
								<s:iterator value="identity">
									<option value="<s:property value="identityId"/>" <s:if test="identityId==contacter.roleid">selected</s:if>>
										<s:property value="identityName" />
									</option>
								</s:iterator>
							</select>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label for="tel">
								联系电话：
							</label>
						</td>
						<td>
							<input type="text" id="tel" name="zqCasecontactModel.tel" value="<s:property value="contacter.tel"/>" />
						</td>
						<td>
							<span class="require"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label for="mobile" >
								手机：
							</label>
						</td>
						<td>
							<input type="text" id="mobile" name="zqCasecontactModel.mobile" value="<s:property value="contacter.mobile"/>"/>
						</td>
						<td>
							<span class="require"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label for="email">
								电子邮箱：
							</label>
						</td>
						<td>
							<input type="text" name="zqCasecontactModel.email" value="<s:property value="contacter.email"/>"/>
						</td>
						<td>
							<span class="require"></span>
						</td>
					</tr>
					<tr>
						<td>
							<label for="address">
								通讯地址：
							</label>
						</td>
						<td span="2">
							<input id="txtAddr" type="text" name="zqCasecontactModel.address" value="<s:property value="contacter.address"/>"/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
						<p>
							<input class="btn-h28 btn-submit" id="submit-case-pro"
								type="button" value="提交" onclick="addContact()"/>
						    <input class="btn-h28 btn-submit" id="submit-case-pro"
								type="button" onclick="javascript:window.close();" value="取消" />
								</p>
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
	</body>
</html>
