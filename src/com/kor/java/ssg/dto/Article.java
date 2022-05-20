package com.kor.java.ssg.dto;

public class Article {
	public int id;
	public String title;
	public String body;
	public String time;
	public int hit;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		
	}
	
	public void increastHit() {
		hit++;
	}

}