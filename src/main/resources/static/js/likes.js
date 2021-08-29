//いいね機能
window.addEventListener("load", () => {
	document.querySelector("#favourite").addEventListener("click", async () => {
		const result = await fetch("send/Myfavourite");
		const data = await result.json(); /* サーバーからオブジェクトの配列がJsonになったものを受け取り、それをJsオブジェクトに変えるPromiseインスタンスを返す*/
		document.querySelector("#times").innerHTML = data.length; //レスポンスはいいねテーブルのレコードの配列を受け取るためlengthで長さを取得し、現在のいいね数を描画できる
	})
})

//星の評価機能
document.addEventListener("DOMContentLoaded", () => {
	const stars = document.getElementsByClassName('star');

	// 星マークにマウスオーバーした時のイベント
	const starMouseover = (e) => {
		const index = Number(e.target.getAttribute('data-star')); //mouseoverされたHTMLClassList内の要素をe.targetで特定してその属性値を取得
		for (let j = 0; j < index; j++) {
			stars[j].textContent = '★';
		}
	}

	// 星マークからマウスが離れた時のイベント
	const starMouseout = (e) => {
		for (let j = 0; j < stars.length; j++) {
			stars[j].textContent = '☆';
		}
	}

	for (let i = 0; i < stars.length; i++) {
		stars[i].addEventListener('mouseover', starMouseover);
		stars[i].addEventListener('mouseout', starMouseout);

		// 星マークをクリックした時のイベント
		stars[i].addEventListener('click', e => {

			for (let j = 0; j < stars.length; j++) { //1:星をクリックしたらいったん全ての黒星が消える(黒星の数を減らしたいときに対応するため)
				stars[j].textContent = '☆';
			}
			const index = Number(e.target.getAttribute('data-star'));//2:その後クリックされた星の要素を特定し、属性値の数だけ星を黒星にする。
			for (let j = 0; j < index; j++) {
				stars[j].textContent = '★';
			}
			// マウスオーバーとマウスアウトのイベント解除
			for (let j = 0; j < stars.length; j++) {
				stars[j].removeEventListener('mouseover', starMouseover);
				stars[j].removeEventListener('mouseout', starMouseout);
			}

			document.querySelector("#star-rating").addEventListener("click", async (e) => {
				e.preventDefault();
				let form = new FormData();
				form.append('star', index); //クリックされた星の数を値として送る
				const response = await fetch('send/starRating', {
					method: 'post',
					body: form
				})
				const data = await response.json();
				console.log(data);
			})
		})
	}
})


