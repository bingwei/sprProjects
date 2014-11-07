<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>风景区列表</title>
	</head>
	<body>
		<%@ include file="includeTop.jsp"%>
		<table border="1px" width="100%">
			<tr><td colspan="4">
				 <div style="float:right">
				   <a href="<c:url value="/vs/add.html"/>">新增景区</a>
			     </div>
		     </td>
		     </tr>
			<tr>
				<td width="15%">景区名</td>
				<td width="15%">所在地</td>
				<td width="60%">简介</td>
				<td width="10%">操作</td>
			</tr>
			<c:forEach var="viewSpace" items="${viewSpaces}" varStatus="status">
			<form id="form${status.count}" method="post" action="${context}/vs/${viewSpace.spaceId}/delete.html">
				<tr>
					<td>
						<a href="<c:url value="/vs/${viewSpace.spaceId}.html"/>">
						   ${viewSpace.spaceName}
						</a>
					</td>
					<td>${viewSpace.address}</td>
					<td>${viewSpace.description}</td>
					<td>
					   <a href="<c:url value="/vs/${viewSpace.spaceId}/edit.html"/>">更改</a>
					   <input type="hidden" name="_method" value="DELETE" />
					   <a href="javascript:document.getElementById('form${status.count}').submit();">删除</a>
					</td>					
				</tr>
				</form>
			</c:forEach>
		</table>
	</body>
</html>
