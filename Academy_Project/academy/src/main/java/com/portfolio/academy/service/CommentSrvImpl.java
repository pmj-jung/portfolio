package com.portfolio.academy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.academy.model.CommentVO;
import com.portfolio.academy.repository.CommentDao;

@Service
public class CommentSrvImpl implements CommentSrv {
	
	@Autowired
	CommentDao commentDao;

	@Override
	public void setComment(CommentVO cvo) {
		commentDao.setComment(cvo);
	}

	@Override
	public List<CommentVO> getCommentList(CommentVO cvo) {
		return commentDao.getCommentList(cvo);
	}

	@Override
	public int getCommentCount(CommentVO cvo) {
		return commentDao.getCommentCount(cvo);
	}

	@Override
	public void commentDelete(CommentVO cvo) {
		commentDao.commentDelete(cvo);
	}

	@Override
	public void modifyComment(CommentVO cvo) {
		commentDao.modifyComment(cvo);
	}

	@Override
	public void setCommentReply(CommentVO cvo) {
		commentDao.setCommentReply(cvo);
	}

	
}
