<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@page import="com.springbook.biz.board.*"%>
<%@page import="com.springbook.biz.board.impl.*"%>
<%@page import="java.util.List"%>

<%
   List<BoardVO> boardList = (List)session.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <center>
      <h1>글 목록</h1>
      <h3>
         테스터님 환영합니다 <a href="logout.do">log out</a>
      </h3>

      <form action="getBoardList.do" method="post">
         <table border="1" cellpadding="0" cellspacing="0" width="700">
            <tr>
               <td align="right"><select name="searchCondition">
                     <option value="TITLE">제목
                     <option value="CONTENT">내용
               </select>
               <input name="searchKeyword" type="text"/> <input type="submit" value="검색">
               </td>
            </tr>
         </table>
      </form>
      
      <table border="1" cellpadding="0" cellspacing="0" width="700">
         <tr>
            <th bgcolor="orange" width="100">번호</th>
            <th bgcolor="orange" width="200">제목</th>
            <th bgcolor="orange" width="150">작성자</th>
            <th bgcolor="orange" width="150">등록일</th>
            <th bgcolor="orange" width="100">조회수</th>
         </tr>
         
         <%
            for(BoardVO board : boardList){
         %>
         
         <tr>
            <td><%=board.getSeq() %></td>
            <td><a href="getBoard.do?seq=<%=board.getSeq() %>"><%=board.getTitle() %></a></td>
            <td><%=board.getWriter() %></td>
            <td><%=board.getRegdate() %></td>
            <td><%=board.getCnt() %></td>
         </tr>
         
         <%
            }
         %>
      </table>
      
   </center>
</body>
</html>