package com.kosta.batis.dao;

import java.util.List;
import java.util.Map;

import com.kosta.batis.domain.BoardDTO;

public interface BoardDao {
	BoardDTO select(Integer bno) throws Exception;
	
	List<BoardDTO> selectPage(Map map) throws Exception;
	
	int count() throws Exception;
	
	int deleteAll() throws Exception;

	int insert(BoardDTO boardDTO) throws Exception;

	int delete(Integer bno, String writer) throws Exception;
	
	int deleteForAdmin(Integer bno) throws Exception;
	
	int update(BoardDTO boardDTO) throws Exception;

	int increaseViewCnt(Integer bno) throws Exception;
	
}
