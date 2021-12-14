package practice.example.Dialect;

//thymeleafの式から呼び出せるメソッドを定義
public class ExpressionObject1 {
	
	public String returnDouble(String text) {
    	String result = text + text;
    	return result;
    }
}
