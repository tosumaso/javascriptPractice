package practice.example;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import practice.example.TestComp.DemoService;

@SpringBootTest
public class DemoServiceTest {

	@Autowired
	DemoService demoService;
	
	@Test
	void hello() {
		assertEquals("hello", demoService.hello());
	}
	
	  @ParameterizedTest //Parameterテストが可能
	  @MethodSource("divideTestArgs")  // "devideTestArgs"という名前のstaticメソッドを引数のソースに使うよ
	  void divide(String b1, String b2, String strExpect, boolean hasError) {

	    BigDecimal expect = Optional.ofNullable(strExpect).map(BigDecimal::new).orElse(null);
	    BigDecimal actual = null;
	    Exception error = null;
	    // 割り算メソッド実行
	    try {
	      actual = demoService.divide(new BigDecimal(b1), new BigDecimal(b2));
	    } catch (Exception e) {
	      error = e;
	    }
	    // 期待値と検証
	    assertEquals(expect, actual);
	    // エラーが発生していないか検証
	    assertEquals(hasError, error != null);
	  }
	  
	  static List<Object[]> divideTestArgs() {
		    return List.of(
		        new Object[] {"1", "1", "1.00", false},
		        new Object[] {"0", "1", "0.00", false},
		        new Object[] {"5", "2", "2.50", false},
		        new Object[] {"10", "3", "3.33", false}, // 四捨五入（小数点第三桁切り捨て）
		        new Object[] {"11", "3", "3.67", false}, // 四捨五入（小数点第三桁切り上げ）
		        new Object[] {"1", "0", null, true}); // ゼロ除算
		  }
}
