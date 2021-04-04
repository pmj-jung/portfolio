package com.portfolio.academy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.academy.model.CommentVO;
import com.portfolio.academy.service.CommentSrv;

@Controller
public class CommentCtr {
	
	@Autowired
	CommentSrv commentSrv;

	@RequestMapping("/setComment")
	@ResponseBody
	public String setComment(@ModelAttribute CommentVO cvo) {
		commentSrv.setComment(cvo);
		return "success";
	}
	
	@RequestMapping("/getCommentList")
	@ResponseBody
	public Map<String, Object> getCommentList(@ModelAttribute CommentVO cvo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count",commentSrv.getCommentCount(cvo)); // 댓글 수
		map.put("list", commentSrv.getCommentList(cvo)); // 댓글 목록
		return map;
	}
	
	@RequestMapping("/commentDelete")
	@ResponseBody
	public String commentDelete(@ModelAttribute CommentVO cvo) {
		commentSrv.commentDelete(cvo);
		return "success";
	}
	
	@RequestMapping("/modifyComment")
	@ResponseBody
	public String modifyComment(@ModelAttribute CommentVO cvo) {
		commentSrv.modifyComment(cvo);
		return "success";
	}
	
	@RequestMapping("/commentReply")
	@ResponseBody
	public String setCommentReply(@ModelAttribute CommentVO cvo) {
		commentSrv.setCommentReply(cvo);
		return "success";
	}
} 
