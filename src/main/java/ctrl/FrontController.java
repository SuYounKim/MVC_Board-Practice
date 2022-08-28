package ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")//*.do 요청을 수행하면 해당 어노테이션에 의해 FC로 오게됨!! 
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() { 
		// ★ FrontController fc = new Frontcontroller(); 
		//객체화를 하지않았는데, 메서드를 사용할 수 있었다...?!! 
		//서블릿 컨테이버(==객체를 관리하는 것) == 웹 서버 == 톰캣이 서블릿을 객체화해주고 있었음 

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String command=uri.substring(cp.length()); //사용자가 진짜로 요청한게 무엇인지 - 실제 액션값만 추출 -> 유지 보수성 증가
		System.out.println(command);

		ActionForward forward=null; //control을 매핑하는 역할 
		
		//액션처리 
		if(command.equals("/main.do")) {
				try {
					forward=new MainAction().execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}else if(command.equals("/logout.do")) {
					try {
						forward=new LogoutAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		}else if(command.equals("/login.do")) {

					try {
						forward=new LoginAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}else if(command.equals("/reg.do")) {

					try {
						forward=new RegAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
		}else if(command.equals("/insertB.do")) {

					try {
						forward=new InsertBAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}else if(command.equals("/insertR.do")) {

					try {
						forward=new InsertRAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}else if(command.equals("/deleteB.do")) {	

					try {
						forward=new DeleteBAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}else if(command.equals("/deleteR.do")) {

					try {
						forward=new DeleteRAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}else if(command.equals("/fav.do")) {

					try {
						forward=new FavAction().execute(request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}else if(command.equals("/deleteM.do")) {

			try {
				forward=new DeleteMAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} /*
			 * else if(command.equals("/search.do")) { try { forward = new
			 * SearchAction().execute(request, response); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } }
			 */
			/*
			if(forward==null) { //과정중에 문제가 발생 
				forward=new ActionForward();
				forward.setPath("error/error.jsp");
				forward.setRedirect(false);
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());//그 결과를 설정된 페이지로 출력
			try {
				dispatcher.forward(request, response);
				// : 타겟페이지(인자)로 request,response 객체를 전달하는 메서드 
				// : 제어권을 넘겨줌 -> 클라이언트가 응답을 확인할 수 있음 
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			if(forward!=null) { // 만약 forward == null이면 위의 액션처리가 제대로 되지 않은 것!
				if(forward.isRedirect()) { //이게 redirect 방식이니? (이전 action들에서 반드시 set해주어야함) 
					response.sendRedirect(forward.getPath()); 
				}else {  // forward 방식 - 요청 헤더를 유지하여 페이지 이동
					// Dispatcher
					// : 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는 역할을 수행하거나,
					//   특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
					RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); // 최종 경로
					dispatcher.forward(request, response); 
				}
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('요청처리실패!');history.go(-1);</script>");
		}
	}

