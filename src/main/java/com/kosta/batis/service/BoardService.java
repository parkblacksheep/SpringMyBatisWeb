package com.kosta.batis.service;

import java.util.List;
import java.util.Map;

import com.kosta.batis.domain.BoardDTO;

public interface BoardService {
	
	List<BoardDTO> getPage(Map map) throws Exception;
	int getCount() throws Exception;
	
	BoardDTO read(Integer bno) throws Exception;
	int remove(Integer bno, String writer) throws Exception;
	
	
}
