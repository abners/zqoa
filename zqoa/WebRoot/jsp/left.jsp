<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>管理页面</title>

<script src="../js/prototype.lite.js" type="text/javascript"></script>
<script src="../js/moo.fx.js" type="text/javascript"></script>
<script src="../js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #3B5998;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(../images/menu_bgS.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM li span {
	margin-right:120px;
	
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #3B5998;
	background-image: url(../images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #3B5998;
	background-image: url(../images/menu_bg1.gif);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(../images/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #FF5809;
	background-image: url(../images/menu_bg2.gif);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#E3F3FA">

  <tr>
    <td width="182" valign="top">
    <div id="container">
      <h1 class="type"><a href="javascript:void(0)">合同管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM"><%--
          <li><span><a href="#">站内搜索</a></span></li>
          --%><li><span><a href="lawyerSelect.htm" target="main">律师承办查看</a></span></li>
         <li><a href="searchMessage.jsp" target="main">站内搜索</a></li>
          <s:if test="%{(#session.user.powers).indexOf('A1')>=0}">
          <li><a href="addContractInit.htm" target="main">添加合同</a></li>
          </s:if>
          <s:if test="%{(#session.user.powers).indexOf('A4')>=0}">
          <li><a href="contractlist.htm" target="main">查看合同</a></li>
          </s:if>
          <li><a href="contAffairRegInit.htm" target="main">合同事务登记</a></li>
          <li><a href="contAffairList.htm" target="main">查看合同事务记录</a></li>
          <s:if test="%{(#session.user.powers).indexOf('A5')>=0}">
          <li><a href="listContractType.htm" target="main">合同分类管理</a></li>
          </s:if>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">案件管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
        <s:if test="%{(#session.user.powers).indexOf('B2')>=0}">
          <li><a href="addCaseInit.htm" target="main">添加案件</a></li>
         </s:if>
         <s:if test="%{(#session.user.powers).indexOf('B5')>=0}">
          <li><a href="listCase.htm" target="main">查看案件</a></li>
         </s:if>
          <li><a href="listEndCase.htm" target="main">查看完结案件</a></li>
          <s:if test="%{(#session.user.powers).indexOf('B1')>=0}">
          <li><a href="caseType.htm" target="main">案件分类管理</a></li>
          </s:if>
          <li><a href="caseContactIdentity.htm" target="main">案件联系人身份管理</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">合同前案件管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="addCaseBeforeContInit.htm" target="main">添加案件</a></li>
          <li><a href="listCaseBeforeCont.htm" target="main">查看案件</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">档案管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
		  <li><a href="listArchives.htm" target="main">查看档案</a></li>
          <li><a href="addArchiveInit.htm" target="main">添加档案</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">客户管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="addCustomer.htm" target="main">添加客户</a></li>
          <li><a href="listCustomer.htm" target="main">查看当前客户</a></li>
          <li><a href="listExpiredCustomer.htm" target="main">查看原来客户</a></li>
        </ul>
      </div>
    </div>
    <s:if test="%{(#session.user.powers).indexOf('D1')>=0||(#session.user.powers).indexOf('D2')>=0||(#session.user.powers).indexOf('D3')>=0||(#session.user.powers).indexOf('D4')>=0||(#session.user.powers).indexOf('D5')>=0||(#session.user.powers).indexOf('D6')>=0}">
     <h1 class="type"><a href="javascript:void(0)">人事管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
         <s:if test="%{(#session.user.powers).indexOf('D1')>=0}">
            <li><a href="addUserInit.htm" target="main">添加用户</a></li>
         </s:if>
         <s:if test="%{(#session.user.powers).indexOf('D6')>=0}">
          <li><a href="userGroupManage.htm" target="main">用户组管理</a></li>
          </s:if>
          <li><a href="listUser.htm" target="main">查看用户资料</a></li>
          <li><a href="listDeparts.htm" target="main">查看公司部门</a></li>
          
        </ul>
      </div>
      </s:if>
       <h1 class="type"><a href="javascript:void(0)">行政管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="asr.htm" target="main">行政事务登记</a></li>
          <li><a href="listAsr.htm" target="main">行政事务管理</a></li>
          <li><a href="announce.htm" target="main">发布公告</a></li>
          <li><a href="announceList.htm" target="main">管理公告</a></li>
          <%--<li><a href="serApplication.htm" target="main">事务申请</a></li>
          <li><a href="/zqoa/index.php/User/editPersonalinfo">申请管理</a></li>
          --%><li><a href="addSerRecordInit.htm" target="main">每日事务记录</a></li>
          <li><a href="listSerRecord.htm" target="main">查看事务记录</a></li>
        </ul>
      </div>
      <%--<h1 class="type"><a href="javascript:void(0)">财务管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="/zqoa/index.php/User/addUser">财务登记</a></li>
          <li><a href="/zqoa/index.php/User/listType">财务管理</a></li>
        </ul>
      </div>
      --%><h1 class="type"><a href="javascript:void(0)">资产管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
        <s:if test="%{(#session.user.powers).indexOf('G2')>=0}">
            <li><a href="addAssetsInit.htm" target="main">添加资产</a></li>
        </s:if>
          <li><a href="assetsList.htm" target="main">查看资产</a></li>
          <s:if test="%{(#session.user.powers).indexOf('G5')>=0}">
          <li><a href="assetsType.htm" target="main">资产分类管理</a></li>
          </s:if>
        </ul>
      </div>
       <h1 class="type"><a href="javascript:void(0)">常用工具管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="toolsList.htm" target="main">常用工具</a></li>
          <li><a href="addToolsInit.htm" target="main">添加工具</a></li>
          <li><a href="toolsTypeManage.htm" target="main">工具分类管理</a></li>
        </ul>
      </div>
      <h1 class="type"><a href="javascript:void(0)">个人管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="modifyPersonalMess.htm" target="main">修改个人资料</a></li>
          <li><a href="modifyMyPass.htm" target="main">修改登录密码</a></li>
          <li><a href="exitSystem.htm" target="main">退出系统</a></li>
        </ul>
      </div>
      </div>
      
        <script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
        </td>
  </tr>
</table>
</body>
</html>
