package com.kor.java.ssg.Container;

import com.kor.java.ssg.Dao.ArticleDao;
import com.kor.java.ssg.Dao.MemberDao;
import com.kor.java.ssg.Service.ArticleService;
import com.kor.java.ssg.Service.ExportService;
import com.kor.java.ssg.Service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleservice;
	public static MemberService memberservice;
	public static ExportService exportservice;
	
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleservice = new ArticleService();
		memberservice = new MemberService();
		exportservice = new ExportService();
	}

	
}
