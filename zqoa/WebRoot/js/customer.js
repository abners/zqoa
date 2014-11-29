$(document).ready(function(){
	$(".ln-del").click(function(){
		if(!confirm("此操将不可恢复，确认删除？")){
			return false;	
		}
	});
})
