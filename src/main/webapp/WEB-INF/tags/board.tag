<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="midCheck" %>
<%@ attribute name="bid" %>

<c:if test="${uid==midCheck}">
<a href="deleteB.do?&bid=${bid}&cnt=${cnt}">[삭제]</a>

</c:if>
<c:if test="${uid!=null}">
<a href="fav.do?&bid=${bid}&cnt=${cnt}">&nbsp;♥</a>
</c:if>