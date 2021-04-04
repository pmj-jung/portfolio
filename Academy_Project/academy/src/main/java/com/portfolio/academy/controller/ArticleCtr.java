package com.portfolio.academy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.LikesVO;
import com.portfolio.academy.service.ArticleSrv;
import com.portfolio.academy.service.BoardSrv;

@Controller
public class ArticleCtr {
	
	@Autowired
	BoardSrv boardSrv;
	
	@Autowired
	ArticleSrv articleSrv;
	
	@Resource(name="uploadPath")
	private String downloadPath;

	@RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
	@ResponseBody
	public String deleteArticle(
			@RequestParam String boardCode,
			@RequestParam(value = "chkArr[]") List<String> chkArr) {
		articleSrv.deleteArticle(boardCode, chkArr);
		return "success";
	}
	
	@RequestMapping(value = "/articleWrite", method = RequestMethod.GET)
	public ModelAndView articleWrite(@RequestParam String boardCode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardCode", boardCode); // 글쓰기버튼 눌러서 들어온 게시판코드
		
		// 1. 게시판목록불러와서 뿌려주고, 뷰단에서 초기게시판코드에 해당하는 게시판을 selected
		mav.addObject("boardList", boardSrv.getBoardList());
		
		mav.setViewName("board_write");
		return mav;
	}
	
	@RequestMapping(value="/articleWrite", method = RequestMethod.POST)
	public String articleWrite(
			@ModelAttribute ArticleVO avo,
			@RequestParam("files") MultipartFile file) throws Exception {
		articleSrv.articleWrite(avo, file);
		return "redirect:/board?boardCode=" + avo.getBoardCode();
	}
	
	@RequestMapping(value = "/articleView", method = RequestMethod.GET)
	public ModelAndView articleView(
			@ModelAttribute ArticleVO avo,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", boardSrv.getBoardOne(avo.getBoardCode()));
		mav.addObject("article", articleSrv.articleView(avo));
		mav.setViewName("board_view");
		return mav;
	}
	
	@RequestMapping(value = "/delArticleOne")
	@ResponseBody
	public String delArticleOne(@ModelAttribute ArticleVO avo) {
		articleSrv.delArticleOne(avo);
		return "success";
	}
	
	@RequestMapping(value = "/articleLikes")
	@ResponseBody
	public String articleLikes(@ModelAttribute LikesVO lvo) {
//		System.out.println(lvo.toString());
		articleSrv.articleLikes(lvo);
		return "success";
	}
	
	@RequestMapping(value = "/articleDownload")
	public void fileDownload(@RequestParam String filename, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String browser = request.getHeader("User-Agent");
			// System.out.println("broswer: " + browser);
			
			if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			} else {
				filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			System.out.println("UnsupportedEncodingException");
		}
		
		String realFilename = downloadPath + filename;
		//System.out.println(realFilename);
		File file1 = new File(realFilename);
		if(!file1.exists()) {
			return ;
		}
		
		// 파일명 지정
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attchment; filename=\"" + filename + "\"");
		
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFilename);
			
			int ncount = 0;
			byte[] bytes = new byte[512];
			
			while((ncount = fis.read(bytes)) != -1 ) {
				os.write(bytes, 0, ncount);
			}
			
			fis.close();
			os.close();
		} catch(Exception e) {
			System.out.println("FileNotFoundException : " + e);
		}
		
	}
	
	@RequestMapping(value = "/articleModify", method = RequestMethod.GET)
	public ModelAndView getArticleModify(@ModelAttribute ArticleVO avo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardCode", avo.getBoardCode());
		mav.addObject("boardList", boardSrv.getBoardList());
		mav.addObject("article", articleSrv.articleView(avo));
		mav.setViewName("board_modify");
		return mav;
	}
	
	@RequestMapping(value = "/articleModify", method = RequestMethod.POST)
	public String setArticleModify(@ModelAttribute ArticleVO avo, @RequestParam("files") MultipartFile file) throws Exception {
		articleSrv.articleModify(avo, file);
		return "redirect:/articleView?boardCode=" + avo.getBoardCode() + "&aid=" + avo.getAid();
	}
	
	@RequestMapping(value = "/articleReply", method = RequestMethod.GET)
	public ModelAndView getArticleReply(@ModelAttribute ArticleVO avo) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("article", articleSrv.articleView(avo)); // 원글에 대한 정보
		mav.addObject("boardList", boardSrv.getBoardList());
		mav.addObject("boardCode", avo.getBoardCode());
		mav.setViewName("board_reply");
		return mav;
	}
	
	@RequestMapping(value = "/articleReply", method = RequestMethod.POST)
	public String setArticleReply(@ModelAttribute ArticleVO avo, @RequestParam("files") MultipartFile file) throws Exception {
		articleSrv.setArticleReply(avo, file);
		return "redirect:/board?boardCode=" + avo.getBoardCode();
	}
}
