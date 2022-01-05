package practice.example;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;
import practice.example.TestComp.DemoController;
import practice.example.TestComp.DemoService;
import practice.example.TestComp.ExternalService;
import practice.example.TestComp.LogFilter;

//@SpringBootTest
@WebMvcTest(DemoController.class) //@WebMvcTest: コントローラーのテストに必要なbeanのみを読み込む それ以外のbeanはMockする必要がある
@AutoConfigureMockMvc
@Slf4j
class DemoControllerWithMockTest {

@Autowired
  private MockMvc mockMvc;

  @Autowired WebApplicationContext webApplicationContext;
  @Autowired LogFilter logFilter;

  @MockBean DemoService demoService;         // Mock化してDIコンテナに登録する
  @MockBean ExternalService externalService; //

  @BeforeEach
  void beforeEach() {
//    MockitoAnnotations.initMocks(this); deprecated?
	  MockitoAnnotations.openMocks(this);
	//本番環境と同じ環境でテストするためにサーバーをmockする
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(logFilter, "/*")
            .build();
  }

  @AfterEach
  void afterEach() {}

  @Test
  void hello() throws Exception {
    // mock
    when(demoService.hello()).thenReturn("こんにちは");      // 最初にモックの戻り値をセット
    // request execute
    mockMvc
        .perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("success"))
        .andExpect(jsonPath("$.message").value("request succeeded."))
        .andExpect(jsonPath("$.data").value("こんにちは"));  // 期待値も変更して検証
    // verify
    verify(demoService, times(1)).hello();           // モックの呼び出し回数を検証
  }

  @Test
  void divideSuccess() throws Exception {
    // mock
    when(demoService.divide(any(), any())).thenReturn(new BigDecimal("3.33")); // 引数関係なく"3.33"を返す
    // request execute
    mockMvc
        .perform(get("/divide/10/3"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("success"))
        .andExpect(jsonPath("$.message").value("request succeeded."))
        .andExpect(jsonPath("$.data").value("3.33"));
    // verify
    verify(demoService, times(1)).divide(any(), any());
  }

  @Test
  void divideInvalidParameter() throws Exception {
    // request execute
    mockMvc
        .perform(get("/divide/10/aaa"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status").value("failure"))
        .andExpect(jsonPath("$.message").value("400エラー"));
    // verify
    verify(demoService, times(0)).divide(any(), any());  // 入力エラーのためモックの呼び出しは0回を検証
  }

  @Test
  void divideZeroError() throws Exception {
    // mock
    when(demoService.divide(any(), eq(BigDecimal.ZERO)))
        .thenThrow(new ArithmeticException("/ by zero"));              // ゼロ除算を想定してエラーを再現
    // request execute
    mockMvc
        .perform(get("/divide/10/0"))
        .andExpect(status().is5xxServerError())
        .andExpect(jsonPath("$.status").value("failure"))
        .andExpect(jsonPath("$.message").value("error has occurred."));
    // verify
    verify(demoService, times(1)).divide(any(), eq(BigDecimal.ZERO));  // モックの呼び出しは1回を検証
  }

  @Test
  void getExternalResource() throws Exception {
    // mock
    when(externalService.getExternalResource())
        .thenReturn("this is mock data for internal test.");    // 外部リソースアクセスせずに文言返す
    // request execute
    MvcResult mvcResult =
        mockMvc
            .perform(get("/external"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.message").value("request succeeded."))
            .andExpect(jsonPath("$.data").value("this is mock data for internal test."))
            .andReturn();
    // 取得したレスポンスをログに出力しておくよ
    log.info("external response : {}", mvcResult.getResponse().getContentAsString());
    // verify
    verify(externalService, times(1)).getExternalResource();  // モックの呼び出しは1回を検証
  }
}
