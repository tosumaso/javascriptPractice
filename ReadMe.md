# 学んだ内容

1. XmlHttpRequet,fetchApiを使ったAjax処理

	1. ページ遷移しないGET,POSTリクエストの送信
		1. GETリクエストではパスにクエリパラメータ(?parameter name=value)を含める
		2. POSTリクエストはhttpリクエストのボディに送信する値を付与して送信、payloadではJsonを,Form Dataではkeyとvalueのオブジェクトで渡す
		
	2. サーバーでJson or FormObjectを受け取り、メソッドに`@ResponseBody`がついた戻り値がクライエントのJsにJsonの形式で返される(JSONとJavaの変換にJacksonライブラリも使える)
		1. GETメソッドでは`@RequestParam`でパラメータをバインドして受け取る
		2. POSTメソッドではJsonを受け取るなら`@RequestBody`でhttpリクエストのボディから値を取得し、Form DataならFormObjectで受け取る
		 
	3. リスポンスを受け取りDOMを使ったhtmlの描画
		1. JsではhttpリスポンスはJson or XMLの形式で受け取るため,JsonからJsのオブジェクトに変換
		2. DOMを使い指定した要素にリスポンスの結果を差し込む
	
2. lazysizesを使った画像の非同期表示

	1. 画面に表示されている画像のみ読み込み
	2. スクロールすることで随時画像を読み込む
	
3. Ajax通信を使ったDBの連携
	
	1. formに入力された値をForm DataとしてHttpリクエストを送り、サーバーではFormObjectとして受け取る
	2. FormObjectから値を取り出しRepositoryを使ったDB処理を行う。戻り値はJSONで返す。
	3. springからEntityをListにしたJSONが返されてJSでJSONからJSのオブジェクトに整形した場合、配列でレコードが格納されている
	
