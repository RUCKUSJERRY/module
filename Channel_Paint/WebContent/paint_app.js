// paint.html 
// 캔버스 (html 요소)  ctx = context = 우리가 픽셀 안에 접근할 수 있는 방식 (접근해서 선도 그리고 도형도 그림)
// https://developer.mozilla.org/ko/docs/Web/API/Canvas_API
const canvas = document.getElementById("jsCanvas");
// 2d로 만들겠다. (3d도 가능)
const ctx = canvas.getContext("2d");
// (default 설정) 색
const colors = document.getElementsByClassName("jsColor");
// 펜 굵기
const range = document.getElementById("jsRange");
// 그리기 or 채우기
const mode = document.getElementById("jsMode");
// 저장
const saveBtn = document.getElementById("jsSave");

// #2c2c2c = 검정색
const INITIAL_COLOR = "#2c2c2c";
const CANVAS_SIZE = 700;

// offsetWidth 요소의 실제 사이즈 (패딩 보더 포함)
canvas.width = document.getElementsByClassName("canvas")[0].offsetWidth;
canvas.height = document.getElementsByClassName("canvas")[0].offsetHeight;

// 그림판 투명배경말고 하얀 배경 (이거 안해주면 이미지 저장할 때 투명배경으로 저장됨)
ctx.fillStyle = "white";
// 사각형 없음 숫자 쓰면 사각형 생김
ctx.fillRect(0, 0, canvas.width, canvas.height);
// 초기값 = INITIAL_COLOR (#2c2c2c)
ctx.strokeStyle = INITIAL_COLOR;
ctx.fillStyle = INITIAL_COLOR;
// 선(펜) 굵기 
ctx.lineWidth = 2.5;
// ctx.fillStyle = "green";
// ctx.fillRect(50, 20, 100, 49);

// 아무때나 그려지면 안되니까
let painting = false;	// 그리기
let filling = false;	// 채우기

// 페인팅 멈춰!
function stopPainting(){
	painting = false;
}

function startPainting(){
	// false 였던걸 true로 바꿔줌 
	painting = true;
	// canvas.addEventListener("click", handleCanvasClick); 이것때문에 오류남
	// 오류잡기
	if (filling){
	ctx.fillStyle
	ctx.fillRect(0, 0, canvas.width, canvas.height);
}

}

// 움직임을 감지하고 선을 그려줌 (path = 기본적인 선 -> 선의 시작점)
function onMouseMove(event){
	const x = event.offsetX;
	const y = event.offsetY;
	// 해당 영역 내에서의 좌표값 : offsetX, Y
    // 전체 window 내에서의 좌표값 : clientX, Y
	if(!painting){
		//console.log("creating path in", x, y);
		ctx.beginPath();
		ctx.moveTo(x,y);
		// 움직이면서 path(선의 시작점) 만들기
	} else {
		//console.log("creating line in", x, y);
		ctx.lineTo(x,y);
		ctx.stroke(); 	// stroke style = 검정색
		// 그리기 시작하면(클릭해서 움직임) 선을 그려준다.
		//ctx.closePath();	선이 끝남. 닫아버리면 시작점은 오직 하나 
	}
}

/*
function onMouseDown(event){
	painting = true;
}
*/

// 색 바꿔주기
function handleColorClick(event){
	//console.log(event.target.style);
	const color = event.target.style.backgroundColor;
	//console.log(color);
	ctx.strokeStyle = color;	// override
	ctx.fillStyle = color;		// override
}

// 선(펜) 굵기 변경
function handleRangeChange(event){
	//console.log(event.target.value);
	const size = event.target.value;
	ctx.lineWidth = size;	// override
}

// 그리기 or 채우기 글자 바꿔주는거
function handleModeClick(){
	if(filling == true){	// 채우기 -> 그리기 
		filling = false;
		mode.innerText = "그리기"
	} else { 
		filling = true;		// 채우기X(그리기) -> 채우기
		mode.innerText = "채우기"
		//ctx.fillStyle = ctx.strokeStyle; 이거 대신에 핸들컬러클릭에 ctx.fillStyle = color; 넣어줌 
	}
}

function handleCanvasClick(){
	if(filling){
		ctx.fillRect(0, 0, canvas.width, canvas.height);
		// 시작점부터 캔버스 크기만큼 채워준다.
	}
}

// 사실 캔버스가 픽셀을 다뤄서 우클릭으로 이미지 저장이 가능함
// 우클릭 저장 방지 
function handleCM(event){
	event.preventDefault();
}

// 저장
function handleSaveClick(){
	// https://developer.mozilla.org/en-US/docs/Web/API/HTMLCanvasElement/toDataURL
	// 지정된 포맷의 이미지 표현을 포함한 dataURL을 반환함-> 나온 URL을 주소창에 쳐보면 이미지가 나옴 
	// ＊png가 디폴트(화질이 더 좋음), jpg도 가능
	const image = canvas.toDataURL();
	const link = document.createElement("a"); // 브라우저로 가지말고 다운받아라 
	link.href = image;
	link.download = "channel_image";
	link.click();
	
}


if(canvas){
	canvas.addEventListener("mousemove", onMouseMove);
	canvas.addEventListener("mousedown", startPainting); 	
	canvas.addEventListener("mouseup", stopPainting);
	canvas.addEventListener("mouseleave", stopPainting);	
	canvas.addEventListener("click", handleCanvasClick);
	canvas.addEventListener("contextmenu", handleCM);
}


Array.from(colors).forEach(color => 
color.addEventListener("click", handleColorClick)
);
// console log(colors); -> HTML 컬렉션 나옴 배열로 바꿔줌 (Array.from() -> 배열로 바꿔주는구나) 
// const colors = document.getElementsByClassName("jsColor"); -> color 반복 (다 가져오자) (color는 단순 변수명) 


// if 문의 값이 null 일 경우 자동으로 false로 판단 (변수값이 존재하지 않을 때 이벤트 실행시 발생되는 오류 사전에 차단)
if(range){
	range.addEventListener("input", handleRangeChange);
}

if(mode){
	mode.addEventListener("click", handleModeClick);
}

if(saveBtn){
	saveBtn.addEventListener("click", handleSaveClick);
}


