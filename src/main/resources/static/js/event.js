export class EventEmitter{
	
	constructor(){
		this._listners = new Map();	
		this._name = "あいうえお"
	}
	
	addEventListener(type,listener){
		if (!this._listners.has(type)){
			this._listners.set(type, new Set())
		}
		const listenerSet = this._listners.get(type);
		listenerSet.add(listener)		
	}
	
	emit(type){
		const listenerSet =this._listners.get(type)
		if (!listenerSet){
			return;
		}
		listenerSet.forEach(listener =>{
			listener.call(this)
		})
	}
	
	removeEventListener(type,listener){
		const listenerSet = this._listners.get(type)
		if (!listenerSet){
			return;
		}
		listenerSet.forEach(ownListener =>{
			if (ownListener === listener){
				listenerSet.delete(listener);
			}
		})
	}
}
