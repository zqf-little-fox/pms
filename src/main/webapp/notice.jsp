<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/moment.min.js"></script>
<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="application/javascript">
	$(function(){
		$.ajax({
			type:'GET',
			url:"${pageContext.request.contextPath}/notice/jsonList",
			success:function(msg){
				if (msg.map.statusCode == 200){
				    //遍历列表数据
				    $(msg.map.page.list).each(function (index,item) {
						var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
									"\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
									"\t<td>"+(index+1)+"</td>\n" +
									"\t<td>"+item.ntitle+"</td>\n" +
									"\t<td align='center'><span >"+item.remark+"</span></td>\n" +
									"\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
									"\t<td>\n" +
									"<a href='javascript:deleteNoticeByNid("+item.nid+")'>删除</a> |" +
									"<a href='${pageContext.request.contextPath}/notice/edit/"+item.nid+"'>编辑</a></td>" +
							      "</tr>";
						$("#index-dw").before(tr);
                    })

					//添加分页
					var div = "<div></div>";
				    var firstPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum=1"+msg.map.searchString+"'>首页</a>";
				    var prePage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum-1)+msg.map.searchString+"'>上一页</a>";
				    var everyPage = "";
				    $(msg.map.page.navigatepageNums).each(function (index,item) {
                    	everyPage = everyPage+"&nbsp;<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?"+item+msg.map.searchString+"'>"+item+"</a>&nbsp;";
                    })
				    var nextPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+(msg.map.page.pageNum+1)+msg.map.searchString+"'>下一页</a>";
                    var lastPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+msg.map.requestURI+"?pageNum="+msg.map.page.pages+msg.map.searchString+"'>尾页</a>";
                    var jumpPage = "&nbsp;&nbsp;跳转到第<input type='text' size='1px' id='pagenum' name='"+msg.map.requestURI+"?"+msg.map.searchString.substring(1)+"&pageNum=' onblur='queryList(this.name)' />页";
                    $(div).append(firstPage).append(prePage).append(everyPage).append(nextPage).append(lastPage).append(jumpPage).appendTo($("#page-td"));
                }
            }
		});
	});

	function deleteNoticeByNid(nid) {
	    if (confirm("您是否删除这条内容？")){
            $.ajax({
                type:"POST",
                url:"${pageContext.request.contextPath}/notice/del/"+nid,
                data:{"_method":"delete"},
                success:function (msg) {
                    if (msg.map.statusCode == 200) {
                        alert("删除成功");
                        $.ajax({
                            type:'GET',
                            url:"${pageContext.request.contextPath}/notice/jsonList",
                            success:function(msg){
                                if (msg.map.statusCode == 200){
                                    //原来的tr删除
                                    $("tr[name=appendtr]").remove();
                                    //遍历列表数据
                                    $(msg.map.page.list).each(function (index,item) {
                                        var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
                                            "\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
                                            "\t<td>"+(index+1)+"</td>\n" +
                                            "\t<td>"+item.ntitle+"</td>\n" +
                                            "\t<td align='center'><span >"+item.remark+"</span></td>\n" +
                                            "\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
                                            "\t<td>\n" +
                                            "<a href='javascript:deleteNoticeByNid("+item.nid+")'>删除</a> |" +
                                            "<a href='${pageContext.request.contextPath}/notice/edit/"+item.nid+"'>编辑</a></td>" +
                                            "</tr>";
                                        $("#index-dw").before(tr);
                                    })
                                }
                            }
                        });
                    }else{
                        alert("删除失败")
                    }
                }
            });
		}
    }

	function queryList(url) {
        var pageNo = $("#pagenum").val();
        $.ajax({
            type:'GET',
            url:url+pageNo,
            success:function(msg){
                if (msg.map.statusCode == 200){
                    //原来的tr删除
                    $("tr[name=appendtr]").remove();
                    //遍历列表数据
                    $(msg.map.page.list).each(function (index,item) {
                        var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
                            "\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
                            "\t<td>"+(index+1)+"</td>\n" +
                            "\t<td>"+item.ntitle+"</td>\n" +
                            "\t<td align='center'><span >"+item.remark+"</span></td>\n" +
                            "\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
                            "\t<td>\n" +
                            "\t\t<a href='javascript:deleteNoticeByNid("+item.nid+")'>删除</a> | \n" +
                            "\t\t<a href='${pageContext.request.contextPath}/notice/edit/"+item.nid+"'>编辑</a></td>\n" +
                            "</tr>";
                        $("#index-dw").before(tr);
                    })
                }
            }
        });
    }
	
	function findList(url) {
        $.ajax({
            type:'GET',
            url:url,
            success:function(msg){
                if (msg.map.statusCode == 200){
                    //原来的tr删除
					$("tr[name=appendtr]").remove();
                    //遍历列表数据
                    $(msg.map.page.list).each(function (index,item) {
                        var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
                            "\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
                            "\t<td>"+(index+1)+"</td>\n" +
                            "\t<td>"+item.ntitle+"</td>\n" +
                            "\t<td align='center'><span >"+item.remark+"</span></td>\n" +
                            "\t<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>\n" +
                            "\t<td>\n" +
                            "\t\t<a href='javascript:deleteNoticeByNid("+item.nid+")'>删除</a> | \n" +
                            "\t\t<a href='${pageContext.request.contextPath}/notice/edit/"+item.nid+"'>编辑</a></td>\n" +
                            "</tr>";
                        $("#index-dw").before(tr);
                    })
                }
            }
        });
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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>
<!--  内容列表   -->
<form name="form2">
<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>项目完成的咋样?</td>
	<td align="center"><span >最近工作累不？项目完成的咋样?加油哈</span></td>
	<td>2015-02-03</td>
	<td><a >删除</a></td>
</tr>--%>
<tr id="index-dw" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center" id="page-td"><!--翻页代码 --></td>
</tr>
</table>
</form>
</body>
</html>