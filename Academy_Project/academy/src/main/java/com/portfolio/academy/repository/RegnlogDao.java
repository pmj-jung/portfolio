package com.portfolio.academy.repository;

import com.portfolio.academy.model.UserVO;

public interface RegnlogDao {
	
	public void setRegister(UserVO uvo);
	public int checkSameId(String userId);
	public UserVO loginCheck(UserVO uvo);
	public String getPassword(UserVO uvo);
}
