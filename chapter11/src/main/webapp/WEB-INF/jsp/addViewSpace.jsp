<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加景区</title>
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
<form action="${context}/vs/save.html" onsubmit="return mySubmit()" method="post">
<table border="1px" width="100%">
	<tr>
		<td width="20%">景区名称：</td>
		<td width="80%"><input type="text" name="spaceName" style="width:100%"/></td>
	</tr>
	<tr>
		<td width="20%">地址：</td>
		<td width="80%"><input type="text" name="address" style="width:100%"/></td>
	</tr>
	<tr>
		<td width="20%">网址：</td>
		<td width="80%"><input type="text" name="website" style="width:100%"/></td>
	</tr>
	<tr>
		<td width="20%">简介：</td>
		<td width="80%"><input type="text" name="description" style="width:100%;height:150px"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		   <input type="submit" value="保存">
		   <input type="reset" value="重置">
		   <input type="hidden" name="_method" value="PUT">
		</td>
	</tr>
</table>
</form>
</body>
</html>
