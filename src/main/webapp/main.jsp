<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kim"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function reg() {
		window.open("reg.jsp", "회원가입 페이지", "width=500,height=200");
	}
</script>
</head>
<body>
	<div id="header">
		<h1>작은 티모${moreContent}</h1>
		<div class="gnb">
			<ul>
				
				<li><a href="main.do?showContent=main">메인으로</a></li>
				<li><kim:login /></li>

				<c:if test="${uid != null}">
					<li><kim:deleteacc /></li>
				</c:if>
			</ul>
		</div>
		<span>최근 가입한 회원</span>
		<kim:search />
	</div>

	<div id="wrapper">
		<div id="content">
			<h2>글 등록하기</h2>
			<kim:write type="msg" />
		</div>

		<div id="main">
			<h2>글 목록보기</h2>
			
			<!-- 만약 로그인 했다면 -->
			<c:if test="${uid !=null}">
				<h3>
					<!-- 세션에 있는 uid를 main.do를 보냄  -->
					<!-- mid로 찾는 selectall이 필요 -> pk로 구분 -->
					<a href="main.do?mid=${uid}&showContent=${uid}">내가 쓴 글</a>
				</h3>
			</c:if>
			<!-- datas에 v.boardVO를 넣어준다 -->
			<c:forEach var="v" items="${datas}">
				<c:set var="b" value="${v.boardVO}" />
				<h3>[${b.mid}] ${b.msg} [ 좋아요 ${b.favcnt} | 댓글 ${b.rcnt} ] <kim:board midCheck="${b.mid}" bid="${b.bid}" /></h3>

				<div class="reply">
					<ul>
						<!-- replyVO가 들어가는 rList에 v처리하여 이것도 datas에 넣어준다 -->
						<c:forEach var="r" items="${v.rList}">
							<li>[${r.mid}] ${r.rmsg} <kim:reply midCheck="${r.mid}" rid="${r.rid}" /></li>
						</c:forEach>
					</ul>
				</div>

				<div class="reply">
					<kim:write type="rmsg" bid="${b.bid}" />
				</div>
			</c:forEach>
		</div>
		<!-- 모든 게시글 보면 더보기 버튼 비활성화 -->
		<c:if test="${!noMoreContent}">
			<!-- 내가 쓴 글만 더보기 -->
			<!-- boarddao cnt, mid 전달 / moreContent는 mainAction의 ParamMid -->
			<c:if test="${moreContent != null}">
				<a href="main.do?showContent=${moreContent}&cnt=${cnt+2}">더보기&gt;&gt;</a>
			</c:if>
			<!-- 전체 글 더보기 -->
			<!-- boarddao cnt만 전달  -->
			<c:if test="${moreContent == null}">
				<a href="main.do?cnt=${cnt+2}">더보기&gt;&gt;</a>
			</c:if>
		</c:if>
	</div>

	<div id="footer">
		회사소개 | 이용약관 | <strong>개인정보처리방침</strong> | 보호정책 | 고객센터 <strong>ⓒ
			Corp.</strong>
	</div>

</body>
</html>