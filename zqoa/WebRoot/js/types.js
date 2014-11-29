// 各种分类/类型管理通用
$(document).ready(function(){
	//列表 表格的样式控制
	tablecloth();
	
	//添加分类 样式化按钮
	$("#btn-add,#btn-add-cate").button();
	
	//样式化修改按钮
	$(".btn-edit-cate,.btn-cancle-edit").button();
		
	//合同类型管理
	$(".cate-info").hide();
	$("#btn-add-cate").click(function(){
		$(".cate-info").show("slow");
	});
	
	//显示修改框
	$("a[title='修改']").click(function(){
		$(this).parent("td").siblings("td").children("span").hide();	
		$(this).parent("td").siblings("td").children("span.hidden-field").show();	
	});
	
	//取消修改
	$(".btn-cancle-edit").click(function(){
		$(this).parent("span").hide();	
		$(this).parent("span").siblings("span").show();		
	});
});