package first.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.lang.StringUtils;





public class AbstractDAO { 
	protected Log log = LogFactory.getLog(AbstractDAO.class); 
	
	@Autowired 
	private SqlSessionTemplate sqlSession; 
	
	protected void printQueryId(String queryId) { 
		
		if(log.isDebugEnabled()){ 
			
			log.debug("\t QueryId \t: " + queryId); } 
		} 
	
	public Object insert(String queryId, Object params){ 
		printQueryId(queryId); 
		return sqlSession.insert(queryId, params); 
		} 
	
	public Object update(String queryId, Object params){ 
		printQueryId(queryId); 
		return sqlSession.update(queryId, params); 
		} 
	
	public Object delete(String queryId, Object params){ 
		printQueryId(queryId); 
		return sqlSession.delete(queryId, params); 
		} 
	
	public Object selectOne(String queryId){ 
		printQueryId(queryId); 
		return sqlSession.selectOne(queryId); 
		} 
	
	public Object selectOne(String queryId, Object params){ 
		printQueryId(queryId); 
		return sqlSession.selectOne(queryId, params); 
		} 
	
	@SuppressWarnings("rawtypes") 
	public List selectList(String queryId){ 
		printQueryId(queryId); 
		return sqlSession.selectList(queryId); 
		} 
	
	@SuppressWarnings("rawtypes") 
	public List selectList(String queryId, Object params){ 
		printQueryId(queryId); 
		return sqlSession.selectList(queryId,params); 
		} 
	
	
	@SuppressWarnings("unchecked")
	public Object selectPagingList(String queryId, Object params){
		printQueryId(queryId);
		System.out.println(params);
		Map<String,Object> map = (Map<String,Object>)params;
		
		String strPageIndex = (String)map.get("PAGE_INDEX");
		String strPageRow = (String)map.get("PAGE_ROW");
		int nPageIndex = 0;
		int nPageRow = 20;
		
		if(StringUtils.isEmpty(strPageIndex) == false){
			nPageIndex = Integer.parseInt(strPageIndex)-1;
		}
		if(StringUtils.isEmpty(strPageRow) == false){
			nPageRow = Integer.parseInt(strPageRow);
		}
		map.put("START", (nPageIndex * nPageRow) + 1);
		map.put("END", 20);

		System.out.println("map 확인이요 : " + map);
		List<Map<String,Object>> list2 = sqlSession.selectList(queryId, map);
		System.out.println("list값은 무엇일까요?" + list2);
		
		return sqlSession.selectList(queryId, map);
	}

	
	
}
