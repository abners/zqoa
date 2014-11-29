$(document).ready(function(){		
	//边栏的 收缩/展开 功能
	//$(".sidebar-items ul").hide();
	$(".sidebar-items h4 a").click(function(){
			$(this).parent("h4").siblings("ul").toggle("blind",{},500);
		});
	$(".sidebar-items h4 a").toggle(
		function(){
			$(this).removeClass("folder").addClass("unfolder");
			},
		function(){
			$(this).removeClass("unfolder").addClass("folder");
			}
	);
	//隐藏ul li 长度为0的模块
	$(".sidebar-items").each(function(){
		var itemLen = $(this).find("ul li").length;
		if(itemLen == 0){
			$(this).hide();
		}
	});
	//头部工具栏的 收缩展开 功能
	$("#toolbar-opt").click(function(){
		$(this).siblings("#toolbar-content").toggle("blind",{},100);
		});
	$("#toolbar-opt").toggle(
		function(){
			$(this).removeClass("eject-blue").addClass("inject-blue");
			},
		function(){
			$(this).removeClass("inject-blue").addClass("eject-blue");
			}
	);
	
			
	$("#select-all").change(
		function(){
			$("input[name='arr']").attr("checked",$(this).attr("checked"));
		}
	);
	//日期选择器
	$(".datepicker").datepicker({
		yearRange: 'c-10:c+10',
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showMonthAfterYear: true,
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
	});
	//ajax发送 及 成功弹窗
	$("#ajax-action-dialog").dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		draggable: false,
		minWidth: 480
	});
});