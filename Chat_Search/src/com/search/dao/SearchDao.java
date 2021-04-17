package com.search.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.search.db.SqlMapConfig;
import com.search.dto.SearchDto;

public class SearchDao extends SqlMapConfig {
	
	public List<SearchDto> chatList(){
		
		SqlSession session = null;
		
		List<SearchDto> list = new ArrayList<SearchDto>();
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("search-mapper.chatlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
		
		return list;
	}
	
	

}
