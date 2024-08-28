package com.kosta.batis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.batis.dao.BoardDao;
import com.kosta.batis.domain.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<BoardDTO> getPage(Map map) throws Exception {
		return boardDao.selectPage(map);
	}

	@Override
	public int getCount() throws Exception {
		return boardDao.count();
	}

	@Override
	public BoardDTO read(Integer bno) throws Exception{
		BoardDTO boardDTO=boardDao.select(bno);
		// 조회수 증가(비즈니스 로직처리)
		boardDao.increaseViewCnt(bno);
		return boardDTO;
	}

	@Override
	public int remove(Integer bno, String writer) throws Exception {
		return boardDao.delete(bno,writer);
	}
}
