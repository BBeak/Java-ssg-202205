package com.kor.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.util.Util;

public class ArticleController {
	private List<Article> list;
	private Scanner sc;
	private int hit;

	public ArticleController(Scanner sc, List<Article> list) {
		this.list = list;
		this.sc = sc;
	}

	public void dowrite() {

		int lastId = list.size() + 1;
		System.out.print("제목 ) ");
		String title = sc.nextLine();
		System.out.print("내용 ) ");
		String body = sc.nextLine();
		String regDate = Util.getNowDatestr();
		Article article = new Article(lastId, title, body, regDate);
		list.add(article);
		System.out.printf("%d번째 게시물이 등록되었습니다.. Regstered Time : %s\n", lastId, regDate);
	}

	public void showList(String command) {
		String searchKeyword = command.substring("article list".length()).trim();
		List<Article> forListArticles = list;

		if (searchKeyword.length() > 0) {
			forListArticles = new ArrayList<>();
			for (Article article : list) {
				if (article.title.contains(searchKeyword)) {
					forListArticles.add(article);
				}

			}
		}
		if (list.size() == 0) {
			System.out.print("현재 등록된 게시물이 없습니다. \n");
			
		}
		System.out.printf("		제목		|		제목		|		조회수		|		등록시간		\n");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("			%d		|		%s		|		%d		|		%s\n", article.id,
					article.title, hit, article.regDate);
		}

	}

	public void showDetail(String command) {
		String[] commandsBits = command.split(" ");
		int getId = Integer.parseInt(commandsBits[2]);
		System.out.println(getId);

		Article fdarticle = null;

		for (int i = 0; i < list.size(); i++) {
			fdarticle = list.get(i);

			if (fdarticle.id == getId) {
				fdarticle.increastHit();
				System.out.printf(
						"			번호		|		제목		|		내용		|		조회수		|	  등록시간		|\n");
				System.out.printf(
						"			%d		|		%s		|		%s		|		%d			|		%s			|\n",
						fdarticle.id, fdarticle.title, fdarticle.body, fdarticle.hit, fdarticle.regDate);
	}
		}
	}
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : list) {
			if (article.id == id) {
				return i;
			}
			i++;

		}
		return -1;
	}

	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		for (Article article : list) {

			if (article.id == index) {
				return article;

			}
		}
		return null;
	}
}
