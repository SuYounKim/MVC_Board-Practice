package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class DeleteBAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; //output
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		
		if(dao.delete(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false); //??? forward면 F, redirect면 T
		}else {
			throw new Exception("deleteB 오류");
		}
		return forward;
	}
}


/*else if(action.equals("deleteB")){
if(bDAO.delete(bVO)){
response.sendRedirect("ctrlB.jsp?action=main&cnt=2");
}
else{
throw new Exception("deleteB 오류");
}*/