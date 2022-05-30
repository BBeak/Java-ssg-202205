package com.kor.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.dto.Article;
import com.kor.java.ssg.dto.Member;
import com.kor.java.ssg.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	

	public MemberController(Scanner sc, List<Member> members) {
		this.members = members;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "signup":
			doSignUp();
			break;
		case "signin":
			doSignIn();
			break;
		case "signout":
			dosignout();
			break;
		default:
			System.out.print("this command is not exist\n");
		}
	}

	private void dosignout() {
		if (isSignined() == false) {
			System.out.println("you are not signined before");
			return;
		}
		loginedMember = null;
		System.out.println("you are sign out now");

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

	public void doSignUp() {

		int id = members.size() + 1;
		String regDate = Util.getNowDatestr();
		String loginId = null;
		String name = null;
		while (true) {
			System.out.print("User Id :");
			loginId = sc.nextLine();

			if (isJoinableId(loginId) == false) {
				System.out.printf("%s는 이미 사용중인 Id입니다.\n", loginId);
				continue;
			}
			System.out.print("이름을 입력해주세요 )");
			name = sc.nextLine();
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
			} else {

				break;

			}

		}

		Member member = new Member(id, loginId, loginPw, name, regDate);
		members.add(member);
		System.out.printf("%d번째 회원가입이 완료되었습니다. Regstered Time : %s\n", id, regDate);

	}

	public void doSignIn() {
		if ( isSignined() ) {
			System.out.println("you are already signined");
			return;
		}

		System.out.print("ID ) ");
		String userId = sc.nextLine();
		System.out.print("PW ) ");
		String userPW = sc.nextLine();

		Member member = getMemberByuserId(userId);

		if (member == null) {
			System.out.println("this user is not exist please try again or do sign up");
			return;
		}
		if (member.loginPw.equals(userPW) == false) {
			System.out.println("check your password again");
			return;
		}

		loginedMember = member;

		System.out.printf("welcome %s\n", loginedMember.name);

	}

	private Member getMemberByuserId(String userId) {
		int index = getMemberindexByUserId(userId);
		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private int getMemberindexByUserId(String userId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(userId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public void domakeTestDatas() {
		System.out.println("==테스트 계정을 생성합니다.==");

		members.add(new Member(1, "admin", "admin", "관리자", Util.getNowDatestr()));
		members.add(new Member(2, "user1", "123", "사용자1", Util.getNowDatestr()));
		members.add(new Member(3, "user2", "123", "사용자2", Util.getNowDatestr()));

	}

}
