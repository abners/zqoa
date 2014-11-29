$(document).ready(function(){
	tablecloth();
	//展开收缩栏目
	$(".btn-toggle").click(function(){
		$(this).parent("h4").siblings("div").slideToggle("slow");
	});
	//展开收缩公告内容
	$(".notice-ct-toggle").live("click",function(){				
		if($(this).siblings("div").css("display") == "none"){
			var htmls = "<a href=\"javascript:void(0);\" class=\"notice-ct-toggle\">(收起内容)</a>";
			$(this).siblings("div").after(htmls);
			$(this).siblings("div").slideDown("slow");
		}else{
			$(this).siblings("div").slideUp("slow");
			var htmls = "<a href=\"javascript:void(0);\" class=\"notice-ct-toggle\">(展开内容)</a>";
			$(this).siblings("a").after(htmls)
		}
		$(this).remove();
	});
	//模块移动
	$(".wrap").sortable({
		axis: 'y',
		connectWith: ".wrap",
		cursor: 'crosshair',
		forcePlaceholderSize: true,
		handle: 'h4',
		items: 'fieldset',
		opacity: 0.6,
		placeholder: "ui-state-highlight",
		stop: function(event, ui) {
			
		}
	});
});