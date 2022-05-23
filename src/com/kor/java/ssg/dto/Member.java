package com.kor.java.ssg.dto;

public class Member extends Dto {
	
		
		public String loginId;
		public String loginPw;
		
		

		public Member(int id, String loginId, String loginPw, String regDate) {
			this.id = id;
			this.loginId = loginId;
			this.loginPw = loginPw;
			this.regDate = regDate;
			
		}
		
		

	}

