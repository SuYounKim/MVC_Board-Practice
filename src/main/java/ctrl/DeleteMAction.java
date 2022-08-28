package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;
import board.BoardVO;
import board.ReplyVO;
import member.MemberDAO;
import member.MemberVO;

public class DeleteMAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		MemberDAO mdao = new MemberDAO();
		BoardDAO dao = new BoardDAO();
		MemberVO mvo = new MemberVO();
		BoardVO bvo = new BoardVO();
		ReplyVO rvo = new ReplyVO();

		mvo.setMid(request.getParameter("mid"));
		bvo.setMid(request.getParameter("mid"));
		rvo.setMid(request.getParameter("mid"));
		System.out.println("로그 : mpw"+ request.getParameter("mpw"));
		//System.out.println(mvo.getMid());
		//System.out.println(bvo.getMid());
		//System.out.println(rvo.getMid());

		mvo.setMpw(request.getParameter("mpw"));
		if(dao.selectOneB(bvo)==null && dao.selectOneR(rvo)==null ) {
			if(mdao.delete(mvo)) {
				HttpSession session = request.getSession();
				session.invalidate();
				forward = new ActionForward();
				forward.setPath("main.do");
				forward.setRedirect(true);
			}
		}
		else {
			System.out.println("로그 : 탈퇴실패");
			throw new Exception("탈퇴 실패");
		}

		return forward;
	}
}

