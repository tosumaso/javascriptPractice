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
	
9. いいね機能(DB相関図:user hasmany posts,hasmany favourites, post belongsto user, hasone favourite, favourite belongsto user,belongsto post)

	1. いいねが押されたらajax通信を行い、サーバーでいいね機能テーブルの外部参照先の情報を取得して外部参照キーとして保存
	2. トップ画面を表示する際、現状のいいねレコードをリポジトリ経由で取得後、その総数を`List<Favourite>.size()`で取得して戻り値で返す。
	3. 言い値送信後はレコードを保存し、総数をjsonで返し、DOMで描画する
	
10. 星の評価機能

	1. 星マークにマウスオーバーしたとき、外れたとき、クリックしたときのイベントに分ける
	2. 最終的にチェックがついた星の数をサーバーに送る。

11. タグ登録、検索機能(DB相関図: item hasmany categories, category hasmany items, jointable)

	1. タグ機能とタグをつける対象にテーブルを分け、manytomanyの組み合わせを管理する中間テーブルを作成。両者のテーブルには外部参照キーをつけず、中間テーブルに２つつける。
	2. manytomanyの親に`@JoinTable`で中間テーブルの定義を書き、子にmappedByで親の外部参照フィールドを指定する
	3. manytomanyでは片方のentityを作成して値をセット、もう片方のentityも値をセットし,`親entity.get子の外部参照.add(子のentity)`と`子entity.get親の外部参照.add(親のentity)`で外部参照情報を持たせてentityを作成し、片方のrepositoryを経由して保存する
	
12. Cascade(DBの外部参照制約とspringの相関につける)

	1. Cascade: 外部参照先のオブジェクトに変更が加えられたときの参照元オブジェクトの変化を指定すること
	2. Cascadeオプション Restrict:update/delete エラーになる Cascade:updateは参照先に依存,deleteは参照先がなくなると参照元も削除される
	3. Cascadeオプション Set Null:update/delete nullに置き換わる
	4. springの`@OneToMany`や`@ManyToMany`などのオプションにcascade = CascadeType.ALLをつけることでそのentityの変化がもう一つのentityに反映される
	
13. WebSocket通信(STOMP.JS = ajaxより軽いメッセージ通信が可能なJSライブラリ, Sock.JS = WebSocketを扱うJSライブラリ)

	1. メッセージをJsonで送信し、formで受け取る。
	2. コンフィグクラスでメッセージを扱うBrokerとEndpointの設定を定義する
	3. `@MessageMapping`でクライアントからメッセージを受け取り、`@SendTo`で指定したパスをsubscribeしているクライアントにメッセージのレスポンスを渡す
	
14. Docker(Spring)メモ
	
	1. docker pull イメージ名(:タグ名)：ローカルに指定したイメージが無いならDockerHubからイメージをダウンロードする。タグを指定したらタグ付のイメージを取得
	2. docker run -it -name="コンテナ名" イメージ名:タグ名：　イメージを元にコンテナを作成し、起動してアクセスする。--rmオプションはコンテナが落ちたらコンテナ自身を削除する、テスト用。
	3. docker run イメージ名：　ローカルに指定したイメージが無いならDockerHubからダウンロードし、コンテナを作成する
	4. docker images：　dockerイメージの一覧表示、docker rmi イメージ名/Id：　dockerイメージの削除
	5. docker ps (-a)：　dockerコンテナの一覧表示。aオプションを外すと稼働中のコンテナのみ表示される、 docker rm コンテナ名/Id：　コンテナの削除
	6. docker restart/start コンテナ名：　停止状態のコンテナを再度起動する
	7. docker attach/exec コンテナ名：起動している仮想コンテナにアクセスする。attachはexitで抜けた後コンテナが落ちる(ctrl + p + q　でコンテナを立ち上げた状態でmacの操作に戻る)。execはexitで抜けてもコンテナが落ちない
	8. centOsコンテナ内で yum update -y: centOsのコマンドアップデート、 yum install httpd -y:　httpd(Webサーバーのデーモン)をインストール
	9. カスタマイズしたコンテナをイメージに直す(同じ環境をコピー): docker commit コピー元のコンテナ名 コピー先/新規のイメージ名 
	10. docker run -d イメージ名 /usr/sbin/httpd -DFOREGROUND : httpdデーモンを起動した状態でコンテナを起動。docker runの引数にhttpdを起動するコマンドを指定し、コンテナにログインしたときに実行される。Webサーバーの機能に必要
	11. Portマッピング: ホストOSのportとコンテナのポートを紐づかせる: docker run [--rm] -d -p 8080(macのポート名):80(コンテナのポート名) イメージ名 /usr/sbin/httpd -DFOREGROUND  : macのブラウザからコンテナにアクセス可能
	12. Volumeマッピング
	13. Dockerfile: イメージの作成をコードにまとめたファイル。Dockerfileを読み込んでイメージを作成し、そのイメージを元にコンテナを作成し起動すればブラウザからアクセスできる。
	14. Dockerfileをbuildして作成したイメージを元にコンテナを作成してそのコンテナ内に入る場合: docker exec -it コンテナ名 bash
	15. Dockerのボリューム/マウントが失敗する場合、DeskTop for MacのGeneral > Use gRPC FUSE for file sharingをオフにする
	16. MySQLのコンテナを作成: docker run -d --env MYSQL_ROOT_PASSWORD= パスワード名 mysql:[タグ名]　：MySQLのパスワードをLinuxコンテナの環境変数に通す
	17. MySQLのコンテナにアクセス: docker exec -it コンテナ名 [/bin/]bash ：　コンテナ内でbashにあるシェルを起動し、あとはmysql -u root -pでMySQLを通常通り操作する
	18. 依存関係やソースコードがjarファイルにまとめられイメージとしてビルドされる。コードを変更した場合はその都度maven installを行いjarファイルを最新の物して、imageを作り直す
	19. dockerfileでイメージを作成し、docker-composeで複数のコンテナを同時に管理できる
	20. dockerでthymeleafを使うには、templatesフォルダからの相対パスで書かれていたcontrollerの戻り値を、最初の/を消して指定する
	21. dockerfile = 公式のイメージにオリジナルの処理を加えイメージを作成する docker-composeのimageプロパティ = 公式のイメージをそのままダウンロードしてイメージ作成。docker-composeはローカル開発に使う。
	22. コンテナを削除するとデータが消える方法　バインドマウント 1: 共有ストレージをコンテナにマウント(コンテナからHostOSのディレクトリを参照できる)する
	23. コンテナ内のデータを永続化する方法　ボリューム: HostOSのDocker内にボリューム(データの永続的な保存領域)を作成
	24. docker-composeのservices内に定義されているvolumes:バインドマウント、トップレベルのvolumes:ボリューム
	25. Docker Desktop for Macが裏で動かしているLinuxOsにアクセスする方法: docker run -it --rm --privileged --pid=host alpine:edge nsenter -t 1 -m -u -n -i sh
	26. バインドマウントでホストディレクトリとコンテナディレクトリを紐づける場合、ホストのディレクトリはfileSharingで指定したパスまたはその子孫である必要がある
	27. mysqlはmy.cnfファイルを設定ファイルとして参照する：mysqlコンテナで文字コードを設定するためにホストのディレクトリにmy.cnfファイルを新たに作成し、docker-composeファイルのvolumes:内でバインドマウントさせる(例：./my.cnf:/etc/mysql/conf.d/my.cnf :プロジェクト内のmy.cnf:mysqlコンテナの設定ファイルパス)
	28. volumeファイルはコンテナが削除されてもデータが残る：　初期化したい場合は一度削除してまた作り直す
	29. docker-compose down (--rmi all)(--volumes): docker-composeファイルで作成したコンテナとネットワークを削除。オプションでcomposeに紐づくイメージとボリュームも削除できる
	30. docker-compose -f docker-composeファイル名 up: docker-composeファイルを元にイメージ、コンテナの作成

15. Herokuデプロイ(Gitデプロイ)
	
	1. git init, git add ., git commitでプロジェクトをgitの管理下に置く
	2. springプロジェクトのroot直下にsystem.propertiesファイルを配置、java.runtime.version=[pomに書かれているjavaのversion]を記載してHerokuで扱える
	3. heroku create: herokuアプリの作成
	3. git push heroku master
	4. herokuのgitリポジトリー名、アプリ名を変更: heroku apps:rename [新しい名前]
	5. mysqlをherokuにデプロイ: heroku addons:create cleardb:[ignite(mysqlを無料で扱う場合はignite)]
	6. herokuの環境変数を確認: heroku config
	7. heroku configの中身: 
		===<アプリ名> Config Vars
		CLEARDB_DATABASE_URL: mysql://<ユーザー名>:<パスワード>@<ホスト名>/<データベース名>?reconnect=true
	   新しい環境変数、DATABASE_URLにCLEARDBで書かれた情報をまとめて変数に設定する
	   (例:DATABASE_URL:         mysql://bf1ba6f33e7de6:598fffb9@us-cdbr-east-04.cleardb.com/heroku_493a3ece8ba1e69?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8)
	8. アプリの実行: heroku open
	9. herokuに登録したレコードはprimary keyが10ずつ増加する、仕様なので変更しない
	10. Herokuを立ち上げるときにjarファイルを読み込ませるためにProcfilwを置き指定したjarファイルを読み込む
		例: web: java -Dserver.port=$PORT -jar target/<jarのファイル名>.jar
		jarファイルの中にはmanifestが含まれている必要がある(manifestファイルの中にMainClassが定義されていないとno main manifest attributeエラーがでる)
	11. herokuに上げたアプリの停止: heroku ps:scale web=0
	
16. Herokuデプロイ(Dockerデプロイ,heroku.yml)

	docker-compose.ymlはherokuデプロイに対応していない。heroku.ymlを作成する。
	1. heroku.ymlを作成,dockerfileはopenjdkをベースにして、jarファイルのコピーをrunする
	2. herokuのアプリはgitで連携するため、git add .とcommitで変更履歴をherokuのrepositoryに反映させる

	1. heroku container:login : Container Registrにログイン
	2. heroku create : herokuアプリを作成,herokuのgitRepositoryが作成される
	3. heroku addons:create cleardb:ignite [--version=mysqlのバージョン] : mysqlをherokuに導入
	4. heroku container:release web: 
	5. heroku config:set DATABASE _URL="CLEARDB_DATABASE_URL" : herokuでcleardbを使うための環境変数
	6. heroku stack:set container : スタックをセット
	7. git push heroku master: 変更を再びherokuのgitRepositoryにプッシュして変更を反映
	8. heroku open
	
17. Dockerfile Command

	1. CMD: docker runでコンテナを起動するときのコマンドまたは引数を指定。docker run時コマンドを上書きする
	2. ENTRYPOINT: docker runでコンテナを起動するときのコマンドまたは引数を指定。CMDとは違いdocker run実行時にコマンドを追加する
	3. EXPOSE: Dockerコンテナ内で公開するポート番号を指定