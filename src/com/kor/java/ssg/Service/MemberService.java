package com.kor.java.ssg.Service;

import com.kor.java.ssg.Container.Container;
import com.kor.java.ssg.Dao.MemberDao;
import com.kor.java.ssg.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService () {
		memberDao = Container.memberDao;
	}
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
	public int getMemberIndexByLoginId(String loginId) {
		return memberDao.getMemberIndexByLoginId(loginId);
	}
	public void signUp(Member member) {
		memberDao.add(member);
	}
	public String getMemberNameByMemberId(int id) {
		return memberDao.getMembernameByMemberId(id);
	}
	
}
