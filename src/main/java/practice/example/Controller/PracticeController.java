package practice.example.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PracticeController {

	@GetMapping("/getMain")
	public String getMain() {
		return "/springScript";
	}
	
	@GetMapping("/getIndex")
	public String getIndex() {
		return "/index";
	}
	
	@GetMapping("/getPractice")
	public String getPractice() {
		return "/springPractice";
	}
}
