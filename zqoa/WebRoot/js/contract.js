$(document).ready(function(){
	//选择客户 弹窗 
	$("#dialog-form,.dialog-form").dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false,
		minWidth: 640,
		minHeight: 520
	});		
	
	//显示 添加客户 表单
	$("#btn-add-cust").button().toggle(
		function(){
			$("#step1-add-cust").slideDown();
		},
		function(){
			$("#step1-add-cust").slideUp();
		}
	);	
	
	//选择客户类型
	$("select[name='cust-type']").attr("selectedIndex","0");
	$("select[name='cust-type']").change(function(){
		var typeid = $(this).val();
		switch(typeid){
			case '0':
				$("#private-cust-form,#legal-cust-form,#other-cust-form").hide();
			break;
			case '1':
				$("#private-cust-form,#legal-cust-form,#other-cust-form").hide();
				$("#private-cust-form").show();
			break;	
			case '2':
				$("#private-cust-form,#legal-cust-form,#other-cust-form").hide();
				$("#legal-cust-form").show();
			break;	
			case '3':
				$("#private-cust-form,#legal-cust-form,#other-cust-form").hide();
				$("#other-cust-form").show();
			break;	
		}	
	});
		
	//日期选择器
	$("#birthday").datepicker({
		yearRange: 'c-60:c+1',
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showMonthAfterYear: true,
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
	});
	
	$(".validity").datepicker({
		yearRange: 'c-10:c+10',
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showMonthAfterYear: true,
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
	});
	
	//新增客户时的数据检测
	$(".cust-form input").blur(function(){
		if($.trim($(this).val()) !== ''){
			$(this).parent().siblings("td").html("").html("<span class=\"accept\">&nbsp;</span>");
		}
	});
	$("input[name='name']").blur(function(){
		if($.trim($(this).val()) === ''){
			$(this).parent().siblings("td").html("").html("<span class=\"tips\">该项不能为空！</span>");
			return false;	
		}else{
			$(this).parent().siblings("td").html("").html("<span class=\"accept\">&nbsp;</span>");
		}
	});	
	$("input[name='email']").blur(function(){
		if($.trim($(this).val()) != '' && !isEmail($(this).val())){
			$(this).parent().siblings("td").html("").html("<span class=\"tips\">电子邮箱格式不正确！</span>");
			return false;	
		}else{
			$(this).parent().siblings("td").html("").html("<span class=\"accept\">&nbsp;</span>");
		}	
	});	
	
	$("#cont-dialog-form").dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false,
		width: 680,
		height:600
	});
	
	//添加 收费阶段 addcontract.html
	$("#add-pay-phase").button().click(function(){
		//取得要显示的阶段数
		var phase = $(".pay-phase tbody tr:visible").length;
		//显示输入框
		$(".pay-phase tbody tr:eq("+phase+")").show();
		//当四个阶段都显示完后隐藏添加按钮
		var nowLen = $(".pay-phase tbody tr:visible").length;
		if( nowLen == 4 ){
			$(this).parent().parent().hide();
		}
	});
	
	//editCont.html
	var len = $("#hidden-phase tr").length;
	for(i=0;i<len;i++){
		$(".pay-phase tbody tr:eq("+i+")").replaceWith($("#hidden-phase tr:eq(0)"));
	}
	
	//添加一个附件 addcontract.html
	$("#ln-add-attach").click(function(){
		var htmls = "<li><input type=\"file\" name=\"files\" /> <a href=\"javascript::\" class=\"cancle-file\">取消</a></li>";
		$(this).parent().before(htmls);
	});
	$(".cancle-file").live("click",function(){
		$(this).parent().remove();
	});
});