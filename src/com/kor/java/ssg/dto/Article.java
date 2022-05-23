package com.kor.java.ssg.dto;

public class Article extends Dto {

	public String title;
	public String body;
	public String time;

	public int hit;

	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		
	}
	
	public void increastHit() {
		hit++;
	}

}