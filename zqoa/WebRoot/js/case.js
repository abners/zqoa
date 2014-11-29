$(document).ready(function(){
	/*$(".dialog-form").dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false,
		minWidth: 640,
		minHeight: 480
	});
	
	//ajax发送 及 成功弹窗
	$("#ajax-action-dialog").dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false,
		minWidth: 480
	});*/
	
	//detail.html
	/*$(".btn-submit,.btn-edit").button();*/
	$("#add-case-proc").click(function(){
		$("#add-case-pro-dialog").dialog({minWidth:640,minHeight:480}).dialog("open");
	});
	$("#add-case-contact").click(function(){
		$("#add-case-contact-dialog").dialog({minWidth:600}).dialog("open");
	});
	$("#add-file").click(function(){
		target = $(this).parent();
		$("<p class=\"file-field\"><input type=\"file\" name=\"file[]\" /><a href=\"javascript::\" class=\"cancle-file\" title=\"取消附件\">取消</a></p>").insertAfter(target);
	});
	$(".cancle-file").live("click",function(){
		$(this).parent().detach();
	});
	//案件流程提醒
	$("#cb-remind").change(function(){
		tmp = $(this).attr("checked") ? $("#remind-detail").slideDown() : $("#remind-detail").slideUp(); 		
	});
	//添加案件事件验证
	/*$("#submit-case-proc").click(function(){
		var content = $("#add-case-pro-dialog textarea").val();
		if($.trim(content) == ""){
			alert("事件内容不能为空！");
			return false;
		}
	});*/
	//删除事件
	$(".ln-del-process").click(function(){
		if(!confirm("此操作将不可恢复，确定要删除吗？")){
			return false;
		}
	});
	//添加案件联系人数据验证
	/*$("#submit-case-contact").click(function(){
		var name = $("#add-case-contact-dialog input[name='name']").val();
		var tel = $("#add-case-contact-dialog input[name='tel']").val();
		var mobile = $("#add-case-contact-dialog input[name='mobile']").val();
		var email = $("#add-case-contact-dialog input[name='email']").val();
		if($.trim(name) == ''){
			$("#add-case-contact-dialog input[name='name']").parent().next().children().html("该项为必填项！");
			$("#add-case-contact-dialog input[name='name']").focus();
			return false;
		}
		if($.trim(tel) == "" && $.trim(mobile) == ""){
			$("#add-case-contact-dialog input[name='tel']").parent().next().children().html("联系电话和手机须填一项！");
			$("#add-case-contact-dialog input[name='tel']").focus();
			return false;
		}
	});*/
	//删除联系人
	$(".ln-del-contact,.del-link").click(function(){
		if(!confirm("此操作将不可恢复，确定要删除吗？")){
			return false;
		}
	});
	//结案
	$(".ln-archive").click(function(){
		if(!confirm("此操作将使案件及相关资料归入档案，确定结案吗？")){
			return false;	
		}
	});
})
