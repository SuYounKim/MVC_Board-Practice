<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,board.*" %>
<jsp:useBean id="bDAO" class="board.BoardDAO" />
<jsp:useBean id="bVO" class="board.BoardVO" />
<jsp:setProperty property="*" name="bVO" />
<jsp:useBean id="rVO" class="board.ReplyVO" />
<jsp:setProperty property="*" name="rVO" />
<%
	String action=request.getParameter("action");

/* if(cnt 파라미터에 정보가 있어? ){
정보가 없다면, 기본값(2)으로 설정해줘! 
-> 향후, 초기화 매개변수로 설정도 가능! 
}	
String cnt_ = request.getParameter("cnt"); //cnt의 값을 받아옴 
int cnt=2; 기본값 설정
if(cnt_ !=null && !cnt_.equals("")){// 전달값이 null, 공백이 아니라면 cnt_를 정수형으로 변환 
cnt = Integer.parseInt(cnt_);
}
for(int i=0; i<cnt; i++){
out.println(i+1);
} */

	String paramCnt=request.getParameter("cnt");
	if(paramCnt==null || paramCnt.equals("")){
		bVO.setCnt(2); // 향후 초기화 매개변수 등으로 설정가능
	}
	System.out.println("cnt: "+bVO.getCnt());

	if(action.equals("main")){
		ArrayList<BoardSet> datas=bDAO.selectAll(bVO);
		request.setAttribute("datas", datas);
		request.setAttribute("cnt", bVO.getCnt());
		pageContext.forward("main.jsp");
	}
	else if(action.equals("insertB")){
		if(bDAO.insert(bVO)){
			response.sendRedirect("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("insertB 오류");
		}
	}
	else if(action.equals("insertR")){
		if(bDAO.insertR(rVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("insertR 오류");
		}
	}
	else if(action.equals("deleteB")){
		if(bDAO.delete(bVO)){
			response.sendRedirect("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("deleteB 오류");
		}
	}
	else if(action.equals("deleteR")){
		if(bDAO.deleteR(rVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("deleteR 오류");
		}
	}
	else if(action.equals("fav")){
		if(bDAO.update(bVO)){
			pageContext.forward("ctrlB.jsp?action=main");
		}
		else{
			throw new Exception("fav 오류");
		}
	}
%> --%>