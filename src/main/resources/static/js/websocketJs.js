let stompClient = null; 

function setConnected(connected) {
	document.querySelector("#connect").disabled = connected;
	document.querySelector("#disconnect").disabled = !connected;

	const conversation = document.querySelector("#conversation");
	if (connected) {
		
	}
	else {
		conversation.style.display = "none";
	}
}

function connect() {
	const socket = new SockJS('/endpoint'); 
	stompClient = Stomp.over(socket); 
	stompClient.connect({}, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/big/greetings', function(message) {
			const record = JSON.parse(message.body);
			showGreeting(record.content);
		});
	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendMessage() {
	const name = document.querySelector("#name");
	const message = document.querySelector("#message");
	stompClient.send("/app/hello", {}, JSON.stringify(
		{ 'name': name.value, 'message': message.value }));
	message.value = "";
}

function showGreeting(message) {
	const tr = document.createElement("tr");
	const td = document.createElement("td");
	td.textContent = message;
	tr.appendChild(td);
	document.querySelector("#greetings").appendChild(tr); // <tr><td>message</td></tr>を描画
}

$(function() { //一番最初に処理される即時関数
	document.querySelectorAll(".form-inline").forEach(target => {
		target.addEventListener("submit", (e) => {
			e.preventDefault();
		});
	});
	document.querySelector("#connect").addEventListener("click", () => { connect(); });
	document.querySelector("#disconnect").addEventListener("click", () => { disconnect(); });
	document.querySelector("#send").addEventListener("click", () => { sendMessage(); });
});

setTimeout(connect, 3000);