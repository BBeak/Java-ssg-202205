package com.kor.java.ssg;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		int lastArticleid = 0;
		List<Article> list = new ArrayList<Article>();
		String title;
		String body;
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH : mm : ss");
		String formatedNow = now.format(formatter);

		while (true) {
			System.out.print("��ɾ�) ");

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
				System.out.printf("���� : ");
				title = sc.nextLine();
				System.out.printf("���� : ");
				body = sc.nextLine();

				list.add(new Article(id, title, body, formatedNow));

				System.out.printf("%d��° ���� �ۼ��Ǿ����ϴ�.\n", id);

			} else if (command.equals("article list")) {
				if (list.size() == 0) {
					System.out.println("�ۼ��� �Խù��� �����ϴ�.");
				}
				if (list.size() != 0) {
					System.out.printf(" ���� %d���� ���� �ֽ��ϴ�.\n", lastArticleid);
					System.out.printf("	��ȣ	|	����	|	����		|	�ð�\n");
					for (int i = 0; i < list.size(); i++) {
						System.out.printf("	%d	|	%s	|	%s	| 	%s\n", list.get(i).getid(), list.get(i).getTitle(),
								list.get(i).getbody(), list.get(i).gettime());
					}
				}

			} else if (command.startsWith("article detail")) {

				String[] commandBits = command.split(" "); 
				int id = Integer.parseInt(commandBits[2]); 
				

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getid() == id) {

						System.out.printf("	��ȣ	|	����	|	 ����	|	�ð�\n");
						System.out.printf("	%d	|	%s	|	%s	| 	%s\n", list.get(i).getid(), list.get(i).getTitle(),
								list.get(i).getbody(), list.get(i).gettime());
						break;
					}

				}

				if (list.get(id-1).getid() != id) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

			}

			else if (command.equals("article delete")) {
				System.out.println("������ ���ϴ� �Խù��� ��ȣ�� �Է����ּ���");
				int d = sc.nextInt();
				sc.nextLine();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getid() == d) {

						list.remove(i);
						break;
					}

				}

			}

			else {
				System.out.printf("%s(��)�� �������� �ʴ� ��ɾ��Դϴ�.\n", command);
			}
		}

		sc.close();

		System.out.println("== ���α׷�  �� ==");
	}
}

class Article {
	private int id;
	private String title;
	private String body;
	private String time;

	public Article(int id, String title, String body, String time) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.time = time;
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

	public String gettime() {
		return time;
	}
}
