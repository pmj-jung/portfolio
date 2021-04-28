package com.portfolio.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.academy.model.BoardVO;
import com.portfolio.academy.paging.PagingVO;
import com.portfolio.academy.service.BoardSrv;
import com.portfolio.academy.service.CommentSrv;

@Controller
public class BoardCtr {
	
	@Autowired
	BoardSrv boardSrv;
	
	@Autowired
	CommentSrv commentSrv;
	
	@RequestMapping("/boardSetting")
	public ModelAndView getCommunity() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",boardSrv.getBoardList());
		mav.addObject("userAuth", boardSrv.getUserAuth());
		mav.setViewName("board_setting");
		return mav;
	}
	
	@RequestMapping(value = "/setBoard", method = RequestMethod.POST)
	@ResponseBody
	public String setBoard(@ModelAttribute BoardVO bvo) {
		// 1. 게시판코드,게시판이름,게시판설명의 앞뒤공백제거 후 저장
		bvo.setBoardCode(bvo.getBoardCode().trim());
		bvo.setBoardName(bvo.getBoardName().trim());
		bvo.setBoardDesc(bvo.getBoardDesc().trim());
		
		// 2. 게시판코드가 중복이 아니라면..
		// 게시판 테이블(board)에 게시판 설정 저장 후, Repository에서
		// 해당 게시판코드의 게시글테이블 생성(테이블명 : article_boardCode),
		// 해당 게시판코드의 댓글테이블 생성(테이블명 : comment_boardCode)
		String result = boardSrv.setBoard(bvo);
		
		// failure : 게시판코드 중복
		// success : 게시판설정저장+게시글테이블생성+댓글테이블생성 완료
		return result;
	}
	
	@RequestMapping(value="/getBoardOne", method = RequestMethod.POST)
	@ResponseBody
	public BoardVO getBoardOne(@RequestParam String boardCode) {
		return boardSrv.getBoardOne(boardCode);
	}
	
	@RequestMapping(value="/modifyBoard", method = RequestMethod.POST)
	@ResponseBody
	public String modifyBoard(@ModelAttribute BoardVO bvo) {
//		System.out.println("boardCode: " +  bvo.getBoardCode() + "\nboardName : " + bvo.getBoardName() + "\nboardDesc : " + bvo.getBoardDesc());
//		System.out.println("read: " + bvo.getBoardRead() + "\nwrite : " + bvo.getBoardWrite() + "\ncomment : " + bvo.getBoardComment() + "\ndownload : " + bvo.getBoardDownload());
		boardSrv.modifyBoard(bvo);
		return "success";
	}
	
	@RequestMapping(value="/deleteBoard", method = RequestMethod.POST)
	@ResponseBody
	public String deleteBoard(@RequestParam(value="chkArr[]") List<String> chkArr) {
		boardSrv.deleteBoard(chkArr);
		return "success";
	}
	
	@RequestMapping(value="/board")
	public ModelAndView getBoard(
			@RequestParam String boardCode,
			@RequestParam(defaultValue = "article_subject") String searchOpt,
			@RequestParam(defaultValue = "") String searchWords,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardSrv.getBoardList()); // 게시판목록 가져오기
		mav.addObject("board",boardSrv.getBoardOne(boardCode));// 해당 게시판코드의 게시판 기본설정 가져오기
		
		int total = boardSrv.articleCount(boardCode);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "10";
		}
		
		PagingVO paging = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		mav.addObject("article", boardSrv.getArticleList(boardCode, searchOpt, searchWords,paging)); // 해당 게시판코드의 게시글 목록 가져오기
		mav.addObject("searchOpt",searchOpt);
		mav.addObject("searchWords",searchWords);
		mav.addObject("paging", paging);
		mav.setViewName("board");
		return mav;
	}
}
