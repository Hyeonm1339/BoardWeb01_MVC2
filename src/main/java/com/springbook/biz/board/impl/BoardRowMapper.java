package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springbook.biz.board.BoardVO;

public class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO bVo = new BoardVO();
		bVo.setSeq(rs.getInt("seq"));
		bVo.setTitle(rs.getString("title"));
		bVo.setWriter(rs.getString("writer"));
		bVo.setContent(rs.getString("content"));
		bVo.setRegdate(rs.getDate("regdate"));
		bVo.setCnt(rs.getInt("cnt"));
		
		return bVo;
	}

}
