package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.ReplyVO;

public class DeleteRAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		BoardDAO dao = new BoardDAO();
		ReplyVO vo = new ReplyVO();
		
		vo.setRid(Integer.parseInt(request.getParameter("rid")));
		
		if(dao.deleteR(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false); //댓글 삭제는 기존 페이지를 유지해야하기 때문에 forward방식
		}else {
			throw new Exception("deleteR 오류");
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


