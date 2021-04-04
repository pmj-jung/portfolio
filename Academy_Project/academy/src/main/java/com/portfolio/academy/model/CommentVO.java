package com.portfolio.academy.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommentVO {

	/* 테이블 명 : comment_(board_code) */
	private int cid;
	private int articleId;
	private String cmntContent;
	private int cmntWriter;
	private String cmntWriterName;
	private int cmntRef;
	private int cmntLevel;
	private int cmntLayer;
	private Date cmntRegdate;
	
	private String boardCode;
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getCmntContent() {
		return cmntContent;
	}
	public void setCmntContent(String cmntContent) {
		this.cmntContent = cmntContent;
	}
	public int getCmntWriter() {
		return cmntWriter;
	}
	public void setCmntWriter(int cmntWriter) {
		this.cmntWriter = cmntWriter;
	}
	public String getCmntWriterName() {
		return cmntWriterName;
	}
	public void setCmntWriterName(String cmntWriterName) {
		this.cmntWriterName = cmntWriterName;
	}
	public int getCmntRef() {
		return cmntRef;
	}
	public void setCmntRef(int cmntRef) {
		this.cmntRef = cmntRef;
	}
	public int getCmntLevel() {
		return cmntLevel;
	}
	public void setCmntLevel(int cmntLevel) {
		this.cmntLevel = cmntLevel;
	}
	public String getCmntRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd kk:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(cmntRegdate);
	}
	public void setCmntRegdate(Date cmntRegdate) {
		this.cmntRegdate = cmntRegdate;
	}
	
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	public int getCmntLayer() {
		return cmntLayer;
	}
	public void setCmntLayer(int cmntLayer) {
		this.cmntLayer = cmntLayer;
	}
	
	@Override
	public String toString() {
		return "[CommentVO] cid : " + cid + ", articleId : " + articleId + ",boardCode : " + boardCode + ", cmntContent : " + cmntContent + 
				", cmntWriter : " + cmntWriter + ", cmntWriterName : " + cmntWriterName + ",cmntLevel : " + cmntLevel
				+ ",cmntLayer : " + cmntLayer +",cmntRegdate : " + cmntRegdate;
	}
	
}
