package first.common.dao;

import first.common.common.MemberVO;

public interface MemberDAO {
	// ȸ������
	public void register(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	//���̵� �ߺ�üũ
	public int idChk(MemberVO vo) throws Exception;
	
	// ȸ������ ����
	public void memberUpdate(MemberVO vo) throws Exception;
	
	// ȸ�� Ż��
	public void memberDelete(MemberVO vo)throws Exception;
    
}