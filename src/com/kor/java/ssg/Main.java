package com.kor.java.ssg;

import java.util.Scanner;

import sun.java2d.opengl.WGLSurfaceData.WGLVSyncOffScreenSurfaceData;



public class Main {
	public static void main(String[]args) {
		System.out.println("== ���α׷� ���� ==");
		Scanner sc = new Scanner(System.in);
		
		
		
		while (true) {
			System.out.println("��ɾ�) ");
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
				System.out.printf("���� :");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				
				System.out.printf("%d��° ���� �ۼ��Ǿ����ϴ�.\n", lastArticleid);
				
			}
			else if(command.equals("article list")) {
				System.out.println("�Խù��� �����ϴ�.");
				
			}
			else {
				System.out.printf("%s(��)�� �������� �ʴ� ��ɾ��Դϴ�.\n", command);
			}
		}
		
		
		sc.close();
		
		System.out.println("== ���α׷�  �� ==");
	}
}
