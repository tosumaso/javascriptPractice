const srcMap = new Map();
window.addEventListener("DOMContentLoaded",()=>{
	const imgs = document.querySelectorAll("img"); //querySelectorAll(セレクタ):指定したセレクタを持つ要素全てをNodeListオブジェクトに格納して返す
	imgs.forEach((img) =>{
		srcMap.set(img,img.dataset.src);
		img.removeAttribute('src')
	})
})

window.addEventListener('load', ()=> {
	const imgs =document.querySelectorAll("img")
	imgs.forEach(img =>{
		const source =srcMap.get(img)
		img.src = source;
	})
})