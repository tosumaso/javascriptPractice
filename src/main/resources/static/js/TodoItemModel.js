let TodoIdx =0;

export
class TodoItemModel{
	constructor({title,completed}){
		this.id = TodoIdx++;
		this.title = title
		this.completed = completed
	}
}