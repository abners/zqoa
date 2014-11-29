<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合同事务登记</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
  </head>
  
  <body>
    <!-- sidebar-->
        <div id="main">
            <div class="toolbar">
            	<p class="toolbar-shadow">&nbsp;</p>
                <div id="toolbar-content">
                    <p class="crumb">
                        <b>您的位置：</b>
                        <a href="toIndex.htm">首页</a> >
                        <a href="contractlist.htm">合同管理</a>>
                        <a href="javascript:void(0);">合同事务登记</a>
                    </p>  
                </div>
                <a id="toolbar-opt" class="option eject-blue" href="javascript:void(0);return false;">&nbsp;</a>
            </div><!-- toolbar end-->
            <div class="wrap">
            	<fieldset class="forms-area">
                	<h4>事物记录</h4>
                    <form action="" method="post" id="addId" class="add-form">
                    	<table>
                   			<colgroup span="1"></colgroup>
							<tr>
								<th>选择合同类型：</th>
								<td>
									<select name="contTypeId" id="contType" style="width:200px;">
										<option value="-1">未选择合同类型</option>
										<s:iterator value="contType">
											<option value="<s:property value="contractTypeId"/>"><s:property value="contractTypeName"/></option>
										</s:iterator>
									</select><span ><font color='red' id="nocont"></font></span>
								</td>
							</tr>
							<tr id="sty1">
								<th>选择合同：</th>
								<td>									
									<select name ="zqContractAffairModel.contractId" id="contId" style="width:200px;">
									</select>
								</td>			
							</tr>
							
                            <tr>
                            	<th>时间：</th>
                                <td><s:property value="todaydate" /></td>
                            </tr>
							<tr>
								<th>事物记录：</th>
								<td><textarea name="zqContractAffairModel.affairContent" style="width:400px;" id="content" ></textarea></td>
							</tr>
                            <tr>
                            	<th>&nbsp;</th>
                                <td><input id="btn-submit" type="button" name="submit" value="提交" /></td>
                            </tr>
                        </table>
                       </form>
                </fieldset>

  </body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	//获取合同
	$("#contType").change(function(){
		 var typeId = $(this).val();
		 if(typeId=='-1'){
			 $("#contId").empty();
			 $("#nocont").text("");
			 return ;
		 }else{
			 $.ajax({
					type : "post",
					url : "getContractByTypeId.htm",
					dataType : "json",
					data : {
						contTypeId:typeId
					},
					success : function(data) { //成功后返回的页面
						$("#contId").empty();
						var item=eval(data.item);
						if(item.length==0){
							$("#nocont").text("该合同类型下尚无合同")
						}else{
							$("#nocont").text("");
							$.each(item,function(idex,item){
								$("#contId").append("<option value='"+item.contId+"'>"+item.contName+"</option>");
							});
						}
						
					},
					error:function(error){
						alert("网络异常，请重新登录!");
					}
				});
		 }
		 
	});
	$("#btn-submit").click(function(){
		if($("#contType").val()=='-1'){
			alert("请选择合同类型!");
			return ;
		}
		if($("#contId option").length==0){
			alert("没有可供选择合同，无法添加事务!");
			return ;
		}
		if(confirm("是否确认添加?")){
			$.ajax({
				type : "post",
				url : "addContAffair.htm",
				dataType : "json",
				data :$("#addId").serialize(),
				success : function(data) { //成功后返回的页面
					if(data.status==1){
						alert("添加成功!");
						window.location.href="contAffairList.htm";
					}else{
						alert("添加失败，请稍后重试！");
					}
					
					
				},
				error:function(error){
					alert("网络异常，请重新登录!");
				}
			});
		}
		
	});
});

</script>
