package com.portfolio.academy.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ArticleVO {

	private int aid;
	private String articleCategory;
	private String articleSubject;
	private int articleWriter;
	private String articleWriterName;
	private String articleContent;
	private Date articleRegdate;
	private int articleHit;
	private int articleLikes;
	private int articleSecret;
	private String articleFileName;
	private String articleFileOrigin;
	private String articleFileUrl;
	private int articleRef;
	private int articleLevel;
	private int articleLayer;
	
	private String boardCode;
	
	private int commentCount;
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}
	public String getArticleSubject() {
		return articleSubject;
	}
	public void setArticleSubject(String articleSubject) {
		this.articleSubject = articleSubject;
	}	
	public int getArticleWriter() {
		return articleWriter;
	}
	public void setArticleWriter(int articleWriter) {
		this.articleWriter = articleWriter;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleRegdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		return sdf.format(articleRegdate);
	}
	public void setArticleRegdate(Date articleRegdate) {
		this.articleRegdate = articleRegdate;
	}
	public int getArticleHit() {
		return articleHit;
	}
	public void setArticleHit(int articleHit) {
		this.articleHit = articleHit;
	}
	public int getArticleLikes() {
		return articleLikes;
	}
	public void setArticleLikes(int articleLikes) {
		this.articleLikes = articleLikes;
	}
	public int getArticleSecret() {
		return articleSecret;
	}
	public void setArticleSecret(int articleSecret) {
		this.articleSecret = articleSecret;
	}
	public String getArticleFileName() {
		return articleFileName;
	}
	public void setArticleFileName(String articleFileName) {
		this.articleFileName = articleFileName;
	}
	public String getArticleFileOrigin() {
		return articleFileOrigin;
	}
	public void setArticleFileOrigin(String articleFileOrigin) {
		this.articleFileOrigin = articleFileOrigin;
	}
	public String getArticleFileUrl() {
		return articleFileUrl;
	}
	public void setArticleFileUrl(String articleFileUrl) {
		this.articleFileUrl = articleFileUrl;
	}
	public int getArticleRef() {
		return articleRef;
	}
	public void setArticleRef(int articleRef) {
		this.articleRef = articleRef;
	}
	public int getArticleLevel() {
		return articleLevel;
	}
	public void setArticleLevel(int articleLevel) {
		this.articleLevel = articleLevel;
	}
	public String getArticleWriterName() {
		return articleWriterName;
	}
	public void setArticleWriterName(String articleWriterName) {
		this.articleWriterName = articleWriterName;
	}
	
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getArticleLayer() {
		return articleLayer;
	}
	public void setArticleLayer(int articleLayer) {
		this.articleLayer = articleLayer;
	}
	@Override
	public String toString() {
		return "aid: " + aid + ",articleCategory : " + articleCategory + ",articleSubject: " + articleSubject
				+ ",articleWriter: " + articleWriter + ",articleWriterName: " + articleWriterName
				+ ",articleContent: " + articleContent + ",articleRegdate: " + articleRegdate
				+ ",articleHit: " + articleHit + ",articleLikes: " + articleLikes + ",articleSecret: " + articleSecret
				+ ",articleFileName: " + articleFileName + ",articleFileOrigin: " + articleFileOrigin
				+ ",articleFileUrl: " + articleFileUrl + ",articleRef: " + articleRef + ",articleLevel: " + articleLevel
				+ ",boardCode: " + boardCode + ",commentCount : " + commentCount + ",articleLayer : " + articleLayer;
	}
	
}
