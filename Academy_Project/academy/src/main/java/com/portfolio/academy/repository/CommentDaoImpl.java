package com.portfolio.academy.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.academy.model.CommentVO;

@Repository
public class CommentDaoImpl implements CommentDao{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void setComment(CommentVO cvo) {
		sqlSession.insert("comment.setComment", cvo);
	}

	@Override
	public List<CommentVO> getCommentList(CommentVO cvo) {
		return sqlSession.selectList("comment.getCommentList", cvo);
	}

	@Override
	public int getCommentCount(CommentVO cvo) {
		return sqlSession.selectOne("comment.getCommentCount", cvo);
	}

	@Override
	public void commentDelete(CommentVO cvo) {
		sqlSession.update("comment.commentDelete",cvo);
	}

	@Override
	public void modifyComment(CommentVO cvo) {
		sqlSession.update("comment.modifyComment", cvo);
	}

	@Override
	public void setCommentReply(CommentVO cvo) {
		CommentVO vo = sqlSession.selectOne("comment.getReplyInfo", cvo);
		cvo.setCmntRef(vo.getCmntRef());
		cvo.setCmntLevel(vo.getCmntLevel());
		cvo.setCmntLayer(vo.getCmntLayer());
		
		sqlSession.update("comment.setCommentLevel", cvo);
		sqlSession.insert("comment.setCommentReply", cvo);
	}

}
