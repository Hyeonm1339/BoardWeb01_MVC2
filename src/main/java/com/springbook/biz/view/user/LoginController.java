package com.springbook.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {

//	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	@GetMapping("login.do")
	public String loginView(@ModelAttribute("user") UserVO vo) { // user라는 이름으로 리퀘스트 영역에 저
		vo.setId("test");
		vo.setPassword("test123");

		return "login.jsp";
	}

//	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@PostMapping("/login.do")
	public String Login(UserVO vo, UserDAO uDao, HttpSession session) {
		UserVO user = uDao.getUser(vo);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		}else {
			return "login.jsp";
		}

	}

//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("로그인컨트롤러 실행");
//
//		// 1. 사용자 입력 정보 추출
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//
//		// 2. DB 연동 처리
//
//		UserVO vo = new UserVO();
//		vo.setId(id);
//		vo.setPassword(password);
//
//		UserDAO userDAO = new UserDAO();
//		UserVO user = userDAO.getUser(vo);
//
//		// 3. 화면 네비게이션
//		ModelAndView mav = new ModelAndView();
//
//		if (user != null) {
//			mav.setViewName("redirect:getBoardList.do");
////					return "getBoardList.do";
//		} else {
//			mav.setViewName("redirect:login.jsp");
////					return "login";   //String viewName에 만든값을 전
//		}
//		return mav;
//	}

}
