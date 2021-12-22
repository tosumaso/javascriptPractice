package practice.example.Service;

import org.springframework.stereotype.Component;

@Component //MVCモデル外のクラスをDIコンテナに管理させたい場合につける
//@Configuration //@Componentの代わりにクラスに@Configurationをつけ、インスタンスを返すメソッドに@BeanをつけるとそのインスタンスをDIコンテナ管理できる
public class Utility {

	//@Bean
	public int someCalculation(int a, int b) {
		return a*b;
	}
}
