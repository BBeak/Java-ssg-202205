package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		int lastArticleid = 0;
		List<article> list = new ArrayList<article>();
		String title;
		String body;

		while (true) {
			System.out.println("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("System exit")) {
				break;
			} else if (command.equals("article write")) {
				int id = lastArticleid + 1;
				lastArticleid = id;
				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();
				list.add(new article(id, title, body));

				System.out.printf("%d번째 글이 작성되었습니다.\n", id);

			} else if (command.equals("article list")) {
				if (list.size() == 0) {
					System.out.println("작성된 게시물이 없습니다.");
				}
				if (list.size() != 0) {
					System.out.printf(" 현재 %d개의 글이 있습니다.\n", lastArticleid);
					System.out.printf("	번호	|	제목	|	내용\n");
					for (int i = 0; i < list.size(); i++) {
						System.out.printf("	%d	|	%s	|	%s\n", list.get(i).getid(), list.get(i).getTitle(),
								list.get(i).getbody());
					}
				}

			} else if (command.equals("search")) {

				System.out.println("열람을 원하는 게시물의 번호를 입력해주세요");
				int num = sc.nextInt();

				System.out.printf("	번호	|	제목	|	내용\n");
				System.out.printf("	%d	|	%s	|	%s\n", list.get(num - 1).getid(), list.get(num - 1).getTitle(),
						list.get(num - 1).getbody());
			} else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다.\n", command);
			}
		}

		sc.close();

		System.out.println("== 프로그램  끝 ==");
	}
}

class article {
	private int id;
	private String title;
	private String body;

	public article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getid() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getbody() {
		return body;
	}
}
