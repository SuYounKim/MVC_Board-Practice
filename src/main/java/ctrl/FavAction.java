package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;

public class FavAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; //output
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		
		if(dao.update(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false); //좋아요 누르기는 기존 페이지를 유지해야하기 때문에 forward방식
		}else {
			throw new Exception("fav 오류");
		}
		return forward;
	}
	
}


/*else if(action.equals("fav")){
if(bDAO.update(bVO)){
pageContext.forward("ctrlB.jsp?action=main&cnt=2");
}
else{
throw new Exception("fav 오류");
}*/