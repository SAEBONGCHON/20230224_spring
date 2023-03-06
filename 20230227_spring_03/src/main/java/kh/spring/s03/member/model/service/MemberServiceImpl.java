package kh.spring.s03.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s03.member.model.dao.MemberDao;
import kh.spring.s03.member.model.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;

	@Override
	public int insert(MemberVo vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int update(MemberVo vo)throws Exception {
		return dao.update(vo);
	}

	@Override
	public int delete(MemberVo vo)throws Exception {
		return dao.delete(vo);
	}

	@Override
	public MemberVo selectOne(String id)throws Exception {
		return null;
	}

	@Override
	public List<MemberVo> selectList()throws Exception {
		return null;
	}
	
}
