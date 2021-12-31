package practice.example;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;
import practice.example.TestComp.ExternalService;
import practice.example.TestComp.LogFilter;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class DemoControllerWithOneSideMockTest {

	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	LogFilter logFilter;

	@MockBean
	ExternalService externalService;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilter(logFilter, "/*")
				.build();
	}

	@AfterEach
	void afterEach() {

	}

	@Test
	void hello() throws Exception {
		mockMvc
				.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.message").value("request succeeded."))
				.andExpect(jsonPath("$.data").value("hello"));
	}

	@Test
	void divideSuccess() throws Exception {
		mockMvc
				.perform(get("/divide/10/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.message").value("request succeeded."))
				.andExpect(jsonPath("$.data").value("3.33"));
	}

	@Test
	void divideInvalidParameter() throws Exception {
		mockMvc
				.perform(get("/divide/10/aaa"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value("failure"))
				.andExpect(jsonPath("$.message").value("400エラー"));
	}

	@Test
	void divideZeroError() throws Exception {
		mockMvc
				.perform(get("/divide/10/0"))
				.andExpect(status().is5xxServerError())
				.andExpect(jsonPath("$.status").value("failure"))
				.andExpect(jsonPath("$.message").value("error has occurred."));
	}

	// 外部リソースの取得だけMockを使って検証するよ
	@Test
	void getExternalResource() throws Exception {
		// mock
		when(externalService.getExternalResource()).thenReturn("this is mock data for internal test.");
		// request
		MvcResult mvcResult = mockMvc
				.perform(get("/external"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.message").value("request succeeded."))
				.andExpect(jsonPath("$.data").value("this is mock data for internal test."))
				.andReturn();
		//
		log.info("external response : {}", mvcResult.getResponse().getContentAsString());
		// verify
		verify(externalService, times(1)).getExternalResource();
	}
}
