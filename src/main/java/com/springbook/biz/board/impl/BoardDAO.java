package com.springbook.biz.board.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {

   private Connection conn = null;
   private PreparedStatement pstmt = null;
   private Statement stmt = null;
   private ResultSet rs = null;
   BoardVO vo = new BoardVO();
   
   private final String BOARD_INSERT = "INSERT INTO board(seq,title,writer,content) "
         + "VALUES((SELECT nvl(max(seq),0)+1 FROM board),?,?,?)";
   private final String BOARD_UPDATE = "update board set title=?, writer=?, content=? where seq=?";
   private final String BOARD_GET = "select * from board where seq=?";
   private final String BOARD_LIST = "select * from board order by seq desc";
   private final String BOARD_DELETE = "delete from board where seq=?";
   private final String BOARD_CNT = "update board set cnt = cnt+1 where seq=?";

   
   
   public void boardCnt(BoardVO vo) {
      System.out.println("===> board cnt");
      
      try {
         conn = JDBCUtil.getConnection();
         pstmt = conn.prepareStatement(BOARD_CNT);
         pstmt.setInt(1, vo.getSeq());
         pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }
      
   }

   public void insertBoard(BoardVO vo ) {
      System.out.println("===> board insert ");

      try {
         conn = JDBCUtil.getConnection();
         pstmt = conn.prepareStatement(BOARD_INSERT);
         pstmt.setString(1, vo.getTitle());
         pstmt.setString(2, vo.getWriter());
         pstmt.setString(3, vo.getContent());
         pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }
   }

   public void updateBoard(BoardVO vo) {
      
     System.out.println("===> 업데이트");
      try {
         conn = JDBCUtil.getConnection();
         pstmt = conn.prepareStatement(BOARD_UPDATE);
         pstmt.setString(1, vo.getTitle());
         pstmt.setString(2, vo.getWriter());
         pstmt.setString(3, vo.getContent());
         pstmt.setInt(4, vo.getSeq());
         pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }
   }

   public void deleteBoard(BoardVO vo) {
      System.out.println("===> 삭제");
      
      try {
         conn = JDBCUtil.getConnection();
         pstmt = conn.prepareStatement(BOARD_DELETE);
         pstmt.setInt(1, vo.getSeq());
         pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }
   }

   
   
   public BoardVO getBoard(BoardVO vo) {
      BoardVO board = null;
      // 조회시 카운터 증가
      boardCnt(vo);
      System.out.println("===> 한명조회()");

      try {
         conn = JDBCUtil.getConnection();
         pstmt = conn.prepareStatement(BOARD_GET);
         pstmt.setInt(1, vo.getSeq());
         rs = pstmt.executeQuery();

          if (rs.next()) {
            board = new BoardVO();
            board.setSeq(rs.getInt("seq"));
            board.setTitle(rs.getString("title"));
            board.setWriter(rs.getString("Writer"));
            board.setContent(rs.getString("content"));
            board.setRegdate(rs.getDate("regdate"));
            board.setCnt(rs.getInt("cnt"));
         }

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }

      return board;
   }

   public List<BoardVO> getBoardList() {

      System.out.println("===> JDBC getBoardList()");

      List<BoardVO> boardList = new ArrayList();

      try {
         conn = JDBCUtil.getConnection();
         System.out.println("conn 값"+conn);
         pstmt = conn.prepareStatement(BOARD_LIST);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            BoardVO board = new BoardVO();
            board.setSeq(rs.getInt("seq"));
            board.setTitle(rs.getString("title"));
            board.setWriter(rs.getString("Writer"));
            board.setContent(rs.getString("content"));
            board.setRegdate(rs.getDate("regdate"));
            board.setCnt(rs.getInt("cnt"));
            boardList.add(board);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         JDBCUtil.close(pstmt, conn);
      }

      return boardList;
   }
}