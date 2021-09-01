var stompClient = null; //デフォルトではStompClientはnull(websocket通信していない状態)

function setConnected(connected) { //4 要素ノード.prop(属性),prop(属性,属性値):　指定した要素の属性値を取得するor要素の属性値を第二引数に変更 
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected); // websocket通信中ならconnectボタンを操作不可にし、通信解除ボタンをdisabled=falseにしてOnにする
	if (connected) { //要素ノード.hide(ミリ秒),.show(ミリ秒): 指定した時間後に要素を非表示or表示する
		$("#conversation").show();
	}
	else {
		$("#conversation").hide(); //websocket通信中ならメッセージを表示、切断状態なら表示しない
	}
	$("#greetings").html("");
}

function connect() { //3
	var socket = new SockJS('/endpoint');
	stompClient = Stomp.over(socket); //configで指定したエンドポイントを持つSockJSを初期化し、Stomp.over(SockJS)でSTOMPのClientを作成
	stompClient.connect({}, function(frame) { //Client.connect({headers},connectCallback,errorCallback) Or .connect(login,password,connectCallback,errorCallback): STOMPクラアントがwebsocket通信を開始する
		//connectCallback(frame)でframeには通信の状態が、errorCallback(error)のerrorにはエラー情報が格納されている
		setConnected(true); //websocket通信が可能な状態なら通信ボタンをconnectに変更 
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/greetings', function(message) { //Client.subscribe(destination to client, メッセージを受信したとき発動するcallback),第一引数宛てのメッセージを購読する
			showGreeting(JSON.parse(message.body).content); //メッセージのbodyに含まれているオブジェクトのメッセージを受け取り,レスポンスのformで定義したプロパティに入っているメッセージ自身を受け取りhtmlに描画する
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

function sendMessage() { //Client.send(destination to server,{headers},messagebody)
	stompClient.send("/app/hello", {}, JSON.stringify({ 'name': $("#name").val(), 'message': $("#message").val() }));
	$("#message").val('');
}

function showGreeting(message) { //5 受信したメッセージをhtmlに描画する
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function() { //1 Beginning 即時関数で一番最初に呼び出される
	$("form").on('submit', function(e) { //formの送信ではなくajax,websocket通信を使う
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); }); //connectボタンを押したらwebsocket通信開始の処理を行う
	$("#disconnect").click(function() { disconnect(); }); //disconnectボタンを押したらwebsocket通信終了の処理を行う
	$("#send").click(function() { sendMessage(); }); //送信ボタンを押したらメッセージを送る処理を行う
});

setTimeout("connect()", 3000); //2