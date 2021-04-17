package com.search.biz;

import java.util.ArrayList;
import java.util.List;

import com.search.dao.SearchDao;
import com.search.dto.SearchDto;

public class SearchBiz {
	
	SearchDao dao = new SearchDao();
	
	public List<SearchDto> chatList(String content){
		
		List<SearchDto> list = dao.chatList();
		List<SearchDto> searchlist = new ArrayList<SearchDto>();
		
		for(SearchDto dto : list) {
			if(dto.getChat_content().contains(content)) {
				searchlist.add(dto);
			}			
		}
				
		return searchlist;
	}

}
