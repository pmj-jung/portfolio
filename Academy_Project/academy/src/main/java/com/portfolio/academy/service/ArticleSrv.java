package com.portfolio.academy.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.LikesVO;

public interface ArticleSrv {
	
	public void deleteArticle(String boardCode, List<String> chkArr);
	public void articleWrite(ArticleVO avo, MultipartFile file) throws Exception;
	public ArticleVO articleView(ArticleVO avo);
	public void delArticleOne(ArticleVO avo);
	public void articleLikes(LikesVO lvo);
	public void articleModify(ArticleVO avo, MultipartFile file) throws IOException;
	public void setArticleReply(ArticleVO avo, MultipartFile file) throws Exception;
}
