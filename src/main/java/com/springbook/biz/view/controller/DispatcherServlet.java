package com.springbook.biz.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String uri = request.getRequestURI(); // 현재 주소값 가져오기.http://localhost:9999/BoardWeb3/getBoard.do
		String path = uri.substring(uri.lastIndexOf("/")); // getBoard.do를 가져옴
		System.out.println(path);
		Controller ctrl = handlerMapping.getController(path);
		System.out.println(ctrl);
		String viewName = ctrl.HandleRequest(request, response);

		String view = null;

		if (!viewName.contains(".do")) { // .do가 포함되었나 안되었나 체크하는 함수
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}

		response.sendRedirect(view);

//		if (path.equals("/login.do")) {
//			System.out.println("로그인 처리");
//			// 1. 사용자 입력 정보 추출
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//
//			// 2. DB 연동 처리
//
//			UserVO vo = new UserVO();
//			vo.setId(id);
//			vo.setPassword(password);
//
//			UserDAO userDAO = new UserDAO();
//			UserVO user = userDAO.getUser(vo);
//
//			System.out.println(user);
//
//			// 3. 화면 네비게이션
//
//			if (user != null) {
//				response.sendRedirect("getBoardList.do");
//			} else {
//				response.sendRedirect("login.jsp");
//			}
//			
//		} else if (path.equals("/logout.do")) {
//			System.out.println("로그아웃 처리");
//
//			response.sendRedirect("login.jsp");
//		} else if (path.equals("/insertBoard.do")) {
//			System.out.println("글 등록 처리");
//
//			String title = request.getParameter("title");
//			String writer = request.getParameter("writer");
//			String content = request.getParameter("content");
//
//			BoardVO bVo = new BoardVO();
//			bVo.setTitle(title);
//			bVo.setWriter(writer);
//			bVo.setContent(content);
//
//			BoardDAO bDao = new BoardDAO();
//			bDao.insertBoard(bVo);
//
//			response.sendRedirect("getBoardList.do");
//
//		} else if (path.equals("/updateBoard.do")) {
//			System.out.println("글 수정 처리112312323");
//			System.out.println("여기는왜?");
//
//			String seq = request.getParameter("seq");
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			String writer = request.getParameter("wirter");
//			
//			System.out.println(seq);
//			System.out.println(title);
//			System.out.println(content);
//			System.out.println(writer);
//
//			BoardVO bVo = new BoardVO();
//			System.out.println(bVo);
//			bVo.setSeq(Integer.parseInt(seq));
//			bVo.setTitle(title);
//			bVo.setWriter(writer);
//			bVo.setContent(content);
//
//			BoardDAO bDao = new BoardDAO();
//			bDao.updateBoard(bVo);
//			response.sendRedirect("getBoardList.do");
//
//		} else if (path.equals("/deleteBoard.do")) {
//			System.out.println("글 삭제 처리");
//
//			String seq = request.getParameter("seq");
//			BoardVO bVo = new BoardVO();
//			System.out.println("객체생성");
//			bVo.setSeq(Integer.parseInt(seq));
//			BoardDAO bDao = new BoardDAO();
//			bDao.deleteBoard(bVo);
//			response.sendRedirect("getBoardList.jsp");
//		} else if (path.equals("/getBoard.do")) {
//			System.out.println("글 상세 조회 처리");
//
//			String seq = request.getParameter("seq");
//			BoardVO bVo = new BoardVO();
//			bVo.setSeq(Integer.parseInt(seq));
//			BoardDAO bDao = new BoardDAO();
//			BoardVO board = bDao.getBoard(bVo);
//
//			HttpSession session = request.getSession();
//			session.setAttribute("board", board);
//			response.sendRedirect("getBoard.jsp");
//
//		} else if (path.equals("/getBoardList.do")) {
//			System.out.println("글 목록 검색 처리");
//
//			BoardDAO boardDAO = new BoardDAO();
//			List<BoardVO> boardList = boardDAO.getBoardList();
//			HttpSession session = request.getSession();
//			session.setAttribute("board", boardList);
//			response.sendRedirect("getBoardList.jsp");
//		}
	}

}