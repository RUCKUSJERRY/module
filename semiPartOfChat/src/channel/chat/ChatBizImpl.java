package channel.chat;

import java.util.List;

import channel.channel.MessageRoomDto;

public class ChatBizImpl implements ChatBiz {
	
	ChatDao dao = new ChatDaoImpl();
	
	@Override
	public List<ChatDto> selectChatList(int channel_num) {
		return dao.selectChatList(channel_num);
	}

	@Override
	public int insertChat(ChatDto dto) {
		return dao.insertChat(dto);
	}
	
	@Override
	public List<MessageRoomDto> messageRoomList(MessageRoomDto msgDto) {
		return dao.messageRoomList(msgDto);
	}
	
	@Override
	public List<MessageDto> callMessageList(int messageroom_seq) {
		return dao.callMessageList(messageroom_seq);
	}
	
	@Override
	public MessageRoomDto msgRoomSelect(int messageroom_seq) {
		return dao.msgRoomSelect(messageroom_seq);
	}
	
	@Override
	public int messageInsert(MessageDto dto) {
		return dao.messageInsert(dto);
	}
	

	@Override
	public int createMessageRoom(MessageRoomDto dto) {
		return dao.createMessageRoom(dto);
	}
	
}
