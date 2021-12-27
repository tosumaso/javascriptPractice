package practice.example.TestComp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

	public String hello() {
		return "hello";
	}
	
	public BigDecimal divide(BigDecimal a, BigDecimal b) {
		return a.divide(b,2,RoundingMode.HALF_UP);
	}
}
