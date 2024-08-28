package com.kosta.batis.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.batis.domain.BoardDTO;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession session;
	private static String namespace="com.kosta.batis.dao.BoardMapper.";
	
	@Override
	public BoardDTO select(Integer bno) throws Exception {
		
		return session.selectOne(namespace+"select",bno);
		
	}

	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace+"deleteAll");
	}

	@Override
	public int insert(BoardDTO dto) throws Exception{
		return session.insert(namespace+"insert", dto);
		
	}
	@Override
	public int delete(Integer bno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		return session.delete(namespace+"delete",map);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		
		return session.update(namespace+"update", boardDTO);
	}

	@Override
	public int deleteForAdmin(Integer bno) throws Exception {
		return session.delete(namespace+"deleteForAdmin", bno);
	}

	@Override
	public int count() throws Exception {
		
		return session.selectOne(namespace+"count");
	}

	@Override
	public List<BoardDTO> selectPage(Map map) throws Exception {
		
		return session.selectList(namespace+"selectPage",map);
	}

	@Override
	public int increaseViewCnt(Integer bno) throws Exception {
		return session.update(namespace+"increaseViewCnt",bno);
	}

}
