document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			headerToolbar : {
				start : 'prevYear,prev,next,nextYear addEventButton',
				center : 'title',
				end : 'today dayGridMonth,timeGridWeek,timeGridDay'
			},
			height : 800,
			aspectRatio : 1.35,
			locale : 'ko',
			customButtons : {
				addEventButton : {
					text : '일정추가',
					click : function() {
						$('#addCalendarForm').modal();
					}
				}
			},
			eventClick : function(info) {
				var eventObj = info.event;

				if (eventObj.url) {
					location.href=eventObj.url;
					info.jsEvent.preventDefault();
				} else {
					alert('Clicked ' + eventObj.title);
				}
			}
		});
		calendar.render();
		
		calendar.addEvent({
			'title' : "공부하기",
			'start' : "2021-04-14T10:00:00",
			'end' : "2021-04-16T20:00:00"

		})
	});
