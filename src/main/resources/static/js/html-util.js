export function htmlToElement(html){
	const template =document.createElement("template")
	template.innerHTML = html;
	return template.content.firstElementChild;
}

export function element(strings, ...values) {
    const htmlString = strings.reduce((result, str, i) => {
        const value = values[i - 1];
        if (typeof value === "string") {
            return result + htmlspecialchars(value) + str;
        } else {
            return result + String(value) + str;
        }
    });
    return htmlToElement(htmlString);
}

export function render(bodyElement, containerElement) {
    // containerElementの中身を空にする
    containerElement.innerHTML = "";
    // containerElementの直下にbodyElementを追加する
    containerElement.appendChild(bodyElement);
}