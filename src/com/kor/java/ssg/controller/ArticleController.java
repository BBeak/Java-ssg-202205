package com.kor.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.util.Util;

public class ArticleController extends Controller {
	private List<Article> list;
	private Scanner sc;
	private int hit;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc, List<Article> list) {
		this.list = list;
		this.sc = sc;
	}
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch(actionMethodName) {
		case "list" :
			showList();
			break;
		case "write" :
			if(isSignined() == false) {
				System.out.println("please write article after signin");
				break;
			}
			doWrite();
			break;
		case "delete" : 
			doDelete();
			break;
		case "modify" :
			doModify();
			break;
		case "detail" : 
			showDetail();
			break;
		default :
			System.out.print("this command is not exist\n");
		
		}
	
	}

	private void doWrite() {

		int lastId = list.size() + 1;
		System.out.print("제목 ) ");
		String title = sc.nextLine();
		System.out.print("내용 ) ");
		String body = sc.nextLine();
		String regDate = Util.getNowDatestr();
		Article article = new Article(lastId, regDate, regDate, loginedMember.id, title, body);
		list.add(article);
		System.out.printf("%d번째 게시물이 등록되었습니다.. Regstered Time : %s\n", lastId, regDate);
	}

	private void showList() {
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
		System.out.printf("		제목			|		제목		|		조회수		|		작성자		|		등록시간		\n");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("			%d		|		%s		|		%d		|		%s		|		%s\n", article.id,
					article.title, hit, loginedMember.name, article.regDate);
		}

	}

	private void showDetail() {
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
	private void doDelete() {
		String[] commandsBits = command.split(" ");
		int id = Integer.parseInt(commandsBits[2]);

		int foundIndex = getArticleIndexById(id);
		if (foundIndex == -1) {
			System.out.printf("%d번쨰 게시물은 존재하지 않습니다.\n", id);
		}
		System.out.printf("%d번째 게시물을 삭제하였습니다.\n", id);
		list.remove(foundIndex);
	}
	private void doModify() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		
		Article fdarticle = getArticleById(id);

		if (fdarticle == null) {
			System.out.printf("%d번째 게시물은 존재하지 않습니다.\n");
		}
		if (fdarticle.memberId != loginedMember.id)
		System.out.printf("제목 )");
		String title = sc.nextLine();
		System.out.printf("내용 )");
		String body = sc.nextLine();

		fdarticle.title = title;
		fdarticle.body = body;

		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

		
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

	public void domakeTestData() {
		System.out.println("== 테스트 데이터를 생성합니다..==");

		list.add(new Article(1, "제목1", "내용1", "master", Util.getNowDatestr()));
		list.add(new Article(2, "제목2", "내용2", "master", Util.getNowDatestr()));
		list.add(new Article(3, "제목3", "내용3", "master", Util.getNowDatestr()));

		
	}

}
