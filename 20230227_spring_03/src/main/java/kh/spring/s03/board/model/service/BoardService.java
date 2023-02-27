package kh.spring.s03.board.model.service;

import kh.spring.s03.board.model.vo.BoardVo;

public interface BoardService {

	public int insert(BoardVo vo);
	public int update(BoardVo vo);
	public int updateReadCount(BoardVo vo);
	public int delete(BoardVo vo);
	public int update(BoardVo vo);
	public int update(BoardVo vo);
}
