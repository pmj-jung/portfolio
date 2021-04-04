package com.portfolio.academy.repository;

import java.util.List;

import com.portfolio.academy.model.CommentVO;

public interface CommentDao {

	public void setComment(CommentVO cvo);
	public List<CommentVO> getCommentList(CommentVO cvo);
	public int getCommentCount(CommentVO cvo);
	public void commentDelete(CommentVO cvo);
	public void modifyComment(CommentVO cvo);
	public void setCommentReply(CommentVO cvo);
}
