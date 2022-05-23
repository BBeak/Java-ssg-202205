package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		System.out.println("==== 프로그램 시작 ==== ");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		int lastArticleid = 3;
		int hit = 0;

		while (true) {
			System.out.print("명령어) ");
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
				System.out.printf("%d번째 게시물이 등록되었습니다. Regstered Time : %s\n", lastId, regDate);
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
					System.out.print("등록된 게시물이 없습니다\n");
					continue;
				}
				System.out.printf("		번호		|			제목		|		조회수		|		게시시간		\n");

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
								"			번호		|		제목		|		내용		|		조회수		|		등록시간		|\n");
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
					System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
				}
				System.out.printf("%d번 게시물이 삭제되었습니다\n", id);
				list.remove(foundIndex);

			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article fdarticle = getArticleById(id);

				if (fdarticle == null) {
					System.out.printf("%d번 게시물을 존재하지 않습니다.");
				}
				System.out.printf("제목 )");
				String title = sc.nextLine();
				System.out.printf("내용 )");
				String body = sc.nextLine();

				fdarticle.title = title;
				fdarticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

			} else if (command.equals("sign up")) {
				int id = members.size() + 1;
				String loginId = null;
				while (true) {
					System.out.print("User Id :");
					loginId = sc.nextLine();

					if (isJoinableId(loginId) == false) {
						System.out.printf("%s는 이미 사용중인 계정입니다.",loginId);
						continue;
					}
					break;
				}
				String loginPw = null;
				String loginPwCf = null;

				while (true) {
					System.out.print("PASSWORD ) ");
					loginPw = sc.nextLine();
					System.out.print("PASSWORD CONFIRM ) ");
					loginPwCf = sc.nextLine();

					if (loginPw.equals(loginPwCf) == false) {
						System.out.printf("비밀번호가 일치하지 않습니다. 비밀번호를 다시 입력해주세요\n");
						continue;
					}
					break;
				}
				String regDate = Util.getNowDatestr();
				Member member = new Member(id, loginId, loginPw, regDate);
				members.add(member);
				System.out.printf("%d번째 회원이 가입되었습니다. Regstered Time : %s\n", id, regDate);
			} else {
				System.out.printf("%s는 올바른 명령어가 아닙니다.\n", command);

			}

		}

		sc.close();
		System.out.println("====프로그램을 종료합니다.====");

	}

	private boolean isJoinableId(String loginId) {
		int index = getMemberindexById(loginId);
			
				if( index == -1) {
					return true;
				}
			
		
		return false;
	}

	private int getMemberindexById(String loginId) {
		int i = 0; 
		for (Member member : members) {
			if(member.loginId.equals(loginId)) {
				return i;
			}
			
		}
		return -1;
	}

	private void makeTestData() {
		System.out.println("==테스트를 위한 데이터를 생성합니다.==");

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
