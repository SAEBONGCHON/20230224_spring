package kh.spring.s03.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kh.spring.s03.board.model.vo.BoardVo;

public interface BoardService {
	public int insert(BoardVo vo);
	public int update(BoardVo vo);
//	public int updateForRelpy(int boardNum);
	public int delete(int boardNum /*BoardVo vo 또는 PK 또는 List<PK>*/);
	public BoardVo selectOne(int boardNum, String writer/*PK*/);
	public int selectOneCount();
	public int selectOneCount(String searchWord);
//	public int updateReadCount(int boardNum);
	
	public List<BoardVo> selectList();				  //전체읽기
	public List<BoardVo> selectList(int currentpage, int limit); //paging처리
	public List<BoardVo> selectList(int currentpage, int limit, String searchWord); //paging처리
	
	//글의 답글 전체 읽기과 답글 페이징 처리
	public List<BoardVo> selectReplyList(int boardNum); 
	public List<BoardVo> selectReplyList(int boardNum, int currentpage, int limit); 
	
}
