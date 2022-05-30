package com.kor.java.ssg.controller;

import com.kor.java.ssg.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	public abstract void doAction(String command, String actionMethodName);
	
	public static boolean isSignined() {
		return loginedMember != null;
	}
}