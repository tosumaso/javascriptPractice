package practice.example.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import practice.example.Entity.Category;
import practice.example.Entity.Favourite;
import practice.example.Entity.Item;
import practice.example.Entity.Mountain;
import practice.example.Entity.Post;
import practice.example.Entity.User;
import practice.example.Form.ItemWithCategories;
import practice.example.Form.JsonConverter;
import practice.example.Form.MountainForm;
import practice.example.Form.SendPathForm;
import practice.example.Repository.CategoryRepository;
import practice.example.Repository.FavouriteRepository;
import practice.example.Repository.ItemRepository;
import practice.example.Repository.MountainRepository;
import practice.example.Repository.PostRepository;
import practice.example.Repository.UserRepository;

@Controller
public class PracticeController {
	
	// Jacksonライブラリ：JavaとJsonの変換を行う、インスタンスをDIコンテナ
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	MountainRepository MtRepository;
	
	@Autowired
	FavouriteRepository fvRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

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
	
	@GetMapping("/getDrawerMenu")
	public String getDrawerMenu() {
		return "/drawermenu";
	}
	
	@GetMapping("/getLikes")
	public String getLikes(Model model) {
		int favourites = fvRepository.findAll().size();
		model.addAttribute("likes", favourites);
		return "/likes";
	}
	
	@GetMapping("/send/Myfavourite") //いいねボタンが押されたら参照先のUserとPostに仮のidをはめてインスタンス化してFavouriteEntityにセット
	@ResponseBody	
	public List<Favourite> countUpFavourite() { //外部参照キーを2つ持たせて保存しFavouriteのレコードを全て(今回は画像が1つだから全てのレコード)を取得する
		Favourite newFavourite = new Favourite();
		newFavourite.setBoth(new User(1), new Post(2)); //本来は動的にユーザーidとポストidは取得する必要がある
		fvRepository.save(newFavourite);
		List<Favourite> favourites =fvRepository.findAll(); //ブラウザにJsonで返してオブジェクトに変換、コレクションオブジェクトの長さを取得して表示ができるか検証
		return favourites;
	}
	
	@PostMapping("/send/starRating") //星で評価を行いチェックされた星の数をformDataで送る
	@ResponseBody
	public int sendStarRating(int star) {
		Optional<Post> post =postRepository.findById(2); //本来は評価されたpostのidを動的に取得する
		post.ifPresent(con -> con.setStar(star));
		post.ifPresent(con -> postRepository.save(con));
		return star;
	}
	
	@GetMapping("/getTag")
	public String getTag(Model model) {
		List<Item> items=itemRepository.findAll();
		model.addAttribute("items",items);
		return "/tag";
	}
	
	@PostMapping("/registerTag") //1つのformでmanytomanyの２つのテーブルの値を保存する
	public String registerTag(ItemWithCategories form,Model model) { 
		Item item = new Item();
		item.setContent(form.getContent()); //片方のentityを作成してカラムに値をセット
		List<Category> categories = 
			form.getCategoryName().stream().map(i -> new Category(i)).collect(Collectors.toList()); //もう片方のentityを作成し値をセット
		categories.forEach(ct -> item.getCategories().add(ct)); //リスト型のentityの外部参照フィールドに参照先のレコード(Category)をadd
		categories.forEach(ct -> ct.getItems().add(item)); //もう片方のリスト型の外部参照フィールドにも参照先のレコード(Item)をadd
		itemRepository.save(item); //外部参照を持たせた片方のentityを保存することで両方のentityを更新できる。

		model.addAttribute("items", itemRepository.findAll());
		return "/tag";
	}
	
	@PostMapping("/registerCategory") //manytomanyでは両方のentityから参照することが可能
	public String registerCategory(ItemWithCategories form,Model model) {
		Item item = new Item();
		List<Category> categories = 
			form.getCategoryName().stream().map(i -> new Category(i)).collect(Collectors.toList());
		categories.forEach(ct -> item.getCategories().add(ct)); 
		categories.forEach(ct -> ct.getItems().add(item));
		categoryRepository.saveAll(categories);// mappedByをつけているentityからも外部参照を持つことができ、viewに情報を送れる
		
		model.addAttribute("categories", categoryRepository.findAll());
		return "/tag";
	}
	
	@GetMapping("/search")
	public String searchByCategory(@RequestParam(name="name") String name, Model model) {
		List<Category> categories =categoryRepository.findByName(name); //クリックしたカテゴリ名をもつカテゴリのレコードを検索
		model.addAttribute("categories", categories);
		return "/searchResult";
	}
}
