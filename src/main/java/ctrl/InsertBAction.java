package ctrl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class InsertBAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; // output
		
		// VO DAO 인스턴스화
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		// DAO수행 필요데이터 SET
		vo.setMid(request.getParameter("mid"));
		vo.setMsg(request.getParameter("msg"));
		
		//request.setAttribute("afterReplyBoard", true);
		
		if(dao.insert(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do"); //어디로 갈지 - 컨트롤러를 통해서 main으로 보내줌 
			forward.setRedirect(false); //어떻게 갈지 - forward면 F, redirect면 T
		}else {
			throw new Exception("insertB 오류");
		}
		
		return forward;
	}
}


/*else if(action.equals("insertB")){
if(bDAO.insert(bVO)){
response.sendRedirect("ctrlB.jsp?action=main&cnt=2");
}
else{
throw new Exception("insertB 오류");*/