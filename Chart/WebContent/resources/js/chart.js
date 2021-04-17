function callChart() {
          	
			var money2021 = parseInt($("#2021").val());
			var money2022 = parseInt($("#2022").val());
			var money2023 = parseInt($("#2023").val());
	
			console.log(typeof money2021);
			
            google.charts.load('current', {packages: ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = new google.visualization.DataTable();
                // 컬럼 추가 하는 함수
                data.addColumn('string', '연도');
                data.addColumn('number', '금액');

                // 로우 데이터 추가하는 함수
                var row = ['2021년', money2021];
                data.addRow(row);
                var row = ['2022년', money2022];
                data.addRow(row);
                var row = ['2023년', money2023];
                data.addRow(row);
                
                var options = {
                curveType: 'function',
                is3D: true,
                hAxis: {
                 title: '연도별 재산 목표',
                 logScale: true,
                 titleTextStyle: {
                        color: 'black',
                        fontSize: 14,
                        bold: true,
                        italic: false
                     },
                     textStyle : {
                     fontSize: 10,
                     bold: true
                     }
                  },
                colors: ['#d97053', '#eed443'],
                legend: {
                    textStyle: {
                     bold: true,
                    color: 'black',
                    fontSize: 14
                        },
                    }
                
                 };

                var chart = new google.visualization.LineChart(document.getElementById('chart'));
                chart.draw(data, options);

            }       

    
}
