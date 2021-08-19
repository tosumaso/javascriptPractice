

//XMLHttpRequestを使いhttpRequestを送りResponseを受け取る場合
/*function fetchUserInfo(userId){
	const request = new XMLHttpRequest();
	request.open("GET",`https://api.github.com/users/${encodeURIComponent(userId)}`);
	request.addEventListener("load", ()=> {
		if (request.status >= 200 && request.status < 300){
			const userInfo = JSON.parse(request.responseText)
			console.log(userInfo);
		} else {
			console.log("エラー",request.statusText)
		}
	});
	request.addEventListener("error", () => {
		console.error("ネットワークエラー");
	});
request.send();
}   */

//fetch apiを使いhttpRequestを送りResponseを受け取る場合
//htmlの特殊文字のエスケープ処理は"//teppeis.github.io/htmlspecialchars/index.js"ライブラリのhtmlspecialchars("エスケープしない処理")を使用
function fetchUserInfo(userId) {
	return fetch(`https://api.github.com/users/${encodeURIComponent(userId)}`)
		.then(response => {
			if (!response.ok) {
				return Promise.reject(new Error(`${response.status}: ${response.statusText}`))
			}
			return response.json()
		});
}

function createView(userInfo) {
	return `
			<h4>${userInfo.name} (@${userInfo.login})</h4>
			<img src="${userInfo.avator_url}" alt= "${userInfo.login}" height="100" >
			<dl>
				<dt>Location</dt>
				<dd>${userInfo.location}</dd>
				<dt>Repositories</dt>
				<dd>${userInfo.public_repos}</dd>
			</dl>
			`
}

function displayView(view){
	const result = document.getElementById("result")
	result.innerHTML =view;
}

function main(){
	fetchUserInfo("js-primer-example")
		.then(userInfo => {return createView(userInfo)})
		.then(view => {displayView(view)})
		.catch(error => console.log(error))
}

async function main(){
	try{
	const userInfo =await fetchUserInfo("js-primer-example")
	const view = createView(userInfo);
	displayView(view)
	} catch(error){
		console.log(error.message)
	}
}
