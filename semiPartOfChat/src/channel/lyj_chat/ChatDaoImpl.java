package channel.lyj_chat;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;
import channel.member.dto.MemberDto;

public class ChatDaoImpl extends SqlMapConfig implements ChatDao {

	@Override
	public List<ChatDto> callChatList(int channel_num) {
		SqlSession session = null;
		List<ChatDto> list = new ArrayList<ChatDto>();

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-chat.callChatList", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public int chatInsert(ChatDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-chat.chatInsert", dto);
			
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
	
	@Override
	public List<MessageRoomDto> messageRoomList(MessageRoomDto msgDto) {
		
		List<MessageRoomDto> list = new ArrayList<MessageRoomDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-chat.messageRoomList", msgDto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
		
	}
	
	@Override
	public List<MessageDto> callMessageList(int messageroom_seq) {
		List<MessageDto> list = new ArrayList<MessageDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-chat.callMessageList", messageroom_seq);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 
	}
	
	@Override
	public MessageRoomDto msgRoomSelect(int messageroom_seq) {
		
		SqlSession session = null;
		MessageRoomDto dto = new MessageRoomDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper-chat.msgRoomSelect", messageroom_seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}
	
	@Override
	public int messageInsert(MessageDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-chat.messageInsert", dto);
			
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
