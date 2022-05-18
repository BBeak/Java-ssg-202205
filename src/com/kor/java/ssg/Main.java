package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		int lastArticleid = 0;
		List<article> list = new ArrayList<article>();
		String title;
		String body;

		while (true) {
			System.out.println("��ɾ�) ");
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
				list.add(new article(id, title, body));

				System.out.printf("%d��° ���� �ۼ��Ǿ����ϴ�.\n", id);

			} else if (command.equals("article list")) {
				if (list.size() == 0) {
					System.out.println("�ۼ��� �Խù��� �����ϴ�.");
				}
				if (list.size() != 0) {
					System.out.printf(" ���� %d���� ���� �ֽ��ϴ�.\n", lastArticleid);
					System.out.printf("	��ȣ	|	����	|	����\n");
					for (int i = 0; i < list.size(); i++) {
						System.out.printf("	%d	|	%s	|	%s\n", list.get(i).getid(), list.get(i).getTitle(),
								list.get(i).getbody());
					}
				}

			} else if (command.equals("search")) {

				System.out.println("������ ���ϴ� �Խù��� ��ȣ�� �Է����ּ���");
				int num = sc.nextInt();

				System.out.printf("	��ȣ	|	����	|	����\n");
				System.out.printf("	%d	|	%s	|	%s\n", list.get(num - 1).getid(), list.get(num - 1).getTitle(),
						list.get(num - 1).getbody());
			} else {
				System.out.printf("%s(��)�� �������� �ʴ� ��ɾ��Դϴ�.\n", command);
			}
		}

		sc.close();

		System.out.println("== ���α׷�  �� ==");
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
