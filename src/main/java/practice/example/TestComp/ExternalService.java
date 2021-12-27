package practice.example.TestComp;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //lombok: @NotNull,finalフィールドを初期化するコンストラクタインジェクションを定義
public class ExternalService {
	
  private static final String EXTERNAL_RESOURCE_URL = "https://qiita.com/api/v2/schema";
  private final RestTemplate restTemplate;
  
  // Qiita APIのSchemaの結果を返すよ
  public String getExternalResource() {
    ResponseEntity<String> response =
        restTemplate.exchange(EXTERNAL_RESOURCE_URL, HttpMethod.GET, null, String.class);
    return response.getBody();
  }
  
}
