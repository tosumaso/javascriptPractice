//XMLHttpRequestの場合

/*document.querySelector("#ajax").addEventListener("click",()=>{ //ajax通信を行う条件
	const xhr = new XMLHttpRequest(); //1.ajax通信を始めるためのインスタンスを作成
	xhr.open("GET","send/path/url/?data=XHR",true) //ajax通信で送るhttpRequetの初期化(httpMethod,リクエストURL(getメソッドで送るならリクエストパラメータを含む),非同期or同期のboolean)
	xhr.onreadystatechange = ()=>{ //ajax通信でresponseを受け取ったら発動するコールバックを設定
		if (xhr.readyState == 4 && xhr.status == 200){ //readyState: クライアントの状態(4==ajax通信が完了) status: http通信の状態
			console.log(xhr)
			const data = JSON.parse(xhr.response); //サーバーからjson形式でデータを受け取るためhttpResponseをJSのオブジェクトに変換
			document.querySelector("#text").textContent = data.content;
		}
	}
	xhr.send() //httpRequestをajaxで送信("POST"で送る場合はsendの引数に送る情報を設定),ajax通信は非同期なためresponse取得の処理を書いてから送信処理を書いても大丈夫
})*/

//FetchApiの場合

document.querySelector("#ajax").addEventListener("click", () => {
	fetch("send/path/url/?data=XML").then(response => { //fetchhttpRequestをajaxで送り、第二引数の情報が
		if (response.ok) {
			return response.json();
		} else {
			throw new Error();
		}
	}).then(json => {
		document.querySelector("#text").innerHTML = json.content;
	}).catch(error => { alert(error) })
})

//FetchApi(postメソッド)
document.getElementById("post-ajax").addEventListener("click", () => { 
	fetch("send/path/post",
		{
			method: "POST",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({ id: 1111, name: "わい" })
		}) //method: "POST",headers: httpRequestのヘッダにデータの送信形式を指定, body: httpRequestのボディに実際に送る値を定義、Content-Typeで指定した形式で付与させる
		.then(response => {
			if (response.ok) {
				return response.json()
			} else {
				throw new Error()
			}
		}).then(data => {
			document.querySelector("#text").textContent = `id: ${data.id}、name: ${data.name}`;
		}).catch(error => { alert(error) })
})

//FetchApi(postメソッド+formから送信)
document.querySelector("#post-ajax-form").addEventListener("click", async (e) => { //formのボタンがクリックされたらイベント発生(submitイベントだとajaxで送信するときに探知されない？)
	e.preventDefault(); //本来、formのactionで送るリクエストを無効化する、ajaxで送るため
	const studentIdElement = document.querySelector("#student-id")
	const nameElement = document.querySelector("#name")
	var form = new FormData() //httpBodyにForm Dataの形式でデータを送信する場合インスタンスを作成し、keyとvalueの形で第二引数内のbodyに選択
	form.append('studentId',studentIdElement.value)
	form.append('name', nameElement.value)
	const response = await fetch("send/path/form",
		{
			method: "POST",
			body: form
		})
	const data = await response.json()
	document.querySelector("#text").textContent = `${data.studentId}そして,${data.name}`;

})