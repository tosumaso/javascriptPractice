package practice.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;
import practice.example.TestComp.LogFilter;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class PracticesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	  
	@Autowired
	LogFilter logFilter;

	  @BeforeEach
	  void beforeEach() {
	    mockMvc =
	        MockMvcBuilders.webAppContextSetup(webApplicationContext)     // MockMVCをセットアップ
	            .addFilter(logFilter, "/*")                               // ただしfilterは手動で追加が必要
	            .build();
	  }

	  // ルート「/」のリクエストをテストするよ
	  @Test
	  void hello() throws Exception {
	    mockMvc.perform(get("/"))                                         // ルート「/」に擬似リクエスト送信
	        .andExpect(status().isOk())                                   // HttpStatus が 200:OK であること
	        .andExpect(jsonPath("$.status").value("success"))             // jsonの値が期待値通りであること
	        .andExpect(jsonPath("$.message").value("request succeeded.")) // 〃
	        .andExpect(jsonPath("$.data").value("hello"));
	  }

	  // 除算（10 ÷ 3）のリクエストをテストするよ
	  @Test
	  void divideSuccess() throws Exception { 
	    mockMvc
	        .perform(get("/divide/10/3"))                                 // 「/divide/10/3」に擬似リクエスト送信
	        .andExpect(status().isOk())                                   // HttpStatus が 200:OK であること
	        .andExpect(jsonPath("$.status").value("success"))
	        .andExpect(jsonPath("$.message").value("request succeeded."))
	        .andExpect(jsonPath("$.data").value("3.33"));                 // 10 ÷ 3 = 3.33 であること
	  }

	  // 不正なリクエスト（10 ÷ aaa）をテストするよ
	  @Test
	  void divideInvalidParameter() throws Exception {
	    mockMvc
	        .perform(get("/divide/10/aaa"))                               // 「/divide/10/aaa」に擬似リクエスト送信
	        .andExpect(status().isBadRequest())                           // HttpStatus が 400:BadRequest であること
	        .andExpect(jsonPath("$.status").value("failure"))
	        .andExpect(jsonPath("$.message").value("400エラー")); // エラーメッセージがあること
	  }

	  // ゼロ除算（10 ÷ 0）のリクエストをテストするよ
	  @Test
	  void divideZeroError() throws Exception {
	    mockMvc
	        .perform(get("/divide/10/0"))                                   // 「/divide/10/0」に擬似リクエスト送信 
	        .andExpect(status().is5xxServerError())                         // HttpStatus が 500:ServerError
	        .andExpect(jsonPath("$.status").value("failure"))
	        .andExpect(jsonPath("$.message").value("error has occurred."));
	  }

	  // 外部リソース（Qiita schema API）の取得をテストするよ
	  @Test
	  void getExternalResource() throws Exception {
	    MvcResult mvcResult =
	        mockMvc
	            .perform(get("/external"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.status").value("success"))
	            .andExpect(jsonPath("$.message").value("request succeeded."))
	            .andExpect(jsonPath("$.data").isNotEmpty())                // 空じゃないこと
	            .andReturn();
	    // 取得したレスポンスをログに出力しておくよ
	    log.info("external response : {}", mvcResult.getResponse().getContentAsString());
	  }

}
