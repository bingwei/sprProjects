<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${viewSpace.spaceName}</title>
	</head>
	<body>
		<%@ include file="includeTop.jsp"%>
		<div style="clear:both">
		    <div style="float:left">
		       <a href="<c:url value="/vs/comment/${viewSpace.spaceId}/1.html"/>">想去</a>
		       (${viewSpace.wantNum})&nbsp;
		       <a href="<c:url value="/vs/comment/${viewSpace.spaceId}/2.html"/>">去过</a>
               (${viewSpace.beenNum})&nbsp;
		       <a href="<c:url value="/vs/comment/${viewSpace.spaceId}/3.html"/>">不想去</a>
		       (${viewSpace.notwantNum})&nbsp; 
		    </div>
			<table border="1px" width="100%">
				<tr>
					<td width="15%" >景区名：</td>
					<td>${viewSpace.spaceName}</td>
				</tr>
				<tr>
					<td>景区地址：</td>
					<td>${viewSpace.address}</td>
				</tr>
				<tr>
					<td>景区网址：</td>
					<td><a href="<c:url value="${viewSpace.website}"/>">${viewSpace.website}</a></td>
				</tr>
				<tr>
					<td>简介：</td>
					<td>${viewSpace.description}</td>
				</tr>
			</table>
			<div>
				<div>
				   <table border="1px" width="100%">
				   		<tr>
				   		    <td colspan="5">景区景点</td>
				   		</tr>
				   		<tr>
							<td width="4%">序号</td>
							<td width="20%">景点名</td>
							<td width="10%">票价(元)</td>		
							<td width="50%">简介</td>			
					    </tr>
					    <c:forEach var="viewPoint" varStatus="status" items="${viewSpace.viewPoints}">
							<tr>
								<td>${status.count}</td>
								<td>${viewPoint.pointName}</td>
								<td>${viewPoint.ticketPrice}</td>
								<td>${viewPoint.description}</td>
							</tr>
							<c:if test="${!empty viewPoint.imgFile}">
								<tr>
								    <td>景区图片</td>
								    <td colspan="4">
								        <img src="<c:url value="/static/uploads/${viewPoint.imgFile}"/>"></img>
								    </td>
								</tr>
						   </c:if>
				       </c:forEach>
				   </table>
				</div>
		   </div>
	</body>
</html>
