package com.project.mall.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mall.command.UserVO;

@Service("userService")
public class UserServiceImp1 implements UserService{

	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int join(UserVO vo) { //회원가입
		
		return userMapper.join(vo);
	}

	@Override
	public UserVO login(String userId, String userPw, String userGrade) { //로그인 기능
		
		return userMapper.login(userId, userPw, userGrade);
	}

	@Override
	public int idCheck(String userId) { //아이디 중복확인
		
		return userMapper.idCheck(userId);
	}

	@Override
	public String idFind(String userName, String userPhone) { //이름 폰번호로 아이디찾기
		
		return userMapper.idFind(userName, userPhone);
	}

	@Override
	public String pwFind(String userId, String userName, String userPhone) { //비밀번호 찾기
		
		return userMapper.pwFind(userId, userName, userPhone);
	}

	@Override
	public int updatePw(String userId, String userPw) { //비밀번호 변경
		
		return userMapper.updatePw(userId, userPw);
	}

	@Override
	public int deleteUser(String userId) { //회원 탈퇴
		return userMapper.deleteUser(userId);
	}

	@Override
	public int updateInfo(String userId, String userPw, String userPhone, String userAddress) { //회원정보 변경
		
		return userMapper.updateInfo(userId, userPw, userPhone, userAddress);
	}

	@Override
	public int passwordCheck(String userId, String userPw) { //기존 비밀번호와 동일한지 확인
		return userMapper.passwordCheck(userId, userPw);
	} 



	

	

	
}
