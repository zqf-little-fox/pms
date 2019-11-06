<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    function queryList() {
        var pageNo = $("#pagenum").val();
        window.location.href = "${requestURI}?pageNum="+pageNo+"${searchString}";
    }
</script>
<tr align="right" bgcolor="#EEF4EA">
    <td height="36" colspan="12" align="center">
        <div>
        <a href="${requestURI}?pageNum=1${searchString}">首页</a>
        <a href="${requestURI}?pageNum=${page.pageNum-1}${searchString}">上一页</a>
        <c:forEach items="${page.navigatepageNums}" var="pageNum">
            <c:if test="${pageNum == page.pageNum}">
                <span style="color:black;font-weight: bold">${pageNum}</span>
            </c:if>
            <c:if test="${pageNum != page.pageNum}">
                <a href="${requestURI}?pageNum=${pageNum}${searchString}">${pageNum}</a>
            </c:if>
        </c:forEach>
        <a href="${requestURI}?pageNum=${page.pageNum+1}${searchString}">下一页</a>
        <a href="${requestURI}?pageNum=${page.pages}${searchString}">尾页</a>
        &nbsp;&nbsp;跳转到第<input type="text" size="1px" id="pagenum" onblur="queryList()" />页
    </div>
    </td>
</tr>
