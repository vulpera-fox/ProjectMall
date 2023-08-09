package com.project.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.mall.command.UserVO;
import com.project.mall.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	//로그인페이지
	@GetMapping("/login")
	public String login() {
		
		return "user/login";
	}
	
	//로그인 하고 mypage로 이동
	@PostMapping("/loginForm")
	public String loginForm(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,@RequestParam("userGrade") String userGrade , RedirectAttributes ra, Model model,/* HttpSession session*/ HttpServletRequest httpServletSession) {
		//아이디 비번 등급 정보로 login 실행
		UserVO userVO =userService.login(userId, userPw, userGrade);
		try {	
			if(userVO.getUserId() == null) {
			}
		} catch (Exception e) {
			ra.addFlashAttribute("msg", "아이디와 비밀번호를 확인하세요");
			return "redirect:/user/login";
		}
		httpServletSession.getSession().invalidate();
		HttpSession session = httpServletSession.getSession(true);
		
		session.setAttribute("userVO",userVO);	
		session.setMaxInactiveInterval(1800);

		//등급 정보가져와서 관리자면 관리자페이지 이동 추후에 다시 경로 설정  
		if(userVO.getUserGrade().equals("관리자")) {		
			return "user/mypage";
		}else {	
//			return "redirect:/user/mypage";
			return "redirect:/product/productpage";
		}
	}
	//마이페이지
	@GetMapping("/mypage")
	public String mypage() {
	    

	    return "user/mypage";
	}
	//정보수정 페이지
	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}
	
	@PostMapping("/modifyForm")
	public String modifyForm(UserVO vo, HttpSession session) {
		UserVO userVO = (UserVO)session.getAttribute("vo");
		if (userVO != null) {
		    System.out.println(userVO.toString());
		} else {
		    System.out.println("userVO is null");
		}

		return "redirect:/user/modify";
	}
	//정보수정하고 마이페이지로 이동
	@PostMapping("/changeInfo")
	public String changeInfo(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw,@RequestParam("userPhone") String userPhone,@RequestParam("userAddress") String userAddress ,RedirectAttributes ra ) {
		int result = userService.updateInfo(userId, userPw, userPhone, userAddress);
		
		if(result == 1 ) {
			ra.addFlashAttribute("msg","회원 정보가 변경되었습니다.");
			return "redirect:/user/mypage";
		}else {
			
			return "redirect:/user/mypage";
		}
	}
	

	//회원가입페이지
	@GetMapping("/join")
	public String join() {
		
		return "user/join";
	}
	
	//회원가입하고 성공하면 이동
	@PostMapping("/joinForm")
	public String joinForm(@Valid @ModelAttribute("vo") UserVO vo, Errors errors, Model model) {
		if(errors.hasErrors()) { //에러가 있다면 true 없다면 false
			//1.유효성 검사에 실패한 에러 확인
			List<FieldError> list = errors.getFieldErrors();
			System.out.println(list.toString());
			//2.반복처리
			for(FieldError err : list) {			
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "입력 형식에 맞추세요");
				}else {					
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			return "user/join";
		}
		int result =userService.join(vo);
		//로그인 실패 시
		if(result != 1) {
			return "user/join";
		}
		
		
		return "redirect:/user/login";
	}
	

	//아이디 중복체크기능
	@PostMapping("/idCheck")
    public ResponseEntity<Map<String, Integer>> idCheck(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        int count = userService.idCheck(userId);
        Map<String, Integer> response = new HashMap<>();
        response.put("check", count);
        return ResponseEntity.ok(response);
    }
	//기존비밀번호와 비교
	@PostMapping("/passwordCheck")
    public ResponseEntity<Map<String, Integer>> passwordCheck(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String userPw = request.get("userPw");
        int count = userService.passwordCheck(userId, userPw);
        Map<String, Integer> response = new HashMap<>();
        response.put("check", count);
        return ResponseEntity.ok(response);
    }
	
	
	//아이디 찾기페이지 
	@GetMapping("/findid")
	public String findid() {
		
		return "user/findid";
	}
	

	//아이디찾기 성공
	@GetMapping("/findid_ok")
	public String findid_ok() {
		return "user/findid_ok";
	}
	
	//이름과 번호를 이용해서 아이디찾기
	@PostMapping("/findIdForm")
	public String findIdForm(@RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone,Model model,RedirectAttributes ra) {
		String userId = userService.idFind(userName, userPhone);
		try {
			if(userId.equals(" ")) {
			}
		} catch (Exception e) {
			
			ra.addFlashAttribute("msg","등록된 정보가 없습니다.");
			return "redirect:/user/findid";
		}
		
		model.addAttribute("userId",userId );
		model.addAttribute("userName",userName);
		return "user/findid_ok";
	}
	//비밀번호 찾기 페이지
	@GetMapping("/findpw")
	public String findpw() {
		
		return "user/findpw";
	}
	@GetMapping("/findpw_ok")
	public String findpw_ok(Model model, @ModelAttribute("userId") String userId, @ModelAttribute("userPw") String userPw, @ModelAttribute("userName") String userName) {
		return "user/findpw_ok";
	}
	//비밀번호 찾기
	@PostMapping("/findPwForm")
	public String findPwForm(@RequestParam("userId") String userId, @RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone,Model model, RedirectAttributes ra) {
	    String userPw = userService.pwFind(userId, userName, userPhone);
	    System.out.println(userPw);
	    try {
	        if(userPw.equals(" ")) {
	        }
	    } catch (Exception e) {
	        ra.addFlashAttribute("msg","등록된 정보가 없습니다.");
	        return "redirect:/user/findpw";
	    }
	    
	    ra.addFlashAttribute("userId",userId);
	    ra.addFlashAttribute("userPw",userPw );
	    ra.addFlashAttribute("userName",userName);
	    System.out.println(userId);
	    System.out.println(userPhone);
	    System.out.println(userName);
	    return "redirect:/user/findpw_ok";
	}
	//비밀번호 재설정
	@PostMapping("/changePwForm")
	public String changePwForm(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, RedirectAttributes ra) {
		int result = userService.updatePw(userId, userPw);
		if(result == 1 ) {
			ra.addFlashAttribute("msg", "비밀번호가 변경되었습니다.");
			return "redirect:/user/login";
		}else {
			ra.addFlashAttribute("msg","비밀번호 변경 실패!!!!!!!!!!");
			return "redirect:/user/findpw_ok";
		}
	}
	//로그아웃하고 세션삭제
	@PostMapping("/logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("vo");
	    session.invalidate();
	    return "redirect/user/login";
	}
	
	//회원탈퇴
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("userId") String userId, RedirectAttributes ra, HttpSession session) {
		int result = userService.deleteUser(userId);
		if(result == 1) {
			ra.addFlashAttribute("msg","회원탈퇴 되었습니다.");
		    session.invalidate();
			return "redirect:/user/login";
		}
		return "user/login";
	}
}
