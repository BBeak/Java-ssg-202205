package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.controller.ArticleController;
import com.kor.java.ssg.controller.MemberController;
import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.dto.Member;
import com.kor.java.ssg.util.Util;

public class App {
	private List<Article> list;
	private List<Member> members;

	public App() {
		list = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("==== 프로그램 시작 ====");
		makeTestData();
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController();
		Scanner sc = new Scanner(System.in);
		int lastArticleid = 3;
		int hit = 0;

		while (true) {
			System.out.print("명령어 ) ");
			String command = sc.nextLine();
			command = command.trim();
			if (command.equals("System exit")) {

				break;
			} else if (command.equals("article write")) {
				int lastId = lastArticleid += 1;
				lastArticleid = lastId;
				System.out.print("제목 ) ");
				String title = sc.nextLine();
				System.out.print("내용 ) ");
				String body = sc.nextLine();
				String regDate = Util.getNowDatestr();
				Article article = new Article(lastArticleid, title, body, regDate);
				list.add(article);
				System.out.printf("%d번째 게시물이 등록되었습니다.. Regstered Time : %s\n", lastId, regDate);
			} else if (command.startsWith("article list")) {

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
					continue;
				}
				System.out.printf("		제목		|		제목		|		조회수		|		등록시간		\n");

				for (int i = forListArticles.size() - 1; i >= 0; i--) {
					Article article = forListArticles.get(i);

					System.out.printf("			%d		|		%s		|		%d		|		%s\n", article.id,
							article.title, hit, article.regDate);
				}

			} else if (command.startsWith("article detail ")) {
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
			} else if (command.startsWith("article delete ")) {
				String[] commandsBits = command.split(" ");
				int id = Integer.parseInt(commandsBits[2]);

				int foundIndex = getArticleIndexById(id);
				if (foundIndex == -1) {
					System.out.printf("%d번쨰 게시물은 존재하지 않습니다.\n", id);
				}
				System.out.printf("%d번째 게시물을 삭제하였습니다.\n", id);
				list.remove(foundIndex);

			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article fdarticle = getArticleById(id);

				if (fdarticle == null) {
					System.out.printf("%d번째 게시물은 존재하지 않습니다.\n");
				}
				System.out.printf("제목 )");
				String title = sc.nextLine();
				System.out.printf("내용 )");
				String body = sc.nextLine();

				fdarticle.title = title;
				fdarticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

			} else if (command.equals("sign up")) {
				memberController.doSignUp(sc);
			
			} else if (command.equals("sign in")) {
				Member user = null;
				while (true) {
					System.out.print("User Id )");
					String userId = sc.nextLine();
					System.out.print("User Pw )");
					String userPw = sc.nextLine();
					user = signin(userId, userPw);
					
					if (user == null) {
					continue;
					
				}else {
					break;
				}
			}
			}

			else {
				System.out.printf("%s는 올바른 명령어가 아닙니다.\n", command);

			}

		}

		sc.close();
		System.out.println("====프로그램 종료.====");

	}

	

	

	private Member signin(String id, String password) {

		for (Member member : members) {
			if (member.loginId.equals(id) && member.loginPw.equals(password)) {
				System.out.printf("%s님 환영합니다.\n", id);
				return member;
			} else {
				System.out.println("Id 나 pw가 일치하지 않습니다.\n");
			}
			
		
		}
		return null;
	}

	private void makeTestData() {
		System.out.println("==테스트 데이터를 생성합니다..==");

		list.add(new Article(1, "제목1", "내용1", Util.getNowDatestr()));
		list.add(new Article(2, "제목2", "내용2", Util.getNowDatestr()));
		list.add(new Article(3, "제목3", "내용3", Util.getNowDatestr()));

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
