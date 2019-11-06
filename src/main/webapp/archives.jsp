<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>档案信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	//全选
	function chooseAll() {
		$(".np:checkbox").each(function(){
			$(this).prop("checked", true);
		})
	}

	//反选
	function reverseChoose() {
		$(".np:checkbox").each(function(){
			//遍历所有复选框，然后取值进行 !非操作
			$(this).prop("checked", !$(this).prop("checked"));
		})
	}

	function batchDelete() {
		if (confirm("你确定要删除吗？")) {
			var ids = [];
			$("#list-table").find("input:checked").each(function () {
				var cid = $(this).val();
				ids.push(cid);
			})

			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/archives/del",
				data:{"_method": "delete","ids":ids},
				success: function (msg){
					if (msg.statusCode == 200){
						window.location.href = "${pageContext.request.contextPath}/archives/list";
					}
				},
				error: function () {
					alert("删除失败");
					window.location.href = "${pageContext.request.contextPath}/archives/list";
				}
			});
		}
	}
</script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>档案信息管理
 </td>

 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;员工档案信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">姓名</td>
	<td width="10%">年龄</td>
	<td width="10%">毕业院校</td>
	<td width="8%">入职时间</td>
	<td width="5%">联系方式</td>
	<td width="8%">学历</td>
	<td width="6%">邮箱</td>
	<td width="8%">政治面貌</td>
	<td width="8%">民族</td>	
	<td width="10%">操作</td>
</tr>
<c:forEach items="${page.list}" var="archives" varStatus="index">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="${archives.dnum}" class="np"></td>
	<td>${index.count}</td>
	<td align="left"><a href=''><u>${archives.employee.ename}</u></a></td>
	<td>${archives.employee.eage}</td>
	<td>${archives.school}</td>
	<td>${archives.hirdate}</td>
	<td>${archives.employee.telephone}</td>
	<td>${archives.xueli}</td>
	<td>${archives.email}</td>
	<td>${archives.zzmm}</td>
	<td>${archives.minzu}</td>
	<td><a href="project-base-edit.jsp">编辑</a> | <a href="project-base-look.jsp">查看详情</a></td>
</tr>
</c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:reverseChoose()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:batchDelete()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
	<a href="${pageContext.request.contextPath}/archives-add.jsp" class="coolbg">&nbsp;添加档案信息&nbsp;</a>
</td>
</tr>
<jsp:include page="page.jsp"></jsp:include>
</table>

</form>
  

</body>
</html>