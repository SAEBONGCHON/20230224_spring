package kh.spring.s03.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s03.member.model.vo.MemberVo;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(MemberVo vo) {
		return sqlSession.insert("memberMapper.insertId", vo);
	}
	public int update(MemberVo vo) {
		return sqlSession.update("memberMapper.updateId", vo);
	}
	public int delete(MemberVo vo) {
		return sqlSession.delete("memberMapper.deleteId", vo);
	}
	public MemberVo selectOne(String id) {
		return sqlSession.selectOne("memberMapper.selectOneId", id);
	}
	public List<MemberVo> selectList() {
		return sqlSession.selectList("memberMapper.selectListId");
	}
}
