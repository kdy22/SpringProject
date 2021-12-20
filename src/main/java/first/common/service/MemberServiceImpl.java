package first.common.service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import first.common.common.MemberVO;
import first.common.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);
		
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//Controller���� ������ �Ķ���͵��� memberUpdate(MemberVO vo)�� �ް�
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		
		//���� vo�� DAO�� �����ݴϴ�.
		dao.memberUpdate(vo);
		
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
	
}