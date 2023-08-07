package com.project.mall.user.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.mall.command.UserVO;

@Mapper
public interface UserMapper {
	public int join(UserVO vo); //회원가입
	public UserVO login(@Param("userId") String userId, @Param("userPw") String userPw, @Param("userGrade") String userGrade); //로그인
	public int idCheck(String userId); //아이디 중복확인
	public String idFind(@Param("userName") String userName,@Param("userPhone") String userPhone);
	public String pwFind(@Param("userId") String userId,@Param("userName") String userName,@Param("userPhone") String userPhone);
	public int updatePw(@Param("userId") String userId,@Param("userPw") String userPw); //비밀번호 재설정
	public int deleteUser(String userId); //회원탈퇴
	public int updateInfo(@Param("userId") String userId,@Param("userPw") String userPw,@Param("userPhone") String userPhone,@Param("userAddress") String userAddress); //회원정보 변경
	public int passwordCheck(@Param("userId") String userId,@Param("userPw") String userPw); //기존 비밀번호 인지 확인
}
