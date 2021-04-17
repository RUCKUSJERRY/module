package channel.alarm;

import java.util.List;

import channel.chat.ChatDto;

public interface AlarmDao {
	
	public int selectAlarm(int num);

	// 알람 갯수 출력
	public List<ChatDto> chatAlarm(int member_num);
	
	// 알람 리스트 출력
	public List<ChatDto> chatAlarmList(int member_num);
}
