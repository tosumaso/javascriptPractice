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
	
4. FileReaderを使った画像のプレビュー表示、画像をドラッグしてアップロード

	1. 画像のドラッグ先の要素にdragover,dragleave,dropのイベントを登録する。
	2. FileReaderでアップロードした画像の情報を操作し、readAsDataUrlでその画像のURIを取得。FileReader.resultに格納される
	3. `<img>`のsrc属性にURIを持ったFileReader.resultをセットし、任意の要素に格納する。
	
5. モーダル画面
	
	1. JSでモーダル画面の背景にdisplay:blockやnoneを挿入して表示を切り替える
	2. モーダル画面はz-indexで手前に来るように調整し、rgbaの透明度は0.5ぐらいにして半透明にする
	3. モーダル画面は閉じるボタンまたはモーダル背景をクリックすることで閉じ、eventのターゲットがmodal背景なら閉じれる
	
6. Css(block,inline,inline-block)

	1. inline-block: inline要素の特徴に上下左右のmargin,paddingが可能と高さと幅を指定できるようになったもの。
	2. `inline-block`は横並びにしたい要素につける、`display:flex`は要素を横並びにしたい子要素の親要素につける。
	
7. グローバルメニュー
	
	1. グローバルメニューの一覧に表示する目次を`<ul>`内に`<li>`で書き、そのliタグ内にドロップダウンで表示する項目を`<ul>`、`<li>`でネストする
	
8. 擬似要素(セレクタ::before, セレクタ::after)

	1. 指定したセレクタの中の最初or最後に擬似要素を作る
	2. contentプロパティは必須で、いらない場合は空文字を指定できる
	3. 擬似要素に`position:absolute`をつけると擬似要素の作成元の要素が親要素となりrelativeを持つ。
	
9. 