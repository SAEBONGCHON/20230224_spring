package kh.spring.s02.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kh.spring.s02.board.model.vo.BoardVo;

public interface BoardService {
	public int insert(BoardVo vo);
	public int update(BoardVo vo);
//	public int updateForRelpy(int boardNum);
	public int delete(int boardNum /*BoardVo vo 또는 PK 또는 List<PK>*/);
	public BoardVo selectOne(int boardNum, String writer/*PK*/);
//	public int updateReadCount(int boardNum);
	public List<BoardVo> selectList();				  //전체읽기
	public List<BoardVo> selectList(int currentpage,int limit); //paging처리
	public List<BoardVo> selectList(int currentpage,int limit, String searchWord); //paging처리
	public int selectOneCount();
	public int selectOneCount(String searchWord);
}
