window.addEventListener("load",()=>{
	document.querySelector("#favourite").addEventListener("click",async()=>{
		const result = await fetch("send/Myfavourite");
		const data = await result.json(); /* サーバーからオブジェクトの配列がJsonになったものを受け取り、それをJsオブジェクトに変えるPromiseインスタンスを返す*/
		document.querySelector("#times").innerHTML = data.length; //レスポンスはいいねテーブルのレコードの配列を受け取るためlengthで長さを取得し、現在のいいね数を描画できる
	})
})