document.addEventListener("DOMContentLoaded",()=>{
	document.querySelector("#saveDb").addEventListener("click",async (e)=>{
		e.preventDefault();
		const mtNameElement = document.querySelector("#mtName");
		const mtHeightElement = document.querySelector("#mtHeight");
		const form = new FormData();
		form.append("name", mtNameElement.value);
		form.append("height", mtHeightElement.value);
		const response = await fetch("send/data/db",
		{
			method: "POST",
			body: form
		});
		const data= await response.json();
		console.log(data)
		data.forEach(byte =>{ //サーバーがListで送ったJSONをJsでは配列で受け取り、配列の要素(springのレコード)を一つずつ取り出す
			const p = document.createElement("p") //appendChildで差し込むための要素ノードを作成
			p.innerHTML = `Id:${byte.id} ,　名前:${byte.name},  標高:${byte.height}` //要素から値を取り出し編集、ノードの中にhtmlとして挿入
			document.querySelector("#mount-list").appendChild(p); //`<p>htmlの内容</p>となった子ノードを親ノードの一番下に挿入`
		});
		mtNameElement.value = ""; //Ajax通信終了後は入力欄の書き込みを空文字で上書きすることでテキストボックスを空にする。
		mtHeightElement.value ="";
	})
})
