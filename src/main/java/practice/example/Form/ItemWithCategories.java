package practice.example.Form;

import java.util.List;
import java.util.stream.Collectors;

public class ItemWithCategories {

	private String content;
	
	private List<String> categoryName; //formから送られた同じ名前のパラメータをListで受け取る。
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(List<String> categoryName) { //受け取ったformのパラメータからnull,空文字を弾く
		List<String> categoryWithoutNull = categoryName.stream().filter(category -> category != null && category != "").collect(Collectors.toList());
		this.categoryName = categoryWithoutNull;
		
	}
	
	
}
