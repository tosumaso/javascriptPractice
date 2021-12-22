package practice.example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import practice.example.TestComp.DBForm;
import practice.example.TestComp.TestUser;

@Controller
public class TestController {

	@GetMapping("/hello/init")
	public String init() {
		return "hello";
	}
	
	@RequestMapping("/hello/model")
    private String init(Model model) {

        // ユーザリスト まずは手動で生成
        List<TestUser> userList = new ArrayList<TestUser>();

        TestUser user = new TestUser();
        user.setUserId(0L);
        user.setUserName("test0");

        TestUser user2 = new TestUser();
        user2.setUserId(1L);
        user2.setUserName("test1");

        userList.add(user);
        userList.add(user2);

        // フォームにユーザのリストを設定し、モデルへ追加することでモデルへ正常に追加されたか検証するための土台を整える
        DBForm form = new DBForm();
        form.setUserList(userList);

        model.addAttribute("message", "hello!");// 1

        model.addAttribute("user", user);// 2

        model.addAttribute("dbForm", form);// 3

        return "hello";
    }
}
