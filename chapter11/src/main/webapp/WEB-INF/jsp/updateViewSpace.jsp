<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${viewForm.viewSpace.spaceName}</title>
		<script>
	   function mySubmit(){
	      with(document){
	         var spaceName = getElementById("spaceName");
	         if(spaceName.value == null || spaceName.value.length ==0){
	            alert("景区名称不能为空，请填上.");
	            spaceName.focus();
	            return false;
	         }else if(spaceName.value.length > 50){
	            alert("景区名称最大长度不能超过50个字符，请调整.");
	            spaceName.focus();
	            return false;
	         }
	          
	         var address = getElementById("address");
	         if(address.value == null || address.value.length==0){
	            alert("景区地址不能为空，请填上.");
	            address.focus();
	            return false;
	         }else if(address.value.length > 50){
	            alert("景区名称最大长度不能超过150个字符，请调整.");
	            address.focus();
	            return false;
	         }
	         
	         var website = getElementById("website");
	         if(website.value != null){
	            if(website.value.length > 100){
	               alert("网址的长度不能超过100个字符。");
	               website.focus();
	               return false;
	            }else if(website.value.toUpperCase().indexOf("HTTP://") != 0){
				   alert("网址必须以http://开头。");
				   website.focus();
	               return false;
				}
	         }   
	         return true;
	      }
	      
	   }
	</script>
	</head>
	<body>
		<%@ include file="includeTop.jsp"%>
		<div>
			<form action="${context}/vs/${viewSpace.spaceId}/update.html"
				onsubmit="return mySubmit()"  method="post">
				<div>
					<table border="1px" width="100%">
						<tr>
							<td width="15%">
								景区名：
							</td>
							<td>
								<input type="text" name="spaceName"
									value="${viewSpace.spaceName}" style="width: 100%" />
							</td>
						</tr>
						<tr>
							<td>
								景区地址：
							</td>
							<td>
								<input type="text" name="address" value="${viewSpace.address}"
									style="width: 100%" />
							</td>
						</tr>
						<tr>
							<td>
								景区网址：
							</td>
							<td>
								<input type="text" name="website" value="${viewSpace.website}"
									style="width: 100%" />
							</td>
						</tr>
						<tr>
							<td>
								简介：
							</td>
							<td>
								<input type="text" name="description"
									value="${viewSpace.description}"
									style="width: 100%; height: 150px" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="保存">
								<input type="reset" value="重置">
								<input type="hidden" name="wantNum"  value="${viewSpace.wantNum}"/>
								<input type="hidden" name="beenNum" value="${viewSpace.beenNum}"/>
								<input type="hidden" name="notwantNum" value="${viewSpace.notwantNum}"/>
								<input type="hidden" name="_method" value="PUT">
								<input type="hidden" name="spaceId" value="${viewSpace.spaceId}"/>
							</td>
						</tr>
					</table>
			</form>
			<div>
				<div>
					<table border="1px" width="100%">
						<tr>
							<td colspan="5">
								<div style="float: left">
									景区景点
								</div>
								<div style="float: right">
									<a href="<c:url value="/vp/${viewSpace.spaceId}/add.html"/>">新增</a>
								</div>
							</td>
						</tr>
						<tr>
							<td width="4%">
								序号
							</td>
							<td width="20%">
								景点名
							</td>
							<td width="10%">
								票价(元)
							</td>
							<td width="50%">
								简介
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach var="viewPoint" varStatus="status"
							items="${viewSpace.viewPoints}">
							<form id="form${status.count}" method="post" action="${context}/vp/${viewPoint.pointId}/delete">
							<tr>
								<td>
									${status.count}
								</td>
								<td>
									${viewPoint.pointName}
								</td>
								<td>
									${viewPoint.ticketPrice}
								</td>
								<td>
									${viewPoint.description}
								</td>
								<td>
									<a href="<c:url value="/vp/${viewPoint.pointId}/edit"/>">更改</a>
									<input type="hidden" name="_method" value="DELETE" />
									<a href="javascript:document.getElementById('form${status.count}').submit();">删除</a>
								</td>
							</tr>
							<c:if test="${!empty viewPoint.imgFile}">
								<tr>
									<td>
										景区图片
									</td>
									<td colspan="4">
										<img
											src="<c:url value="/static/uploads/${viewPoint.imgFile}"/>"></img>
									</td>
								</tr>
							</c:if>
							</form>
						</c:forEach>
					</table>
				</div>
			</div>
	</body>
</html>
