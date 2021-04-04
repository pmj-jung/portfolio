package com.portfolio.academy.service;

import javax.servlet.http.HttpSession;

import com.portfolio.academy.model.UserVO;

public interface RegnlogSrv {
	public void setRegister(UserVO uvo);
	public int checkSameId(String userId);
	public UserVO loginCheck(UserVO uvo,HttpSession session);
	public void logout(HttpSession session);
	public String getPassword(UserVO uvo);
}
