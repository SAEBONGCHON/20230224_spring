package kh.spring.s03.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kh.spring.s03.board.model.dao.BoardDao;
import kh.spring.s03.board.model.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	
	
	@Override
	@Transactional
	public int insert(BoardVo vo) {
		int seqBoardNum = dao.getSeqBoardNum();
		if(vo.getBoardNum() != 0) {
			// 몇번글의 답글인지 번호가 있고, 원글이라면  0이다.
			dao.updateForRelpy(vo.getBoardNum());
		}
//		return dao.insert(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bvo", vo);
		map.put("seqBoardNum", seqBoardNum);
		
		
		dao.insert(map);
		return dao.insertFile(map);
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
		if(result!= null && !result.getBoardWriter().equals(writer)) {
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

	@Override
	public List<BoardVo> selectList(int currentpage, int limit, String searchWord) {
		// TODO Auto-generated method stub
		return dao.selectList(currentpage, limit, searchWord);
	}

	@Override
	public int selectOneCount(String searchWord) {
		return dao.selectOneCount(searchWord);
	}

	@Override
	public List<BoardVo> selectReplyList(int boardNum) {
		return dao.selectReplyList(boardNum);
	}

	@Override
	public List<BoardVo> selectReplyList(int boardNum, int currentpage, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
