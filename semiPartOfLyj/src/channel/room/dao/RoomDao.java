package channel.room.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db.SqlMapConfig;
import channel.room.dto.RoomDto;
import channel.room.dto.RoomMemberDto;

public class RoomDao extends SqlMapConfig{
    // 1. 채널 추가
	public int createRoom(RoomDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper.createRoom", dto);
		
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
	
	// 2. 채널 추가시 채널 추가한 계정을 채널참여자 리스트에 인서트
		public int roomMemberAdd(RoomMemberDto roomDto) {
			SqlSession session = null;
			int res = 0;
			try {
				session = getSqlSessionFactory().openSession(false);
				res = session.insert("channelmapper.roomMemberAdd", roomDto);
			
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
	
	// 2. 해당 아이디가 가지고 있는 채널 출력
	public List<RoomDao> channelList(String member_id) {
		SqlSession session = null;
		List<RoomDao> list = new ArrayList<RoomDao>();
		
		session = getSqlSessionFactory().openSession(false);
		list = session.selectList("channelmapper.channelList", member_id);
		
		return list;
	}
	
}
