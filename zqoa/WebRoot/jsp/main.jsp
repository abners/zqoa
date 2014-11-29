<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link href="<%=path%>/css/tablecloth.css" rel="stylesheet" />
		<link href="<%=path%>/css/index.css" rel="stylesheet" />
		<link href="<%=path%>/css/main.css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.5.1.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery-ui-1.8.11.custom.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tablecloth.js"></script>
		<script type="text/javascript" src="<%=path%>/js/index.js"></script>

	</head>
	<body style='overflow: scroll; overflow-x: hidden'>
		<div id="main">
			<div class="toolbar">
				<p class="toolbar-shadow">
					&nbsp;h
				</p>
				<div id="toolbar-content">
					<p class="crumb">
						<b>您的位置：</b>
						<a href="javascript:void(0)">首页</a> >

					</p>

				</div>
				<a id="toolbar-opt" class="option eject-blue"
					href="javascript:void(0);return false;">&nbsp;</a>
			</div>
			<!-- toolbar end-->
			<div class="wrap">
				<fieldset class="forms-area" id="notices-area">
					<h4>
						<span>最新公告</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div id="notice-ct" class="content">
						<ul>
							<li>
								<a href="javascript:void(0);">公告测测试</a><a
									href="javascript:void(0);" class="notice-ct-toggle">(展开内容)</a>

								<div>
									<p>
										欢迎使用中勤律师事务所OA系统！
									</p>
									<p>
										<span>发布人：</span>
										<span> | 发布时间：2013-01-19 01:36:42</span>
									</p>
								</div>
							</li>
						</ul>
					</div>
				</fieldset>
				<!--notices-area end-->
				<fieldset class="forms-area" id="remind-area">
					<h4>
						<span>近期提醒</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<h2 style="padding: 15px 30px">
					<s:if test="%{#session.caseprocess.size==0}">
						<span class="info" style="padding-left: 20px">暂无提醒</span>
						</s:if>
						<s:else>
						<div class="content">
						<table id="info-list">
							<tr>
								<th class="w180">
									提醒结束时间
								</th>
								<!--th>内容</th>-->
								<th class="w180">
									内容
								</th>
								
							</tr>
							
							<s:iterator value="%{#session.caseprocess}">
							<tr>
								<td><s:property value="remTime"/></td>
								<td><s:property value="remContent"/></td>
							</tr>
							</s:iterator>
							</table>
							</div>
						</s:else>
					</h2>
				</fieldset>
				<!--remind-area end-->

				<%--<fieldset class="forms-area" id="msg-area">
					<h4>
						<span>我的收件箱</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content">
						<table id="info-list">
							<tr>
								<th class="w180">
									标题
								</th>
								<!--th>内容</th>-->
								<th class="w180">
									发件人
								</th>
								<th class="w180">
									收信时间
								</th>
								<th class="w180">
									操作
								</th>
							</tr>
							<tr>
								<td>
									<a href="######">435</a>
								</td>
								<!--<td>hjghjgjh</td>-->
								<td>
									测试
								</td>
								<td>
									2013-09-01 08:31:06
								</td>
								<td>

									<a class="detail-link" href="#" title="查看详情"
										onclick="javascript:window.open('/zqoa/index.php/Msg/detail/id/8','查看详情', 'height=500,width=700,status=no,toolbar=no,menubar=no,location=no,scrollbars=no');">
										查看详情</a>
									<a class="detail-link" href="#" title="回复消息"
										onclick="javascript:window.open('/zqoa/index.php/Msg/reply/id/8','回复消息', 'height=500,width=700,status=no,toolbar=no,menubar=no,location=no,scrollbars=no');">
										回复</a>
									<a class="detail-link" href="/zqoa/index.php/Msg/del/id/8"
										title="删除消息">删除</a>
								</td>
							</tr>
						</table>
						<span class="msg" style="padding-left: 20px;"><a href="#"
							onclick="javascript:window.open('/zqoa/index.php/Msg/send','发送消息', 'height=500,width=700,status=no,toolbar=no,menubar=no,location=no,scrollbars=no');">发送新消息</a>
						</span>
					</div>
				</fieldset>
				--%><!--msg-area end-->
				<fieldset class="forms-area" id="work-area">
					<h4>
						<span><s:if test="#session.user.isManage==0">我承办的未结案件</s:if><s:else>当前有最新进展的未结案件</s:else></span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content work-ct">
					<s:if test="#session.index.zqNoOverCaseModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办案件记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">案件编号</th>
								<th>案件名称</th>
								<th class="w180">案件类型</th>
								<th class="w180">主办律师</th>
							</tr>
							<s:iterator value="#session.index.zqNoOverCaseModels">
							<tr>
								<td><a class="detail-link" href="<s:if test="contId!=null">caseDetail.htm</s:if><s:else>caseBeforeContDetail.htm</s:else>?caseId=<s:property value="id"/>" title=""><s:property value="number"/></a></td>
								<td><s:property value="caseName"/></td>
								<td><s:property value="typeName"/></td>  
								<td><s:property value="userName"/></td>                      

							</tr>
							</s:iterator>
						</table>	
					</s:else>	
					</div>
				</fieldset>
				<!--work-area end-->
				<fieldset class="forms-area" id="work-area">
					<h4>
						<span>我承办的已结案件</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content work-ct">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办案件记录</span>
						</h2>
					</div>
				</fieldset>
				<!--work-area end-->

				<fieldset class="forms-area" id="work-area">
					<h4>
						<span><s:if test="#session.user.isManage==0">我承办的未结合同</s:if><s:else>当前有最新进展的未结合同</s:else></span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content work-ct">
					<s:if test="#session.index.zqNoOverContractModels.size==0">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办合同记录</span>
						</h2>
					</s:if>
					<s:else>
						<table id="info-list">
							<tr>
								<th class="w240">合同编号</th>
								<th>合同名称</th>
								<th class="w180">客户名称</th>
								<th class="w180">主办律师</th>
								<%--<th class="w60">操作</th>
							--%></tr>
							<s:iterator value="#session.index.zqNoOverContractModels">
							<tr>
								<td><a class="detail-link" href="contractDetail.htm?contId=<s:property value="id"/>" title=""><s:property value="number"/></a></td>
								<td><s:property value="contName"/></td>
								<td><s:property value="custName"/></td>  
								<td><s:property value="lawyerName"/></td>                      
								
							</tr>
							</s:iterator>
						</table>	
					</s:else>	
					</div>
				</fieldset>
				<!--work-area end-->
				<fieldset class="forms-area" id="work-area">
					<h4>
						<span>我承办的已结合同</span>
						<a class="btn-toggle" href="javascript:void(0);" title="展开/收缩"></a>
					</h4>
					<div class="content work-ct">
						<h2 style="padding: 15px 30px">
							<span class="info" style="padding-left: 20px">无承办合同记录</span>
						</h2>
					</div>
				</fieldset>
				<!--work-area end-->
			</div>
			<!--wrap end-->
		</div>
		<!-- main-->
		<div class="clean"></div>
		</div>
		<!--content end-->
	</body>
</html>