package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.controller.ArticleController;
import com.kor.java.ssg.controller.Controller;
import com.kor.java.ssg.controller.MemberController;
import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.dto.Member;

public class App {
	private List<Article> list;
	private List<Member> members;

	public App() {
		list = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("==== 프로그램 시작 ====");

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, list);
		articleController.domakeTestData();
		Controller controller = null;
		while (true) {
			System.out.print("명령어 ) ");
			String command = sc.nextLine();
			command = command.trim();
			
			if (command.length() == 0) {
				continue;
			}
			
			if (command.equals("system exit")) {
				break;
			}
			
			String[] commandBits = command.split(" ");
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];
			
			if (commandBits.length == 1) {
				System.out.printf("%s is command is not exist command\n", command);
				continue;
			}
			if (controllerName.equals("article")) {
				controller = articleController;
			}
			else if (controllerName.equals("member")) {
				controller = memberController;
			}else {
				System.out.printf("%s는 올바른 명령어가 아닙니다.\n", command);

			}
			
		
			
			controller.doAction(command, actionMethodName);
		}

		sc.close();
		System.out.println("====프로그램 종료.====");

	}

}
