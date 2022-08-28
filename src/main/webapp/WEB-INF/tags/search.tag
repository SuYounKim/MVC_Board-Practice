<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="m" items="${member}">
<c:if test="${m.mid == null}">
	최근에 가입한 회원이 없습니다.
</c:if>
<c:if test="${m.mid != null}">
	<tr>
		<th><a href="main.do?mid=${m.mid}&cnt=2&showContent=${m.mid}">[${m.mname}]&nbsp;</a></th>
	</tr>
</c:if>
</c:forEach>
