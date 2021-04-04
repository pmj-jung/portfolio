package com.portfolio.academy.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portfolio.academy.model.UserVO;

@Repository
public class RegnlogDaoImpl implements RegnlogDao{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public int checkSameId(String userId) {
		// 0=동일한 아이디 없음, 1=동일한 아이디 존재함
		return sqlSession.selectOne("regnlog.checkSameId", userId);
	}

	@Override
	public void setRegister(UserVO uvo) {
		sqlSession.insert("regnlog.setRegister", uvo);
	}

	@Override
	public UserVO loginCheck(UserVO uvo) {
		return sqlSession.selectOne("regnlog.loginCheck", uvo);
	}

	@Override
	public String getPassword(UserVO uvo) {
		return sqlSession.selectOne("regnlog.getPassword", uvo);
	}

}
