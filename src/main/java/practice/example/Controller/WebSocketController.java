package practice.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import practice.example.Entity.Message;
import practice.example.Form.HelloMessage;
import practice.example.Repository.MessageRepository;

@Controller
public class WebSocketController {
	
	@Autowired
	MessageRepository messageRepository;
	
	@MessageMapping("/hello")
	@SendTo("/big/greetings")
	public Message greeting(HelloMessage message) throws Exception{
		Thread.sleep(1000); //送信してからクライアントに返すまでのラグ(ミリ秒)
		Message savedMessage = new Message();
		savedMessage.setContent(message.getMessage());
		messageRepository.save(savedMessage); //formオブジェクトから値を取得しリポジドリー経由で保存
		return savedMessage; //追加されたメッセージを表示するために@SendToを購読しているクライアントに渡す
	}
	
	@GetMapping("/getWebSocket") //チャット画面一覧表示
	public String getWebSocket(Model model) {
		model.addAttribute("messages", messageRepository.findAll()); //一覧画面取得時、メッセージの一覧を取得してhtmlに描画する
		return "/websocket";
	}
}
