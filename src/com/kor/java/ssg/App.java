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
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, list);
		
		
		int hit = 0;

		while (true) {
			System.out.print("명령어 ) ");
			String command = sc.nextLine();
			command = command.trim();
			if (command.equals("System exit")) {

				break;
				
				
			} else if (command.equals("article write")) {
				articleController.dowrite();
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
				
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
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
				memberController.doSignIn(sc);
			
			}

			else {
				System.out.printf("%s는 올바른 명령어가 아닙니다.\n", command);

			}

		}

		sc.close();
		System.out.println("====프로그램 종료.====");

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
