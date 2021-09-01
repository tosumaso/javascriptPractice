package practice.example.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import practice.example.Form.Greeting;
import practice.example.Form.HelloMessage;

@Controller
public class WebSocketController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception{
		Thread.sleep(1000);
		System.out.println(message.getMessage());
		return new Greeting(HtmlUtils.htmlEscape(message.getName()) + " : " + HtmlUtils.htmlEscape(message.getMessage()));
	}
	
	@GetMapping("/getWebSocket")
	public String getWebSocket() {
		return "/websocket";
	}
}
