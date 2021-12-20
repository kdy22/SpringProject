package first.common.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import first.common.common.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject 
	SqlSession sql;
	// ȸ������

	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert("memberMapper.register", vo);
	}
	
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		
		return sql.selectOne("memberMapper.login", vo);
	}
	
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// vo�� ��� �Ķ���͵��� memberMapper.xml�� memberMapper��� namespace�� 
		// ���̵� memberUpdate�� ������ �Ķ���͵��� �־��ݴϴ�.
		sql.update("memberMapper.memberUpdate", vo); 
	}
	
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = sql.selectOne("memberMapper.idChk", vo);
		return result;
	}
	
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		// MemberVO�� ��� ������ �����ݴϴ�.
		// �׷� xml���� memberMapper.memberDelete�� ���ø�
		//  #{userId}, #{userPass}�� �Ķ���Ͱ��� ��Ī�� �ǰ�����.
		sql.delete("memberMapper.memberDelete", vo);
		
	}
	
}