package com.kor.java.ssg;

import java.util.Scanner;



public class Main {
	public static void main(String[]args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("명령어를 입력해주세요");
		
		String command = sc.nextLine();
		
		System.out.println("입력된 명령어 : "+command);
		
		command = sc.nextLine();
		
		System.out.println("입력된 명령어 : "+command);
		
		
		sc.close();
		
		System.out.println("== 프로그램  끝 ==");
	}
}
