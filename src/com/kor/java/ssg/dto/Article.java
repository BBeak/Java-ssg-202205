package com.kor.java.ssg.dto;

public class Article extends Dto {

	public String title;
	public String body;
	public String time;
	public String userId;
	public int hit;

	public Article(int id, String title, String body, String userId, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.regDate = regDate;
		
	}
	
	public void increastHit() {
		hit++;
	}

}