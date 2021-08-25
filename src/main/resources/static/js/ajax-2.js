// 画像のプレビュー表示、ドラッグで画像のアップロード
document.addEventListener("DOMContentLoaded", () => {

	const zone = document.querySelector("#drop-zone"); //ドラッグ先の要素

	zone.addEventListener("dragover", (e) => { //ドラッグ中のコンテンツをドラッグ先の要素に掲げている状態
		e.stopPropagation(); //イベントの伝搬を止める(親要素にdragoverイベントが登録されていないので書かなくても良い)
		e.preventDefault();
		zone.style.background = "#ff3399"; //id#drop-zoneのstyle属性のbackgroundプロパティに色を代入
	})
	zone.addEventListener("dragleave", () => { //ドラッグ中のコンテンツがドラッグ先の要素から外れた場合
		zone.style.background = "#ffffff";
	})
	zone.addEventListener("drop", (e) => { //ドラッグ中のコンテンツがドラッグ先の要素にペーストされた状態
		e.stopPropagation();
		e.preventDefault();

		zone.style.background = "#ffffff";

		const files = e.dataTransfer.files; //ドラッグしているファイルについてFileListが管理

		const fr = new FileReader() // ファイルの読み込みに関するオブジェクト
		for (let i = 0; i < files.length; i++) {
			fr.readAsDataURL(files[i]); //FileListの配列に格納されているFileを１つずつ読み込みURIを取得
			fr.addEventListener("load", () => { //　ファイルリーダーがアップロードした画像を全て読み込んだ場合
				const img = document.createElement('img');
				img.setAttribute('src', fr.result); //FileReader.resultで読み込んだ画像の結果を取得する

				const preview = document.getElementById("preview");
				preview.appendChild(img);
			})
		}

	})

})

// モーダル画面の表示切替
document.addEventListener("DOMContentLoaded", () => {
	const modalButton = document.querySelector("#button"); //モーダル画面を表示させるボタン
	const modal = document.querySelector("#modal"); //モーダル画面の背景
	const closeBtn = document.querySelector("#closeBtn"); //モーダル画面を閉じるボタン
	modalButton.addEventListener("click", () => { 
		modal.style.display = "block"; //モーダル表示ボタンが押されたら#modalのdivのstyle属性のdisplayプロパティにblockを追加
	})
	closeBtn.addEventListener("click", () => {
		modal.style.display = "none"; //閉じるボタンが押されたらモーダル背景のdivタグにdisplay:noneを設定して見えなくする
	})
	window.addEventListener('click', (e)=> { //閉じるボタンの他にモーダル背景がクリックされたらモーダルを閉じる
		if (e.target === modal) { //クリックされた要素が#modalのdiv要素なら
			modal.style.display = 'none';
		}
	});

})