package com.portfolio.academy.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.LikesVO;

@Repository
public class ArticleDaoImpl implements ArticleDao{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public void deleteArticle(String boardCode, List<String> chkArr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		for(int i=0; i < chkArr.size(); i++) {
			map.put("aid", chkArr.get(i));
			sqlSession.update("article.deleteArticle",map);
			sqlSession.delete("comment.deleteComments", map);
		}
	}

	@Override
	public void articleWrite(ArticleVO avo) {
		sqlSession.insert("article.articleWrite", avo);
	}

	@Override
	public ArticleVO articleView(ArticleVO avo) {
		sqlSession.update("article.hitUp",avo);
		return sqlSession.selectOne("article.articleView", avo);
	}

	@Override
	public void delArticleOne(ArticleVO avo) {
		sqlSession.update("article.deleteArticle", avo);
		sqlSession.delete("comment.deleteComments", avo);
	}

	@Override
	public void articleLikes(LikesVO lvo) {
		int result = sqlSession.selectOne("article.likeCheck", lvo); // 해당 게시판&게시글에 추천을 눌렀으면 1, 안눌렀으면 0
//		System.out.println("[ArticleDaoImpl.articleLikes] result : " + result);
		
		// 추천 활성화
		if( result == 0 ) {
			sqlSession.insert("article.likeUp", lvo); // likes 테이블 insert
			sqlSession.update("article.articleLikeUp", lvo); // 해당 게시글 테이블의 추천수 update
		}else {
			// 추천 비활성화
			sqlSession.delete("article.likeDown", lvo); // likes 테이블 delete
			sqlSession.update("article.articleLikeDown", lvo); // 해당 게시글 테이블의 추천수 update
		}
	}

	@Override
	public void articleModify(ArticleVO avo) {
		sqlSession.update("article.articleModify", avo);
	}

	@Override
	public String getFileName(ArticleVO avo) {
		return sqlSession.selectOne("article.getFileName", avo);
	}

	@Override
	public void setArticleReply(ArticleVO avo) {
		sqlSession.update("article.setArticleLevel", avo); // articleLevel(순서) UPDATE
		sqlSession.insert("article.setArticleReply", avo); // 답글 INSERT
	}
	
}
