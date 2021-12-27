package practice.example.TestComp;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class DemoController {

	private final DemoService demoService;
	private final ExternalService externalService;

	// あいさつするよ
	@GetMapping("/")
	public CommonResponse hello() {
		String data = demoService.hello();
		return CommonResponse.builder().data(data).build();
	}

	// 割り算するよ
	@GetMapping("/divide/{num1}/{num2}")
	public CommonResponse divide(
			@PathVariable String num1,
			@PathVariable String num2) {
		BigDecimal data = demoService.divide(new BigDecimal(num1), new BigDecimal(num2));
		return CommonResponse.builder().data(data).build();
	}

	// Qiita APIのSchemaの結果を返すよ
	@GetMapping("/external")
	public CommonResponse external() {
		String data = externalService.getExternalResource();
		return CommonResponse.builder().data(data).build();
	}
}
