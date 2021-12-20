package first.sample.dao;

import org.springframework.stereotype.Repository;

import java.util.List; 
import java.util.Map;

import first.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{

	@SuppressWarnings("unchecked") 
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{ 
		log.debug("인터셉터 테스트 3");
		System.out.println("checking :"+map);
		return (List<Map<String, Object>>)selectPagingList("sample.selectBoardList", map);

		}
	
	public void insertBoard(Map<String, Object> map) throws Exception{ 
		insert("sample.insertBoard", map); 
		
	}
	
	public void updateHitCnt(Map<String, Object> map) throws Exception{ 
		update("sample.updateHitCnt", map); 
		} 
	
	public void updateHitCnt(Object object) throws Exception{ 
		update("sample.updateHitCnt", object); 
		} 
	
	@SuppressWarnings("unchecked") 
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{ 
		log.debug("인터셉터 테스트 777");
		System.out.println("check333 :"+map);
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map); 
	}
	
	public void updateBoard(Map<String, Object> map) throws Exception{ 
		update("sample.updateBoard", map); 
		
	}
	
	public void deleteBoard(Map<String, Object> map) throws Exception{ 
		update("sample.deleteBoard", map); 
		
	}
	
	public void insertFile(Map<String, Object> map) throws Exception{ 
		insert("sample.insertFile", map); 
	}
	
	@SuppressWarnings("unchecked") 
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("sample.selectFileList", map); 	
	}

	public void deleteFileList(Map<String, Object> map) throws Exception{ 
		update("sample.deleteFileList", map); 
	}

	public void updateFile(Map<String, Object> map) throws Exception{ 
		update("sample.updateFile", map); 
	}

}
