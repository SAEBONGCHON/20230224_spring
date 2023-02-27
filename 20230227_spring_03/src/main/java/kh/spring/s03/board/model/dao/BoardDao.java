package kh.spring.s03.board.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import kh.spring.s03.board.model.vo.BoardVo;

public class BoardDao {
	
	@Autowired
	//root-context의 sqlSession임
	private SqlSession sqlSession;
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("boardns.insertid", vo);
	}
//	public int update(BoardVo vo) {
//		return sqlSession.update("boardns.updateid", vo);
//	}
//	public int updateReadCount(BoardVo vo) {
//		return sqlSession.updateReadCount("boardns.updateReadCount", vo);
//	}
//	public int delete(BoardVo vo) {
//		return sqlSession.delete("boardns.deleteid", vo);
//	}
//	public int update(BoardVo vo) {
//		return sqlSession.update("boardns.updateid", vo);
//	}
//	public int update(BoardVo vo) {
//		return sqlSession.update("boardns.updateid", vo);
//	}

	
}
