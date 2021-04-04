package com.portfolio.academy.repository;

import java.util.List;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.BoardVO;
import com.portfolio.academy.model.UserVO;
import com.portfolio.academy.paging.PagingVO;

public interface BoardDao {
	public String setBoard(BoardVO bvo);
	public List<BoardVO> getBoardList();
	public List<UserVO> getUserAuth();
	public BoardVO getBoardOne(String boardCode);
	public void modifyBoard(BoardVO bvo);
	public void deleteBoard(List<String> chkArr);
	public List<ArticleVO> getArticleList(String boardCode, String searchOpt, String searchWords, PagingVO paging);
	public int articleCount(String boardCode);
}
