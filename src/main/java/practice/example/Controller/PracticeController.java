package practice.example.Controller;

import java.io.IOException;
import java.util.List;

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

import practice.example.Entity.Mountain;
import practice.example.Form.JsonConverter;
import practice.example.Form.MountainForm;
import practice.example.Form.SendPathForm;
import practice.example.Repository.MountainRepository;

@Controller
public class PracticeController {
	
	// Jacksonライブラリ：JavaとJsonの変換を行う、インスタンスをDIコンテナ
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MountainRepository MtRepository;

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
	
	@PostMapping("/send/data/db")
	public String postAjaxToDB(MountainForm form) { //httpリクエストのFormDataをFormObjectで受け取り、Repositoryでセーブ
			Mountain mountain = new Mountain();
			mountain.setName(form.getName());
			mountain.setHeight(form.getHeight());
			MtRepository.save(mountain);
			return "redirect:/get/index"; //リダイレクト先でJSONを返しても非同期のまま
	}
	
	@GetMapping("/get/index")
	@ResponseBody
	public List<Mountain> getIndexAjax(){
		List<Mountain> data =MtRepository.findAll();
		return data;
	}
	
	@GetMapping("/get/fileUploader")
	public String getFileUploader() {
		return "/fileUpload";
	}
	
	@GetMapping("/getCss")
	public String getCss() {
		return "/IndexCss";
	}
	
	@GetMapping("/getGlobalMenu")
	public String getGlobalMenu() {
		return "/globalmenu";
	}
}
