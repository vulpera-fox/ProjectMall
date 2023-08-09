package com.project.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.mall.util.interceptor.UserAuthHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public UserAuthHandler userAuthHandler() {
		
		return new UserAuthHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userAuthHandler())
		.addPathPatterns("/user/**")
		.excludePathPatterns("/user/login")
		.excludePathPatterns("/user/loginForm")
		.excludePathPatterns("/user/join")
		.excludePathPatterns("/user/joinForm")
		.excludePathPatterns("/user/idCheck")
		.excludePathPatterns("/user/findid")
		.excludePathPatterns("/user/findid_ok")
		.excludePathPatterns("/user/findIdForm")
		.excludePathPatterns("/user/findpw")
		.excludePathPatterns("/user/findpw_ok")
		.excludePathPatterns("/user/findPwForm")
		.excludePathPatterns("/user/changePwForm")
		.excludePathPatterns("/product/productpage")
		.addPathPatterns("/admin/**");
		
	}	
}
