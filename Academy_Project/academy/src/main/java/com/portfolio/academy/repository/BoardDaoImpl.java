package com.portfolio.academy.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.BoardVO;
import com.portfolio.academy.model.UserVO;
import com.portfolio.academy.paging.PagingVO;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String setBoard(BoardVO bvo) {
		
		// boardCode 중복 확인( 결과가 0이 아니면 중복 )
		int result = sqlSession.selectOne("board.checkCode", bvo.getBoardCode());
		
		if( result > 0 ) {
			return "failure";
		}else {
			// 1. 게시판 설정 저장
			sqlSession.insert("board.setBoard", bvo);
			
			// 2. 게시글게시판 생성
			String articleStr = "CREATE TABLE article_" + bvo.getBoardCode();
			articleStr += "(id int not null auto_increment primary key,";
			articleStr += "article_category char(1) not null,";
			articleStr += "article_subject varchar(100) not null,";
			articleStr += "article_writer int not null,";
			articleStr += "article_content text,";
			articleStr += "article_regdate datetime,";
			articleStr += "article_hit int default 0,";
			articleStr += "article_likes int default 0,";
			articleStr += "article_secret boolean not null,";
			articleStr += "article_file_name varchar(300),";
			articleStr += "article_file_origin varchar(300),";
			articleStr += "article_file_url varchar(500),";
			articleStr += "article_ref int,";
			articleStr += "article_level int,";
			articleStr += "article_layer int);";
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("articleStr", articleStr);
			sqlSession.insert("board.setArticleTbl", map);
			
			// 3. 댓글게시판 생성
			String cmntStr = "CREATE TABLE comment_" + bvo.getBoardCode();
			cmntStr += "(id int not null auto_increment primary key,";
			cmntStr += "article_id int,";
			cmntStr += "cmnt_content text,";
			cmntStr += "cmnt_writer int,";
			cmntStr += "cmnt_ref int,";
			cmntStr += "cmnt_level int,";
			cmntStr += "cmnt_layer int,";
			cmntStr += "cmnt_regdate datetime);";
			
			map.put("cmntStr", cmntStr);
			sqlSession.insert("board.setCommentTbl", map);
			
			return "success";
		}
		
	}

	@Override
	public List<BoardVO> getBoardList() {
		return sqlSession.selectList("board.getBoardList");
	}

	@Override
	public List<UserVO> getUserAuth() {
		return sqlSession.selectList("board.getUserAuth");
	}

	@Override
	public BoardVO getBoardOne(String boardCode) {
		return sqlSession.selectOne("board.getBoardOne",boardCode);
	}

	@Override
	public void modifyBoard(BoardVO bvo) {
		sqlSession.update("board.modifyBoard", bvo);
	}

	@Override
	public void deleteBoard(List<String> chkArr) {
		// 1. board 테이블에서 지우기
		for(int i = 0; i < chkArr.size(); i++ ) {
			sqlSession.delete("board.deleteBoard",chkArr.get(i));
			
			// 2. 해당 boardCode의 게시글테이블(article_*), 댓글테이블(comment_*) 지우기
			Map<String, String> map = new HashMap<String, String>();
			String articleStr = "DROP TABLE article_" + chkArr.get(i);
			String commentStr = "DROP TABLE comment_" + chkArr.get(i);
			
			map.put("articleStr", articleStr);
			map.put("commentStr", commentStr);
			sqlSession.update("board.dropArticle", map);
			sqlSession.update("board.dropComment",map);
		}
		
	}

	@Override
	public List<ArticleVO> getArticleList(String boardCode, String searchOpt, String searchWords, PagingVO paging) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("searchOpt", searchOpt);
		map.put("searchWords", searchWords);
		map.put("start", paging.getStart());
		map.put("end", paging.getCntPerPage());
		return sqlSession.selectList("board.getArticleList", map);
	}

	@Override
	public int articleCount(String boardCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("boardCode", boardCode);
		return sqlSession.selectOne("board.articleCount", map);
	}

}
