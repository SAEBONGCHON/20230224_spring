package kh.spring.s02.board.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s02.board.model.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("boardns.insertid", vo);
	}
	public int update(BoardVo vo) {
		return sqlSession.update("boardns.updateid", vo);
	}
	public int updateReadCount(int boardNum) {
		return sqlSession.update("boardns.updateReadCount", boardNum);
	}
	public int updateForRelpy(int boardNum) {
		return sqlSession.update("boardns.updateForRelpy", boardNum);
	}
	public int delete(int boardNum /*BoardVo vo 또는 PK 또는 List<PK>*/) {
		return sqlSession.delete("boardns.deleteid", boardNum);
	}
	public BoardVo selectOne(int boardNum/*PK*/) {
		return sqlSession.selectOne("boardns.selectOneid", boardNum);
	}
	public List<BoardVo> selectList(int currentpage) {
		return sqlSession.selectList("boardns.selectListid");
	}
	//아래와같은 코드, 보통 DAO에서 컨트롤러를 불러오는 것은 하지 않는다. 연관관계가 형성됨
	// 그래서 public -> private로 변경하여 int limit로 가져오는것을 선호한다.
	public List<BoardVo> selectList(int currentPage, int limit) {
//		int offset =  (currentPage-1)*limit;		
//		RowBounds rb = new RowBounds(offset, limit);
//		return sqlSession.selectList("boardns.selectListid", null, rb);		
		return sqlSession.selectList("boardns.selectListid", null, new RowBounds((currentPage-1)*limit,limit));		
	}
	public int selectOneCount() {
		return sqlSession.selectOne("boardns.selectOneCount");
	}
	
	public List<HashMap<String, Object>> tempSelect() {
		List<HashMap<String, Object>> listmap = sqlSession.selectList("board_num");
		for(HashMap<String, Object> map : listmap) {
			System.out.println((String)map.get("boardDate"));
		}
		// property = key = attribute = column = field
		return listmap;
	}

	
//	public List<BoardVo> selectList2() {
//		return sqlSession.selectList("boardns.selectListid2");
//	}
}



