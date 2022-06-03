package com.kor.java.ssg.Dao;

import java.util.ArrayList;
import java.util.List;

import com.kor.java.ssg.dto.Article;

public class ArticleDao extends Dao {
	public List<Article>list;
	
	public ArticleDao(){
		list = new ArrayList<>();
	}
	public void add(Article article) {
		list.add(article);
		lastId = article.id;
	}
	public int getLastID() {
		return lastId;
	}
	public int getNewId() {
		return lastId+1;
	}
	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword != null && searchKeyword.length() != 0) {
				List<Article> forPrintArticles = new ArrayList<>();
					for(Article article : list) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
		
		return forPrintArticles;
		}
		return list;
	}
	public int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : list) {
			if(article.id == id) {
				return i;
			}
			i++;
			
		}
		return -1;
	}
	public Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		
		if (index != -1) {
			return list.get(index);
		}
		return null;
	}
	public void remove(Article fdarticle) {
		list.remove(fdarticle);
	}
}
