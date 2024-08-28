package com.kosta.batis.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kosta.batis.domain.BoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest2 {
	@Autowired
	private BoardDao boardDao;

	@Test
	public void insertDummyDataTest() throws Exception{
		boardDao.deleteAll();
		
		for(int i=1; i<=250; i++) {
			BoardDTO boardDTO=new BoardDTO("Pioneering"+i,"취업준비..","kosta");
			boardDao.insert(boardDTO);
		}
	}

}
