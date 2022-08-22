package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public List<BoardVO> dataTransForm(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchCondition("");
		List<BoardVO> boardList = boardService.getBoardList(vo); // 전체리스트를 불러와서 리스트에 저장
		return boardList;
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo/* , BoardDAO boardDAO */) throws IllegalStateException, IOException {
		System.out.println("글 등록 처리");
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println(uploadFile);
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); // 실제이름을 fileName에 저장
			uploadFile.transferTo(new File("/Users/hyeon1339/file/" + fileName));
		}

		boardService.insertBoard(vo);
		// boardDAO.insertBoard(vo);
		return "redirect:getBoardList.do"; // 기본은 포워드방식으로 이동
	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo/* , BoardDAO boardDAO */) {
		System.out.println("글 수정 처리");
		boardService.updateBoard(vo);
		// boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo/* , BoardDAO boardDAO */) {
		System.out.println("글 삭제 처리");
		boardService.deleteBoard(vo);
		// boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}

	@RequestMapping("/getBoard.do")
	public /* ModelAndView */ String getBoard(BoardVO vo/* ,BoardDAO boardDAO */, Model model/* ModelAndView mav */) {
		System.out.println("글 상세 조회 처리");

		model.addAttribute("board", boardService.getBoard(vo));

		return "getBoard.jsp";

//      mav.addObject("board", boardDAO.getBoard(vo)); 
//      mav.setViewName("getBoard.jsp");

//      return mav;
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	@RequestMapping("/getBoardList.do")
	public /* ModelAndView */ String getBoardList(BoardVO vo/* ,BoardDAO boardDAO */,
			Model model/* ModelAndView mav */) {
		System.out.println("글 목록 검색 처리");

		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}

		model.addAttribute("boardList", boardService.getBoardList(vo));

		return "getBoardList.jsp";

//      mav.addObject("boardList", boardDAO.getBoardList()); 
//      mav.setViewName("getBoardList.jsp");
//      
//      return mav;
	}

//   public /*ModelAndView*/ String getBoardList(
//         @RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
//         @RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
//         BoardDAO boardDAO,Model model/*ModelAndView mav*/) {
//      System.out.println("글 목록 검색 처리");
//      System.out.println("검색조건 : " + condition);
//      System.out.println("검색 단어 : " + keyword);
////      model.addAttribute("boardList", boardDAO.getBoardList());
//      
//      return "getBoardList.jsp";
//      
////      mav.addObject("boardList", boardDAO.getBoardList()); 
////      mav.setViewName("getBoardList.jsp");
////      
////      return mav;
//   }
}