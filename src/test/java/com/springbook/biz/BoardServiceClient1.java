package com.springbook.biz;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

public class BoardServiceClient1 {

	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml"); // 싱글톤 패턴임

		BoardService boardService = (BoardService) container.getBean("boardService");
		Scanner sc = new Scanner(System.in);
		System.out.println("1.전체조회 2.조회 3.생성 4.수정 5.삭제");
		System.out.print("번호를 입력>>");
		int num = sc.nextInt();
		if (num == 1) {
			List<BoardVO> boardList = boardService.getBoardList();
			System.out.println(boardList);
			for (BoardVO board : boardList) {
				System.out.println("--->" + board.toString());
			}
		} else if (num == 2) {
			BoardVO vo = new BoardVO();
			System.out.println("조회할 번호를 입력>>");
			int seq = sc.nextInt();
			vo.setSeq(seq);
			vo = boardService.getBoard(vo);
			System.out.println(vo.toString());
		} else if (num == 3) {
			BoardVO vo = new BoardVO();
			
			System.out.println("Title 입력>>");
			String title = sc.nextLine();
			vo.setTitle(title);
			
			System.out.println("Wirter 입력>>");
			String writer = sc.nextLine();
			vo.setWriter(writer);
			
			System.out.println("Content 입력>>");
			String content = sc.nextLine();
			vo.setContent(content);
	
			boardService.insertBoard(vo);
		} else if (num == 4) {
			BoardVO vo = new BoardVO();
			System.out.println("수정할 글 번호 입력:");
			int seq = sc.nextInt();
			vo.setSeq(seq);

			System.out.println("수정할 제목 입력:");
			String title = sc.nextLine();
			vo.setTitle(title);

			System.out.println("수정할 내용 입력");
			String content = sc.nextLine();
			vo.setContent(content);

			boardService.updateBoard(vo);
		} else if (num == 5) {
			BoardVO vo = new BoardVO();
			System.out.println("삭제할 글 번호 입력:");
			int seq = sc.nextInt();

			vo.setSeq(seq);
			boardService.deleteBoard(vo);
		} else {
			System.out.println("번호를 잘못 입력함.");
		}
		container.close();
	}

}