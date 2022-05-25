package com.kor.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Member;
import com.kor.java.ssg.util.Util;

public class MemberController {
	private List<Member>members;
	private Scanner sc;
	
	public MemberController(Scanner sc, List<Member>members){
		this.members = members;
		this.sc = sc;
	}
	
	private int getMemberindexById(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}

		}
		return -1;
	}

	private boolean isJoinableId(String loginId) {
		int index = getMemberindexById(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	
	public void doSignUp(Scanner sc) {
		int id = members.size() + 1;
		String regDate = Util.getNowDatestr();
		String loginId = null;
		while (true) {
			System.out.print("User Id :");
			loginId = sc.nextLine();

			if (isJoinableId(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 Id입니다.\n", loginId);
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
				System.out.printf("비밀번호가 일치하지 않습니다 다시 입력하여주십시오.\n");
				continue;
			}else {
		
			break;
			
			}
			
			}
		
			Member member = new Member(id, loginId, loginPw, regDate);
			members.add(member);
			System.out.printf("%d번째 회원가입이 완료되었습니다. Regstered Time : %s\n", id, regDate);
			
			
			
		
	}
	public void doSignIn(Scanner sc) {
		
		Member user = null;
		while (true) {
			System.out.print("User Id )");
			String userId = sc.nextLine();
			System.out.print("User Pw )");
			String userPw = sc.nextLine();
			user = signin(userId, userPw);
			
			if (user == null) {
			continue;
			
	}
		}
	}
			private Member signin(String id, String password) {

				for (Member member : members) {
					if (member.loginId.equals(id) && member.loginPw.equals(password)) {
						System.out.printf("%s님 환영합니다.\n", id);
						return member;
					} else {
						System.out.println("Id 나 pw가 일치하지 않습니다.\n");
						continue;
					}
}
				return null;

			}
}
