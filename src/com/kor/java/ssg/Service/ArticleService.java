package com.kor.java.ssg.Service;

import java.util.List;

import com.kor.java.ssg.Container.Container;
import com.kor.java.ssg.Dao.ArticleDao;
import com.kor.java.ssg.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	public List<Article>getForPrintArticles(String searchKeyword){
		return articleDao.getArticles(searchKeyword);
	}
	public Article getArticleById(int id) {
		
		return articleDao.getArticleById(id);
	}
	public int getArticleIndexbyId(int id) {
		return articleDao.getArticleIndexById(id);
	}
	public void remove(Article fdarticle) {
		articleDao.remove(fdarticle);
	}
	public List<Article>getForPrintArticles(){
		return articleDao.getArticles(null);
	}
	
}
