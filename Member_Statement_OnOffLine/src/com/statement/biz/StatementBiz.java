package com.statement.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.statement.dao.StatementDao;
import com.statement.dto.StatementDto;

public class StatementBiz {
	
	StatementDao dao = new StatementDao();

	public StatementDto login(String member_id, String member_pw) {
		List<StatementDto> lists = dao.allCheck();
		StatementDto dto = null;
		for(StatementDto list : lists) {
			if(list.getMember_id().equals(member_id) && list.getMember_pw().equals(member_pw)) {
				dto = new StatementDto(list.getMember_num(),
									   list.getMember_id(),
									   list.getMember_pw(),
									   list.getMember_name(),
									   list.getMember_email(),
									   list.getMember_phone(),
									   list.getMember_pscode(),
									   list.getMember_addr(),
									   list.getMember_addrdt(),
									   list.getMember_type(),
									   list.getMember_auth(),
									   list.getMember_date(),
									   list.getMember_enabled(),
									   list.getMember_statement());
			}
		}		
		return dto;	
	}
	
	public List<StatementDto> allList(){
		return dao.allCheck();
	}
	
	public int updateStatement(StatementDto dto) {
		return dao.updateStatement(dto);
	}
	
	public String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		
		return result.toString();
	}
	
}
