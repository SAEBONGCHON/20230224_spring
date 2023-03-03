package kh.spring.s03.member.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kh.spring.s03.member.model.vo.MemberVo;

@Service
public class MemberService {

	public int insert(MemberVo vo);
	public int update(MemberVo vo);
	public int delete(MemberVo vo);
	public MemberVo selectOne(String id);
	public List<MemberVo> selectList();
}
