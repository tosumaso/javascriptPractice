
/* document.addEventListener('DOMContentLoaded', function(){

  var img_element = document.querySelector("#kain");

    // 画像読み込み完了したときの処理
    img_element.addEventListener('load', ()=> {
      console.log("load");
    });
}); */

const obj = {
	level: 1,
	nest: {
		level: 2
	}
};

//shallowcopy

function shallowCopy(obj) {
	return Object.assign({}, obj);
};

//deepcopy

function deepCopy(obj) {
	return JSON.parse(JSON.stringify(obj));
};

const shallow = shallowCopy(obj);
const deep = deepCopy(obj);

console.log(obj === shallow);
console.log(obj.nest === shallow.nest)

console.log(obj === deep);
console.log(obj.nest === deep.nest);

/* const colors = [
	{ "color": "red" },
	{ "color": "green" },
	{ "color": "blue" }
];

const index = colors.findIndex(obj => obj.color === "green")
console.log(index);

const element = colors.find(obj => obj.color === "blue")
console.log(element);
console.log(element.color); */

/* const str = "ABC あいう DE えお";
const alphabetsPattern = /[a-zA-Z]+/g;
// matchAllはIteratorを返す
const matchesIterator = str.matchAll(alphabetsPattern);
console.log(matchesIterator) */

/* const pattern = /ECMAScript (\d+)/;
// [マッチした全部の文字列, キャプチャの1番目, キャプチャの2番目 ....]
const [all, capture1] = "ECMAScript 6".match(pattern);
console.log(all); // => "ECMAScript 6"
console.log(capture1); // => "6" */

/* function baseJoin(baseURL,pathname){
	const modifiedBaseURL= baseURL.replace(/\/$/,"");
	return modifiedBaseURL + pathname;
}

function getResource(baseURL,pathname){
	const url = baseJoin(baseURL,pathname);
	console.log(url);
}

const baseURL = "http://example.com/resouces/";
const pathname = "/example.js";
getResource(baseURL, pathname); */

/* function func(strings, ...values){
	console.log(strings);
	console.log(values);
}

func `template ${10} literal${20}`; */

/* class MyArray extends Array {
	get first() {
		if (this.length === 0) {
			return undefined;
		} else {
			return this[0];
		}
	}

	get last() {
		if (this.length === 0) {
			return undefined;
		} else {
			return this[this.length - 1];
		}
	}
}

console.log(MyArray.prototype)
const array= MyArray.from([1,2,3,4,5])*/


/* function dummyFetch(path,callback){
	setTimeout(() => {
		if (path.startsWith("/success")){
			callback(null, {body: `Response body of ${path}`})
		} else {
			callback(new Error("Not Found"))
		}
	},1000 * Math.random())
}

dummyFetch("/success/data", (error,response) => {
	if (error) {
		console.log(error.message)
	} else {
		console.log(response)
	}
});

dummyFetch("/failure/data", (error,response) => {
	if (error){
		console.log(error)
	} else {
		console.log(response)
	}
}) */

/* function dummyFetch(path){
	return new Promise((resolve,reject) =>{
		setTimeout(() =>{
			if (path.startsWith("/success")){
				resolve({body: `Response of body ${path}`})
			} else {
				reject(new Error("Not Found"))
			}
		},1000 * Math.random())
	})
}

dummyFetch("/success/data").then(function (response){
	console.log(response);
}, function (error) {
	
})
dummyFetch("/failure/data").then(function onFullfilled(response){
	
},function onRejected(error){
	console.log(error)
}) */

/* function dummyFetch(path) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			if (path.startsWith("/resource")) {
				resolve({ body: `Response body of ${path}` });
			} else {
				reject(new Error("NOT FOUND"));
			}
		}, 1000 * Math.random());
	});
}
console.log(Promise.all([dummyFetch("/resource/A"),dummyFetch("/resource/B")])) */

/* function dummyFetch(path) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			if (path.startsWith("/resource")) {
				resolve({ body: `Response body of ${path}` });
			} else {
				reject(new Error("NOT FOUND"));
			}
		}, 1000 * Math.random());
	});
} */

/* async function fetchResources(resources){
	const results =[];
	for (let i =0; i < resources.length; i++){
	 const response =  dummyFetch(resources[i])
	 results.push(response.body);
	}
	return results
}

fetchResources(["/resource/A","/resource/B"]).then((results)=>{console.log(results)}) */

/* function asyncWork(number) {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			if (typeof number === "number") {
				let numArray = [];
				for (let i = 1; i <= number; i++) {
					numArray.push(number)
				}
				resolve(numArray)
			} else {
				reject(new Error("タイプちがくね？"))
			}
		}, 1000)
	})
}

async function countNumber(number) {
	const promise = await asyncWork(number)
	return promise;
}

countNumber(4).then(numArray => { console.log(numArray) }) */

