package com.springbook.biz;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserServiceClient {
	
	
	public static void getUsers(UserService user) {
		
	}

	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml"); // 싱글톤 패턴임
		UserService user = (UserService)container.getBean("userService");
		UserVO uVo = new UserVO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("아이디 입력: ");
		uVo.setId(sc.nextLine());
		System.out.println("비밀번호 입력: ");
		uVo.setPassword(sc.nextLine());
		
		UserVO result = user.getUser(uVo);
		
		if(result != null) System.out.println(result.getName() + "님 환영합니다.");
		else System.out.println("로그인 실패!");
		
		container.close();

	}

}
