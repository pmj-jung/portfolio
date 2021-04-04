package com.portfolio.academy.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.academy.model.UserVO;
import com.portfolio.academy.repository.RegnlogDao;

@Service
public class RegnlogSrvImpl implements RegnlogSrv {
	
	@Autowired
	RegnlogDao regnlogDao;
	
	@Override
	public int checkSameId(String userId) {
		return regnlogDao.checkSameId(userId);
	}

	@Override
	public void setRegister(UserVO uvo) {
		regnlogDao.setRegister(uvo);
	}

	@Override
	public UserVO loginCheck(UserVO uvo, HttpSession session) {
		UserVO result = regnlogDao.loginCheck(uvo);
		if( result != null ) {
			session.setAttribute("uid", result.getUid());
			session.setAttribute("userName", result.getUserName());
			session.setAttribute("userGender", result.getUserGender());
			session.setAttribute("userPhone", result.getUserPhone());
			session.setAttribute("userEmail", result.getUserEmail());
			session.setAttribute("userRegdate", result.getUserRegdate());
			session.setAttribute("userAuth", result.getUserAuth());
			session.setAttribute("authName", result.getAuthName());
		}
		
		return result;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public String getPassword(UserVO uvo) {
		return regnlogDao.getPassword(uvo);
	}

}
