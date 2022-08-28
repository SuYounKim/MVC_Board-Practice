package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(); //request를 통해 세션을 가져옴
		session.invalidate(); // 로그아웃 기능인 세션 삭제
		
		ActionForward forward=new ActionForward();
		forward.setPath("main.do"); //어디로 갈지 - 컨트롤러를 통해서 main으로 보내줌 
		forward.setRedirect(true); //어떻게 갈지 - forward면 F, redirect 면 T
		return forward;
	}
	
}

/*		else if(action.equals("logout")){
		session.invalidate(); //세션 삭제
		response.sendRedirect("ctrlB.jsp?action=main&cnt=2");*/