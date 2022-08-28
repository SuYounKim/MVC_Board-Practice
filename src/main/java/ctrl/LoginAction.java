package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberVO mVO = new MemberVO();
		MemberDAO mDAO = new MemberDAO();
		
		mVO.setMid(request.getParameter("mid"));
		mVO.setMpw(request.getParameter("mpw"));
		
		mVO = mDAO.selectOne(mVO);
		if(mVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("uid", mVO.getMid());
		}
		else {
			System.out.println("로그 : 로그인 실패");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(true);
		return forward;
	}

}
