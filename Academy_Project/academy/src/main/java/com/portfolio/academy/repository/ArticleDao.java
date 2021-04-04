package com.portfolio.academy.repository;

import java.util.List;

import com.portfolio.academy.model.ArticleVO;
import com.portfolio.academy.model.LikesVO;

public interface ArticleDao {
	
	public void deleteArticle(String boardCode, List<String> chkArr);
	public void articleWrite(ArticleVO avo);
	public ArticleVO articleView(ArticleVO avo);
	public void delArticleOne(ArticleVO avo);
	public void articleLikes(LikesVO lvo);
	public void articleModify(ArticleVO avo);
	public String getFileName(ArticleVO avo);
	public void setArticleReply(ArticleVO avo);
}
