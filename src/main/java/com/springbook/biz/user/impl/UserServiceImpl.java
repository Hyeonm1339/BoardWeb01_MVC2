package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

import lombok.Setter;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Setter(onMethod=@__({@Autowired}))
//	@Autowired
	private UserDAO userDAO;

	@Override
	public UserVO getUser(UserVO vo) {
		// TODO Auto-generated method stub
		return userDAO.getUser(vo);
	}

}
