package practice.example.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import practice.example.JsonConverter;
import practice.example.SendPathForm;

@Controller
public class PracticeController {
	
	// Jacksonライブラリ：JavaとJsonの変換を行う、インスタンスをDIコンテナ
	@Autowired
	ObjectMapper mapper;

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
	
	@GetMapping("/ajax")
	public String getAjax() {
		return "/ajax";
	}

	@GetMapping("send/path/url")
	@ResponseBody
	public String ajax(Model model, @RequestParam(name="data") String data) throws IOException{
		System.out.println("ajaxのデータ" + data);
		JsonConverter j = new JsonConverter();
		j.content = data;
		String json = mapper.writeValueAsString(j);
		return json;
	}
	
	@PostMapping("send/path/post")
	@ResponseBody
	public String postAjax(@RequestBody String data) { //httpリクエストのリクエストボディにjsonデータが入っており、@RequestBodyでバインドする
		System.out.println(data);
		return data;
	}
	
	@PostMapping("/send/path/form")
	@ResponseBody
	public String postAjaxForm(SendPathForm form) { //formで値が送信された場合、httpリクエストのボディにFormDataが付与されるためFormObjectで受け取る
		String json = null;
		try {
			json = mapper.writeValueAsString(form);	//リクエストに含まれた値を含むFormObjectはDIコンテナで自動生成されていて、jacksonライブラリでjsonに変換
		} catch (JsonProcessingException error) {
			System.out.println(error);
		}
		System.out.println(json);
		return json;
	}
}
