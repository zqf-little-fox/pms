<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language='javascript'>
	var curopenItem = '1';
</script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/menu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<base target="main" />
</head>
<body target="main">
	<table  width='99%' height="100%" border='0' cellspacing='0' cellpadding='0' >
        <tr><td style='padding-left:3px;padding-top:8px' valign='top' id="menuss">
		<c:forEach items="${sessionScope.sourceslist}" var="secondSources" varStatus="index">
		<dl class='bitem'>
			<c:if test="${index.count == 1}">
				<dt onclick=showHide("items${index.count}_1")><b>${secondSources.name}</b></dt>
				<dd style='display:block' class='sitem' id=items${index.count}_1>
					<ul class='sitemu' id=${index.index}>
						<c:forEach items="${secondSources.children}" var="sources">
							<li><a href='${sources.url}' target='main'>${sources.name}</a> </li>
						</c:forEach>
					</ul>
				</dd>
			</c:if>
			<c:if test="${index.count != 1}">
				<dt onclick=showHide("items${index.count}_1")><b>${secondSources.name}</b></dt>
				<dd style='display:none' class='sitem' id=items${index.count}_1>
					<ul class='sitemu' id=${index.index}>
						<c:forEach items="${secondSources.children}" var="sources">
							<li><a href='${sources.url}' target='main'>${sources.name}</a> </li>
						</c:forEach>
					</ul>
				</dd>
			</c:if>
		</dl>
		</c:forEach>
				<%--<dl class='bitem'>
					<dt onclick=showHide("items1_1")><b>项目管理</b></dt>
					<dd style='display:block' class='sitem' id=items1_1>
						<ul class='sitemu' id=0>
						<li><a href='${pageContext.request.contextPath}/pro/list' target='main'>基本信息管理</a> </li>
						<li><a href='${pageContext.request.contextPath}/anal/list' target='main'>需求分析管理</a> </li>
						<li><a href='${pageContext.request.contextPath}/mod/list' target='main'>模块管理</a> </li>
						<li><a href='${pageContext.request.contextPath}/func/list'>功能管理</a> </li>
						<li><a href='${pageContext.request.contextPath}/attach/list' target='main'>附件管理</a> </li>
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt onclick=showHide('items2_1')><b>日常办公</b></dt>
					<dd style='display:none' class='sitem' id=items2_1>
						<ul class='sitemu' id=1>
							<li><a href='${pageContext.request.contextPath}/task-add.jsp' target='main'>创建任务</a> </li>
							<li><a href='${pageContext.request.contextPath}/task/list' target='main'>任务信息</a> </li>
							<li><a href='${pageContext.request.contextPath}/task/myList' target='main'>我的任务</a> </li>
							<li><a href='notice.jsp' target='main'>通知公告</a></li>
							<li><a href='${pageContext.request.contextPath}/archives/list' target='main'>档案管理</a> </li>
							<li><a href='message-give.jsp' target='main'>消息推送</a> </li>
							<li><a href='message-give.jsp' target='main'>报销管理</a> </li>
							<li><a href='baoxiao-task.jsp' target='main'>报销审批</a> </li>
							<li><a href='${pageContext.request.contextPath}/bx/list' target='main'>我的报销</a> </li>
							<li><a href='${pageContext.request.contextPath}/fatie.jsp' target='main'>发帖页面</a> </li>
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt onclick=showHide("items3_1")><b>信息箱</b></dt>
					<dd style='display:none' class='sitem' id=items3_1>
						<ul class='sitemu' id=2>
							<li><a href='message-seed.jsp' target='main'>发信息</a> </li>
							<li><a href='${pageContext.request.contextPath}/email/list' target='main'>发件箱</a> </li>
							<li><a href='${pageContext.request.contextPath}/email/giveList'>收件箱</a> </li>
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt onclick=showHide("items4_1")><b>客户信息管理</b></dt>
					<dd style='display:none' class='sitem' id=items4_1>
						<ul class='sitemu' id=3>
							<li><a href='${pageContext.request.contextPath}/cust/list' target='main'>客户信息</a> </li>
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt onclick=showHide("items5_1")><b>系统管理</b></dt>
					<dd style='display:none' class='sitem' id=items5_1>
						<ul class='sitemu' id=4>
							<li><a href='user.jsp' target='main'>人员管理</a> </li>
							<li><a href='pm.jsp' target='main'>权限维护</a> </li>
							<li><a href='${pageContext.request.contextPath}/role/list' target='main'>角色管理</a> </li>
						</ul>
					</dd>
				</dl>


				<dl class='bitem'>
					<dt onclick=showHide("items7_1")><b>对标管理</b></dt>
					<dd style='display:none' class='sitem' id=items7_1>
						<ul class='sitemu' id=6>
							<li><a href='${pageContext.request.contextPath}/benchmarking/list' target='main'>对标信息管理</a></li>
							<li><a href='duibiao-result.jsp' target='main'>数据统计</a> </li>
						</ul>
					</dd>
				</dl>

				<dl class='bitem'>
					<dt onclick=showHide("items6_1")><b>我的信息</b></dt>
					<dd style='display:none' class='sitem' id=items6_1>
						<ul class='sitemu' id=5>
							<li><a href='info.jsp' target='main'>信息查看</a> </li>
							<li><a href='modpassword.jsp' target='main'>修改密码</a> </li>
						</ul>
					</dd>
				</dl>
			</td>--%>
		</tr>
	</table>
</body>
</html>