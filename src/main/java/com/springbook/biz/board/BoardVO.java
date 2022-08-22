package com.springbook.biz.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BoardVO {
	private int seq,cnt;
	private String title,writer,content;
	private Date regdate;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String searchCondition;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String searchKeyword;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private MultipartFile uploadFile;
	
}
