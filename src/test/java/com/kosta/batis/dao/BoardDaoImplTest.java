package com.kosta.batis.dao;


import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kosta.batis.domain.BoardDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {

	@Autowired
	private BoardDao boardDao;
	
//	@Test
	public void selectTest() throws Exception{
		assertTrue(boardDao != null);
		System.out.println("boardDao = "+boardDao);
		
		BoardDTO boardDTO = boardDao.select(3);
		System.out.println("boardDTO = "+boardDTO);
		assertTrue(boardDTO.getBno().equals(3));
		
		boardDao.deleteAll();
		boardDTO = new BoardDTO("Pioneering","Ready for Action","kosta");
		boardDao.insert(boardDTO);
		
		boardDTO=boardDao.select(4);
		System.out.println("boardDto ="+boardDTO);
		assertTrue(boardDTO.getBno().equals(4));
		
		boardDao.delete(3,"kosta");
		
		boardDTO= new BoardDTO("Pioneering","update ","kosta");
		boardDao.update(boardDTO);
	}
	
//	@Test
	public void selectOne() throws Exception{
		boardDao.count();
	}
	
	@Test
	public void selectPageTest() throws Exception{
		boardDao.deleteAll();
		
		for(int i=1;i<=10; i++) {
			BoardDTO boardDTO=new BoardDTO("Pioneering"+i, "취업 준비 등등..", "kosta");
			boardDao.insert(boardDTO);
			
		}
		Map map = new HashMap();
		map.put("offset", 7);
		map.put("pageSize", 3);
		
		List<BoardDTO> list=boardDao.selectPage(map);
		assertTrue(list.get(0).getTitle().equals("Pioneering3"));
		assertTrue(list.get(1).getTitle().equals("Pioneering2"));
		assertTrue(list.get(2).getTitle().equals("Pioneering1"));
		
	}
}

