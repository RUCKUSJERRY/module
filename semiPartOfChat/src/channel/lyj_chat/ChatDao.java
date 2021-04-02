package channel.lyj_chat;

import java.util.List;
import channel.lyj_room.MessageRoomDto;

public interface ChatDao {
	
	// 채팅 DB에서 불러오기
	public List<ChatDto> callChatList(int channel_num);	
	// 채팅 DB에 저장하기
	public int chatInsert(ChatDto dto);
	// 메세지 리스트 불러오기
	public List<MessageRoomDto> messageRoomList(MessageRoomDto msgDto);	
	// 메세지 DB에서 불러오기
	public List<MessageDto> callMessageList(int messageroom_seq);	
	// 메시지방 정보 불러오기
	public MessageRoomDto msgRoomSelect(int messageroom_seq);
	// 메세지 DB에 저장하기
	public int messageInsert(MessageDto dto);
	// 메세지 룸 생성
	public int createMessageRoom(MessageRoomDto dto);
	
	
}
