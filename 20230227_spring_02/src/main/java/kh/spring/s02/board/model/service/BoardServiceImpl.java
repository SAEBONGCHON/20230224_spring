package kh.spring.s02.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s02.board.model.dao.BoardDao;
import kh.spring.s02.board.model.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	
	
	@Override
	public int insert(BoardVo vo) {
		if(vo.getBoardNum() != 0) {
			// 몇번글의 답글인지 번호가 있고, 원글이라면  0이다.
			dao.updateForRelpy(vo.getBoardNum());
		}
		return dao.insert(vo);
	}

	@Override
	public int update(BoardVo vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(int boardNum) {
		return dao.delete(boardNum);
	}

	@Override
	public BoardVo selectOne(int boardNum, String writer) {
		BoardVo result = dao.selectOne(boardNum);
		if(!result.getBoardWriter().equals(writer)) {
			dao.updateReadCount(boardNum);
		}
		return result;
//		if(dao.updateReadCount(boardNum)>0) {
//			return dao.selectOne(boardNum);			
//		}else {
//			return null;
//		}
	}

	@Override
	public List<BoardVo> selectList() {
		return dao.selectList();
	}

	@Override
	public int selectOneCount() {
		return dao.selectOneCount();
	}

	@Override
	public List<BoardVo> selectList(int currentpage,int limit) {
		return dao.selectList(currentpage);
	}

}
