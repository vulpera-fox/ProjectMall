package com.project.mall.user.service;

import com.project.mall.command.UserVO;

public interface UserService {
	public int join(UserVO vo); //회원가입
	public UserVO login(String userId, String userPw, String userGrade); //로그인
	public int idCheck(String userId); //아이디 중복확인
	public String idFind(String userName, String userPhone); //아이디 찾기
	public String pwFind(String userId,String userName, String userPhone);//비밀번호 찾기
	public int updatePw(String userId,String userPw); //비밀번호 재설정
	public int deleteUser(String userId); //회원탈퇴
	public int updateInfo(String userId, String userPw, String userPhone, String userAddress); //회원정보 변경
	public int passwordCheck(String userId, String userPw); //기존 비밀번호 인지 확인
}
