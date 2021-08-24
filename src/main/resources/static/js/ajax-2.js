document.addEventListener("DOMContentLoaded", () => {
	
	const zone = document.querySelector("#drop-zone");

	zone.addEventListener("dragover", (e) => {
		e.stopPropagation();
		e.preventDefault();
		zone.style.background = "#ff3399";
	})
	zone.addEventListener("dragleave", (e) => {
		zone.style.background = "#ffffff";
	})
	zone.addEventListener("drop", (e) => {
		e.stopPropagation();
		e.preventDefault();

		zone.style.background = "#ffffff";

		const files = e.dataTransfer.files;

		const fr = new FileReader()
		for (let i = 0; i < files.length; i++) {
			fr.readAsDataURL(files[i]);
			fr.addEventListener("load", () => {
				const img = document.createElement('img'); 
				img.setAttribute('src', fr.result);
				
				const preview = document.getElementById("preview");
				preview.appendChild(img);
			})
		}

	})

})