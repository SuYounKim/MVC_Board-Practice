package ctrl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class RegAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; // output
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setMid(request.getParameter("mid"));
		vo.setMname(request.getParameter("mname"));
		vo.setMpw(request.getParameter("mpw"));
		
		if(dao.insert(vo)) {
			forward = new ActionForward();
			forward.setPath("main.jsp");
			forward.setRedirect(true);
			//response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 완료!');window.close();</script>");
			
		}else {
			//response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
		}
		return forward;
	}
	
}

/*
else if(action.equals("reg")){
if(mDAO.insert(mVO)){ // 회원 가입에 성공했다면
	out.println("<script>alert('회원가입 완료!');window.close();</script>");
}else{
	out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
}*/