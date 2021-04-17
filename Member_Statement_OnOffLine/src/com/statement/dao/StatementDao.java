package com.statement.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.statement.db.SqlMapConfig;
import com.statement.dto.StatementDto;

public class StatementDao extends SqlMapConfig {
	
	public List<StatementDto> allCheck() {
		SqlSession session = null;
			
		List<StatementDto> list = new ArrayList<StatementDto>();
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("statement-mapper.allCheck");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return list;
	}
	public int updateStatement(StatementDto dto) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("statement-mapper.updateStatement", dto);
			if (res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
			
		return res;
	}
	

}
