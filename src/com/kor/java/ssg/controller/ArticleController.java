package com.kor.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.Container.Container;
import com.kor.java.ssg.Service.ArticleService;
import com.kor.java.ssg.Service.MemberService;
import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.dto.Member;
import com.kor.java.ssg.util.Util;

public class ArticleController extends Controller {
	private List<Article> list;
	private Scanner sc;
	private int hit;
	private String command;
	private String actionMethodName;
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController(Scanner sc) {
		this.sc = sc;
		articleService = Container.articleservice;
		memberService = Container.memberservice;
		
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "write":
			if (isSignined() == false) {
				System.out.println("로그인 후 이용해주세요");
				break;
			}
			doWrite();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		case "detail":
			showDetail();
			break;
		default:
			System.out.print("this command is not exist\n");

		}

	}

	private void doWrite() {

		int lastId = Container.articleDao.getNewId();
		System.out.print("제목 ) ");
		String title = sc.nextLine();
		System.out.print("내용 ) ");
		String body = sc.nextLine();
		String regDate = Util.getNowDatestr();
		Article article = new Article(lastId, title, body, loginedMember.id, regDate);
		Container.articleDao.add(article);
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

			String writerName = null;

			List<Member> members = Container.memberDao.members;
			for (Member member : members) {
				if (article.memberId == member.id) {
					writerName = member.name;
					break;
				}
			}

			System.out.printf("			%d		|		%s		|		%d		|		%s		|		%s\n",
					article.id, article.title, hit, writerName, article.regDate);
		}

	}

	private void showDetail() {
		String[] commandsBits = command.split(" ");
		int id = Integer.parseInt(commandsBits[2]);

		Article fdarticle = articleService.getArticleById(id);

		if (fdarticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		fdarticle.increastHit();

		System.out.printf("번호 : %d\n", fdarticle.id);
		System.out.printf("날짜 : %s\n", fdarticle.regDate);
		System.out.printf("작성자 : %s\n", fdarticle.userId);
		System.out.printf("제목 : %s\n", fdarticle.title);
		System.out.printf("내용 : %s\n", fdarticle.body);
		System.out.printf("조회 : %d\n", fdarticle.hit);

	}

	private void doDelete() {
		String[] commandsBits = command.split(" ");
		int id = Integer.parseInt(commandsBits[2]);

		Article fdArticle = getArticleById(id);
		if (fdArticle == null) {
			System.out.printf("%d번쨰 게시물은 존재하지 않습니다.\n", id);
		}
		if (fdArticle.memberId != loginedMember.id)
			System.out.printf("%d번째 게시물을 삭제하였습니다.\n", id);
		list.remove(fdArticle);
	}

	private void doModify() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article fdarticle = getArticleById(id);

		if (fdarticle == null) {
			System.out.printf("%d번째 게시물은 존재하지 않습니다.\n");
		}
		if (fdarticle.memberId != loginedMember.id) {
			System.out.println("권한이 없습니다.");
		}
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

		Container.articleDao
				.add(new Article(Container.articleDao.getNewId(), "제목1", "내용1", 1, Util.getNowDatestr()));
		Container.articleDao
				.add(new Article(Container.articleDao.getNewId(), "제목2", "내용2", 1, Util.getNowDatestr()));
		Container.articleDao
				.add(new Article(Container.articleDao.getNewId(), "제목3", "내용3", 1, Util.getNowDatestr()));

	}

}
