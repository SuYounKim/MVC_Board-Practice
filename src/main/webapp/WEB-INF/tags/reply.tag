<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="midCheck" %>
<%@ attribute name="rid" %>

<c:if test="${uid==midCheck}">
<a href="deleteR.do?&rid=${rid}&cnt=${cnt}">[삭제]</a>

</c:if>