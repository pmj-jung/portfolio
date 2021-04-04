package com.portfolio.academy.model;

public class LikesVO {
	
	// likes 테이블의 정보
	private int lid, boardId, articleId, userId;
	
	// likes 테이블 업데이트 후, article_(boardCode) 테이블의 변경을 위해 만든 변수
	private String boardCode;

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	@Override
	public String toString() {
		return "[likes TABLE] id : " + lid + ", board : " + boardId + ", article : " + articleId + ", user : " + userId  + ", boardCode : " + boardCode;
	}

}
