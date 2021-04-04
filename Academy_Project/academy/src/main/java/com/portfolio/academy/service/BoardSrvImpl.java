package com.portfolio.academy.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.BoardVO;
import com.portfolio.academy.model.UserVO;
import com.portfolio.academy.paging.PagingVO;
import com.portfolio.academy.repository.BoardDao;

@Service
public class BoardSrvImpl implements BoardSrv{
	
	@Resource(name="uploadPath")
	private String uploadPath; // 업로드된 파일 저장 경로
	
	private static final Logger logger = LoggerFactory.getLogger(BoardSrvImpl.class);
	
	@Autowired
	BoardDao boardDao;

	@Override
	public String setBoard(BoardVO bvo) {
		return boardDao.setBoard(bvo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		return boardDao.getBoardList();
	}

	@Override
	public List<UserVO> getUserAuth() {
		return boardDao.getUserAuth();
	}

	@Override
	public BoardVO getBoardOne(String boardCode) {
		return boardDao.getBoardOne(boardCode);
	}

	@Override
	public void modifyBoard(BoardVO bvo) {
		boardDao.modifyBoard(bvo);
	}

	@Override
	public void deleteBoard(List<String> chkArr) {
		boardDao.deleteBoard(chkArr);
	}

	@Override
	public List<ArticleVO> getArticleList(String boardCode, String searchOpt, String searchWords,PagingVO paging) {
		return boardDao.getArticleList(boardCode, searchOpt, searchWords, paging);
	}

	@Override
	public int articleCount(String boardCode) {
		return boardDao.articleCount(boardCode);
	}

}
