<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑任务</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    $(function () {
        //请求项目列表
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/pro/proInList",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var option = "<option value='"+ item.pid +"'>"+ item.pname +"</option>";
                    $("#jsonList").append(option);
                })
                $("#jsonList").val(${task.function.project.pname});
            }
        })

        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/emp/empNameList",
            success:function (msg) {
                $(msg).each(function (index,item) {
                    var option = "<option value='"+item.eid+"'>"+item.ename+"</option>";
                    $("#empNameList").append(option);
                })
            }
        })
    });

    function changeId(pid) {
        $("#funcName").empty();
        $("#funcName").append("<option>---请选择---</option>");
        //请求需求分析列表
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/anal/analListById/" + pid,
            success:function (msg) {
                $("#analTitle").empty();
                $("#analTitle").append("<option>---请选择---</option>");
                var option = "<option value='"+msg.id+"'>"+msg.title+"</option>";
                $("#analTitle").append(option);
            }
        });
        //请求模块名称列表
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/mod/modNameList/" + pid,
            success:function (msg) {
                $("#modName").empty();
            	$("#modName").append("<option>---请选择---</option>");
                $(msg).each(function (index,item) {
                    var option = "<option value='"+item.id+"'>"+item.modname+"</option>";
                    $("#modName").append(option);
                })
            }
        })
    }

    function changeFunc(id) {
        //请求功能名称列表
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/func/funcList/" + id,
            success:function (msg) {
                $("#funcName").empty();
                $("#funcName").append("<option>---请选择---</option>");
                $(msg).each(function (index,item) {
                    var option = "<option value='"+item.id+"'>"+item.functionname+"</option>";
                    $("#funcName").append(option);
                })
            }
        })
    }

	function commit() {
		$("#form2").submit();
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
    当前位置:任务管理>>编辑任务
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form2" name="form2" action="${pageContext.request.contextPath}/task/update" method="post">
<input type="hidden" name="_method" value="PUT"/>
<input type="hidden" name="status" value="${task.status}"/>
<input type="hidden" name="empFk" value="${task.empFk}">
<input type="hidden" name="id" value="${task.id}"/>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑任务&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">参考位置：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="jsonList" onchange="changeId(this.value)"></select>-
		<select id="analTitle">
			<option>${task.function.analysis.title}</option>
		</select>-
		<select id="modName" onchange="changeFunc(this.value)">
			<option>${task.function.model.modname}</option>
		</select>-
		<select name="funFk" id="funcName">
			<option value="${task.funFk}">${task.function.functionname}</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">任务标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="tasktitle" value="${task.tasktitle}"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime" value="<fmt:formatDate value="${task.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate>"/>
		<img id="startTime" onclick="WdatePicker({el:'startTime'})" src="${pageContext.request.contextPath}/static/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">结束时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="endTime" name="endtime" value="<fmt:formatDate value="${task.endtime}" pattern="yyyy-MM-dd"></fmt:formatDate>">
		<img onclick="WdatePicker({el:'endTime'})" src="${pageContext.request.contextPath}/static/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	</td>


</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">执行者：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk2" id="empNameList">
			<option value="${task.empFk2}">${task.employee1.ename}</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">优先级：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option <c:if test="${task.level.equals('高')}">selected</c:if> value="高">高</option>
			<option <c:if test="${task.level.equals('中')}">selected</c:if> value="中">中</option>
			<option <c:if test="${task.level.equals('低')}">selected</c:if> value="低">低</option>
			<option <c:if test="${task.level.equals('暂缓')}">selected</c:if> value="暂缓">暂缓</option>
		</select>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >详细说明：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="remark" rows=10 cols=130>${task.remark}</textarea>
	</td>
</tr>
<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>
</form>
</body>
</html>