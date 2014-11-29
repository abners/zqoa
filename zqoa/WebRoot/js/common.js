// 公共函数

//检测email地址格式
function isEmail(str){
	res = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 
	var re = new RegExp(res); 
	return !(str.match(re) == null); 	
}
/**
 * 弹出窗口
 * @param theURL
 * @param winName
 * @param features
 * @return
 */
function MM_openBrWindow(theURL,winName,features) { //v2.0
	  window.open(theURL,winName,features);
	}
/**
 * 弹出居中窗口
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 */
function openwindow(url,name,iWidth,iHeight)
{
var url; //转向网页的地址;
var name; //网页名称，可为空;
var iWidth; //弹出窗口的宽度;
var iHeight; //弹出窗口的高度;
var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}
/**
 * 
 * span超长值隐藏显示
 * id:span的id
 * maxLen:长度,默认长度为10
 */
function showTdTitle(id,maxLen){
	if (maxLen == undefined || maxLen == '' || maxLen == null) {
		maxLen = 10;
	}
	$("span[id='"+id+"']").each(function(){
		var content = $(this).html();
		if(content.length > maxLen){
			$(this).html(content.substring(0,maxLen)+"...");
			$(this).attr("title",content);
		}
	});
}
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