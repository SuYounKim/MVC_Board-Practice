/*
 * package ctrl;
 * 
 * import java.util.ArrayList;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import board.BoardDAO; import board.BoardSet; import board.BoardVO; import
 * member.MemberDAO; import member.MemberVO;
 * 
 * public class SearchAction implements Action{
 * 
 * @Override public ActionForward execute(HttpServletRequest request,
 * HttpServletResponse response) throws Exception { ActionForward forward =
 * null; MemberVO mvo = new MemberVO(); MemberDAO mdao = new MemberDAO();
 * BoardDAO bdao = new BoardDAO(); BoardVO bvo = new BoardVO(); String
 * paramMid=request.getParameter("mid"); String
 * paramCnt=request.getParameter("cnt"); String
 * paramSearch=request.getParameter("search"); if(paramCnt==null ||
 * paramCnt.equals("")){ bvo.setCnt(2); } else {
 * bvo.setCnt(Integer.parseInt(paramCnt)); } mvo.setMid(paramMid);
 * if(paramSearch == null) { mvo.setSearch(1); }else {
 * mvo.setSearch(Integer.parseInt(paramSearch)); }
 * 
 * ArrayList<BoardSet> datas = bdao.selectB(mvo,bvo); ArrayList<MemberVO> member
 * = mdao.selectAll(mvo); request.setAttribute("member", member);
 * request.setAttribute("datas", datas); request.setAttribute("cnt",
 * bvo.getCnt()); request.setAttribute("search", mvo.getSearch());
 * 
 * forward=new ActionForward(); forward.setPath("/main.do");
 * forward.setRedirect(false);
 * 
 * return forward; }
 * 
 * }
 */