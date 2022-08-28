<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,member.*"%>
<jsp:useBean id="mDAO" class="member.MemberDAO"/>
<jsp:useBean id="mVO" class="member.MemberVO"/>
<jsp:setProperty property="*" name="mVO"/>
<%
	String action=request.getParameter("action");
	System.out.println("로그: "+action);
	
	if(action.equals("login")){
		mVO=mDAO.selectOne(mVO);
		if(mVO!=null){
			session.setAttribute("mid", mVO.getMid());
			response.sendRedirect("ctrlB.jsp?action=main");
		}else{
			out.println("<script>alert('로그인 실패!');history.go(-1);</script>");
		}
	}else if(action.equals("logout")){
		session.invalidate(); //세션 삭제
		response.sendRedirect("ctrlB.jsp?action=main&cnt=2");
	}else if(action.equals("reg")){
		if(mDAO.insert(mVO)){ // 회원 가입에 성공했다면
			out.println("<script>alert('회원가입 완료!');window.close();</script>");
		}else{
			out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
		}
	}

%> --%>