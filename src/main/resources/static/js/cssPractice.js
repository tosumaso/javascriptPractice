// １文字ずつアニメーション
document.addEventListener("DOMContentLoaded",()=>{
	const CLASSNAME = "-visible";
	const TIMEOUT = 1500;
	const target = document.querySelector(".title");

	setInterval(() => {
		target.classList.add(CLASSNAME);
		setTimeout(() => {
			target.classList.remove(CLASSNAME);
		}, TIMEOUT);
	}, TIMEOUT * 2);
})
	
