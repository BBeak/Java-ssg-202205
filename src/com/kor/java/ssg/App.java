package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.util.Util;

public class App {
	private List<Article> list;

	public App() {
		list = new ArrayList<>();
	}

	public void start() {
		System.out.println("==== ���α׷� ���� ==== ");

		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		int lastArticleid = 3;
		int hit = 0;

		while (true) {
			System.out.print("��ɾ�) ");
			String command = sc.nextLine();
			command = command.trim();
			if (command.equals("System exit")) {

				break;
			} else if (command.equals("article write")) {
				int lastId = lastArticleid += 1;
				lastArticleid = lastId;
				System.out.print("���� ) ");
				String title = sc.nextLine();
				System.out.print("���� ) ");
				String body = sc.nextLine();
				Article article = new Article(lastArticleid, title, body);
				list.add(article);
				System.out.printf("%d��° �Խù��� ��ϵǾ����ϴ�.\n", lastId);
			} else if (command.equals("article list")) {
				if (list.size() == 0) {
					System.out.print("��ϵ� �Խù��� �����ϴ�\n");
					continue;
				}
				System.out.print("	��ȣ	|	����	|	��ȸ��\n");
				if (list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						Article article = list.get(i);

						System.out.printf("	%d	|	%s	|	%d\n", article.id, article.title, hit);
					}
				}
			} else if (command.startsWith("article detail")) {
				String[] commandsBits = command.split(" ");
				int getId = Integer.parseInt(commandsBits[2]);

				Article fdarticle = null;

				for (int i = 0; i < list.size(); i++) {
					fdarticle = list.get(i);

					if (fdarticle.id == getId) {
						fdarticle.increastHit();
						System.out.printf("		��ȣ		|		����		|		����		|		��ȸ��		\n");
						System.out.printf("		%d		|		%s		|		%s		|		%d		\n", fdarticle.id, fdarticle.title,
								fdarticle.body, fdarticle.hit);

					}
				}
			} else if (command.startsWith("article delete")) {
				String[] commandsBits = command.split(" ");
				int getId = Integer.parseInt(commandsBits[2]);
				Article fdarticle = null;
				for (int i = 0; i < list.size(); i++) {
					fdarticle = list.get(i);
					if (fdarticle.id == getId) {
						list.remove(i);
					}
				}
			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article fdarticle = null;

				for (int i = 0; i < list.size(); i++) {
					Article article = list.get(i);

					if (article.id == id) {
						fdarticle = article;
						break;
					}
				}
				if (fdarticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.");
				}
				System.out.printf("���� )");
				String title = sc.nextLine();
				System.out.printf("���� )");
				String body = sc.nextLine();
				
				fdarticle.title = title;
				fdarticle.body = body;
				
				System.out.printf("%d�� �Խù��� �����Ǿ����ϴ�.\n", id);

			} else {
				System.out.printf("%s�� �ùٸ� ��ɾ �ƴմϴ�.\n", command);

			}

		}

		sc.close();
		System.out.println("====���α׷��� �����մϴ�.====");

	}

	private void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�.");
		
		list.add(new Article(1, "����1", "����1"));
		list.add(new Article(2, "����2", "����2"));
		list.add(new Article(3, "����3", "����3"));
		
		
	}

}
