package com.project.mall.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	@NotEmpty(message = "아이디는 필수입니다.")
	private String userId;
	@NotEmpty(message = "비밀번호는 필수입니다.")
	private String userPw;
	@NotEmpty(message = "이름을 입력하세요")
	private String userName;
	@Pattern(message = "-빼고 11자리 입력해주세요", regexp = "[0-9]{11}")
	private String userPhone;
	@NotEmpty(message = "주소를 입력해주세요")
	private String userAddress;
	private String userGrade;
}
