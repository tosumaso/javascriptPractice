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