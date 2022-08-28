package ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.ReplyVO;

public class InsertRAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null; // output
		
		BoardDAO dao = new BoardDAO();
		ReplyVO vo = new ReplyVO();
		vo.setMid(request.getParameter("mid"));
		vo.setBid(Integer.parseInt(request.getParameter("bid")));
		vo.setRmsg(request.getParameter("rmsg"));
		
		// if(request.getAttribute("afterReply") != null : 댓글 입력 --> 전체 내용 출력 + 전체 더보기
		// if(request.getAttribute("afterReply") == null : 댓글 입력 X 
		request.setAttribute("afterReply", true);
		
		if(dao.insertR(vo)) {
			forward=new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false); //댓글추가는 기존 페이지를 유지해야하기 때문에 forward방식 
		}else {
			throw new Exception("insertR 오류");
		}	
		
		return forward;
	}

}

/*else if(action.equals("insertR")){
if(bDAO.insertR(rVO)){
response.sendRedirect("ctrlB.jsp?action=main&cnt=2");
}
else{
throw new Exception("inserR 오류");*/