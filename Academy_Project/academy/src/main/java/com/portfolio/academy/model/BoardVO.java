package com.portfolio.academy.model;

public class BoardVO {
	
	/*	테이블 명 : board  */
	private int bid;
	private String boardCode;
	private String boardName;
	private String boardDesc;
	private int boardRead;
	private int boardWrite;
	private int boardComment;
	private int boardDownload;
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardDesc() {
		return boardDesc;
	}
	public void setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
	}
	public int getBoardRead() {
		return boardRead;
	}
	public void setBoardRead(int boardRead) {
		this.boardRead = boardRead;
	}
	public int getBoardWrite() {
		return boardWrite;
	}
	public void setBoardWrite(int boardWrite) {
		this.boardWrite = boardWrite;
	}
	public int getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(int boardComment) {
		this.boardComment = boardComment;
	}
	public int getBoardDownload() {
		return boardDownload;
	}
	public void setBoardDownload(int boardDownload) {
		this.boardDownload = boardDownload;
	}
}
