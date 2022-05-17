package com.kor.java.ssg;

import java.util.Scanner;

import sun.java2d.opengl.WGLSurfaceData.WGLVSyncOffScreenSurfaceData;



public class Main {
	public static void main(String[]args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		
		
		while (true) {
			System.out.println("명령어) ");
			String command = sc.nextLine();
			int lastArticleid =0;
			
			command = command.trim();
			
			if (command.length()== 0) {
				continue;
			}
			
			if (command.equals("System exit")){
				break;
			}
			else if(command.equals("article write")) {
				int id = lastArticleid+1;
				lastArticleid = id;
				System.out.printf("제목 :");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번째 글이 작성되었습니다.\n", lastArticleid);
				
			}
			else if(command.equals("article list")) {
				System.out.println("게시물이 없습니다.");
				
			}
			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다.\n", command);
			}
		}
		
		
		sc.close();
		
		System.out.println("== 프로그램  끝 ==");
	}
}
