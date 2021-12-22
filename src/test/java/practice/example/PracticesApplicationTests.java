package practice.example;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PracticesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	//	@Test
	//	void contextLoads() throws Exception{
	//		this.mockMvc.perform(get("/hello/init")).andDo(print())
	//        .andExpect(status().isOk());
	//	}

	//    @Test
	//    void modelLoading() throws Exception{
	//    	this.mockMvc.perform(get("/hello/model")).andExpect(model().attribute("message","hello"));
	//    }

	//    @Test
	//    void trueEntity() throws Exception{
	//    	this.mockMvc.perform(get("/hello/model")).andDo(print())
	//    		.andExpect(model()
	//    			.attribute("user", hasProperty(
	//    					"userName", is("test0"))));
	//    }

	@Test
	void ListEntity() throws Exception {
		this.mockMvc.perform(get("/hello/model")).andExpect(
				model().attribute("dbForm", hasProperty(
						"userList", hasItem(
								hasProperty("userName", is("test1"))
								))));
	}

}
