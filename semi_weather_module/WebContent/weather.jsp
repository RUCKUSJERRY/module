<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

    <h2>current weather </h2>
    <h3 class="thistime">현재시간: </h3>
    <h3 class="ctemp">현재온도: </h3>
    <h3 class="lowtemp">최저온도: </h3>
    <h3 class="hightemp">최고온도: </h3>
    <h3 class="icon">
        <!-- <img src="http://openweathermap.org/img/wn/10d.png" alt=""> -->
    </h3>
    <h2>weather forecast 48hours </h2>

    <h3>
        <table>
            <thead>
                <tr>
                    <td>시간</td>
                    <td>온도</td>
                </tr>
            </thead>
            <tbody>
                <!-- <tr>
                <td>현재 시간</td>
                <td>현재 온도</td>
            </tr> -->
            </tbody>
        </table>

    </h3>



    <script src=""></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script>
        var latitude = "null";
        var longitude = "null";
        var cityname = "null";
        
        //현재 날씨
        $.getJSON(
            'http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=6a0d9f98d475b0998a05cf0b47a8569c&units=metric',
            function (
                result) {
                //alert(result.main.temp);
                $('.ctemp').append(result.main.temp);
                $('.lowtemp').append(result.main.temp_min);
                $('.hightemp').append(result.main.temp_max);
                //result.weather[0].icon
                var wiconUrl = '<img src="http://openweathermap.org/img/wn/' + result.weather[0].icon +
                    '.png" alt="' + result.weather[0].description + '">'
                $('.icon').html(wiconUrl);

                //10자리시간= Time of data calculation, unix, UTC 유닉스시간이기에 표준시간으로 변환필요
                var ct = result.dt; //웨더데이트는 제이슨에서 가져온 값이다.

                //컨벌트 타입 함수는 오늘날짜를 * 1000한 대한민국 표준시로 형식을 바꿔준다.
                function Unix_timestamp(t) {
                    var date = new Date(t * 1000);
                    //Tue Apr 06 2021 15:28:58 GMT+0900 (대한민국 표준시)
                    var year = date.getFullYear();
                    var month = "0" + (date.getUTCMonth() + 1);
                    var day = "0" + date.getDate();
                    var hour = "0" + date.getHours();
                    var minute = "0" + date.getMinutes();
                    var second = "0" + date.getSeconds();


                    return year + '/' + month.substr(-2) + '/' + day.substr(-2) + ' ' + hour.substr(-2) + ':' +
                        minute.substr(-2) + ':' + second.substr(-2);
                }
                var currentTime = Unix_timestamp(ct); //커런트타임은 컨버트타임으로 바꿔준다.파라미터는 웨더데이트
                $('.thistime').append(currentTime); //thistime을 convertTime으로 형변환한 weatherdate을 붙여준다.



            });

        //48시간 날씨 예보

        $.getJSON(
            'https://api.openweathermap.org/data/2.5/onecall?lat=37.5683&lon=126.9778&&appid=6a0d9f98d475b0998a05cf0b47a8569c&units=metric',
            function (
                result) {

                for (var i = 0; i < 48; i++) {
                    var ctime = result.hourly[i].dt;
                    var ctemp = result.hourly[i].temp;

                    function
                    Unix_timestamp(t) {
                        var date = new Date(t * 1000); //Tue Apr 06 2021 15:28:58 GMT+0900 (대한민국 표준시) var
                        year = date.getFullYear();
                        var month = "0" + (date.getUTCMonth() + 1);
                        var day = "0" + date.getDate();
                        var hour = "0" + date.getHours();
                        var minute = "0" + date.getMinutes();
                        var second = "0" + date.getSeconds();
                        return day.substr(-2) + '일 ' + hour.substr(-2) + '시  - ';
                    }
                    var currentTime = Unix_timestamp(ctime);
                    var tableHtml = '<tr>' + '<td>' +
                        currentTime + '</td>' + '<td>' + ctemp + '</td>' + '</tr>';
                    $('tbody').append(tableHtml);
                }
            });
    </script>

</body>

</html>