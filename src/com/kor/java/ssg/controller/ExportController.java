package com.kor.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.ssg.Container.Container;
import com.kor.java.ssg.Service.ExportService;
import com.kor.java.ssg.dto.Member;
import com.kor.java.ssg.util.Util;

public class ExportController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ExportService exportService;
	

	public ExportController(Scanner sc ) {
		members = Container.memberDao.members;
		exportService = Container.exportservice;
		this.sc = sc;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "html" : 
			dohtml();
			break;
		default :
			System.out.print("존재하지 않는 명령어입니다.\n");
		}
	}
	private void dohtml() {
		System.out.println("html 생성을 시작합니다. ");
		exportService.makeHtml();
	}

}