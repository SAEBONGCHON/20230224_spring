package kh.spring.s03.member.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kh.spring.s03.member.model.vo.MemberVo;

@Service
public interface MemberService {

	public int insert(MemberVo vo) throws Exception;
	public int update(MemberVo vo)throws Exception;
	public int delete(MemberVo vo)throws Exception;
	public MemberVo selectOne(String id)throws Exception;
	public List<MemberVo> selectList()throws Exception;
}
