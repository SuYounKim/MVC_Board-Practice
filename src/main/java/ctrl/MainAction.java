package ctrl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDAO;
import board.BoardSet;
import board.BoardVO;
import member.MemberDAO;
import member.MemberVO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//	request.setCharacterEncoding("UTF-8");//인코딩 필터가 없으면 이작업을 해야함 
	//	response.setCharacterEncoding("UTF-8"); //인코딩 필터가 없으면 이작업을 해야함 
		
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		MemberVO mvo = new MemberVO();
		MemberDAO mdao = new MemberDAO();

		String paramCnt = request.getParameter("cnt");
		if(paramCnt == null || paramCnt.equals("")){
			vo.setCnt(2); // 향후 초기화 매개변수 등으로 설정가능
		}
		else {
			vo.setCnt(Integer.parseInt(paramCnt));
		}
		
		ArrayList<MemberVO> member = mdao.selectAll(mvo);
		request.setAttribute("member", member);
		
		String showContent = request.getParameter("showContent");
		System.out.println("showContent : [" + showContent + "]");
		
		//main.jsp에서 내가쓴글 눌렀을때 나오는 mid
		//String paramMid = request.getParameter("mid");
		//String paramRmsg = request.getParameter("rmsg");
		//System.out.println("request.getParameter(mid) : " + paramMid);
		//System.out.println("request.getParameter(rmsg) : " + paramRmsg);
		
		HttpSession session = request.getSession(); // 출력할 게시글의 작성자를 세션에 저장 
		if(showContent != null) { // 메인으로 가기 / 내가 쓴 글 보기 / 최근 가입 회원이 쓴 글에 접속
			if(showContent.equals("main")) { // 메인으로 가기
				session.removeAttribute("moreContent"); // 세션 삭제 --> 전체 글 보기
			}
			else { // 내가 쓴 글 / 최근 회원이 쓴 글
				session.setAttribute("moreContent", showContent); // 작성자 세션 저장
			//	System.out.println("moreContent : " + session.getAttribute("moreContent"));
				vo.setMid(showContent); // 해당 작성자의 모든 게시글 출력하기 위한 mid set
			}
		}
		else { // 작성, 삭제, 좋아요 등 게시글 조작 --> showContent를 전달하지 않는다.
			// 그래서 세션에 있는 작성자 정보 이용
			// ex) 내가 쓴 글 보기(나의 mid 세션에 저장) --> 좋아요, 댓글 등 기능 수행(showContent 전달 X) 
			vo.setMid((String)session.getAttribute("moreContent")); // 바로 직전에 세션에 저장된 작성자의 글을 출력(전체 게시글 포함)
		}			

		
		ArrayList<BoardSet> datas = dao.selectAll(vo);
		System.out.println("mainaction" + datas);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", vo.getCnt());
		
		
		// 모든 게시글 보면 더보기 버튼 비활성화
		BoardVO nextBvo = vo;
		//nextBvo에 다음에 보여질 게시물 미리 계산
		nextBvo.setCnt(vo.getCnt() + 2); 
		// 다음에 보여줄 게시글
		ArrayList<BoardSet> nextDatas = dao.selectAll(nextBvo);
		// cnt와 '다음에 보여줄 게시글 개수' 차이가 2보다 크거나 같으면
		// 더보기 버튼 비활성화
		request.setAttribute("noMoreContent", nextBvo.getCnt() - nextDatas.size() >= 2 ? true : false);
		System.out.println("noMoreContent : " + request.getAttribute("noMoreContent"));

		// 내가 쓴 글 --> 세션 mid를 [C]에 전달
		// 전달된 mid 값이 없다면, 전체 글 더보기
		//if(paramMid != null && request.getAttribute("AfterReply") == null && request.getAttribute("afterReplyBoard") == null) { // 내가 쓴 글만 보기 --> 
		//	request.setAttribute("moreContent", paramMid); //paramMid를 "moreContent"로 
		//	System.out.println("moreContent : " + request.getAttribute("moreContent"));
		//}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
