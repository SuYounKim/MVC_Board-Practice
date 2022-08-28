<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="type"%>
<%@ attribute name="bid" %>
<c:if test= "${uid !=null}">

		
<c:choose>
		<c:when test="${type=='msg'}">
		<form action="insertB.do?cnt=${cnt}" method="post">
		<input type="hidden" name="mid" value="${uid}"> 
		<input type="text" name="msg">
		<input type="submit" value="글 등록">
		</form>
		</c:when>
		
		<c:when test="${type=='rmsg'}">
			<form action="insertR.do?cnt=${cnt}" method="post">
			<input type="hidden" name="mid" value="${uid}">
			<input type="hidden" name="bid" value="${bid}">
			댓글 : <input type="text" name="rmsg">
			<input type="submit" value="댓글 등록">
			</form>
		</c:when>
</c:choose>

</c:if>

<c:if test="${uid==null}">
	<c:choose>
		<c:when test="${type=='msg'}">
			<input type="text" disabled value="등록하려면 로그인하세요!">
			<input type="submit" disabled value="글 등록">
		</c:when>
		
		<c:when test="${type=='rmsg'}">
			댓글 : <input type="text" disabled value="등록하려면 로그인하세요!">
			<input type="submit" disabled value="댓글 등록">
		</c:when>
	</c:choose>
</c:if>