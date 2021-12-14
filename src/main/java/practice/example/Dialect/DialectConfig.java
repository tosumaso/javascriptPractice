package practice.example.Dialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class DialectConfig {
	
	@Bean
    public ClassLoaderTemplateResolver templateResolver(){ //thymeleafの書かれたファイルを検索
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");// /src/main/resourcesからのhtmlフォルダの相対パス
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
	
	@Bean
	public SpringTemplateEngine templateEngine() { //thymeleafの設定
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver()); //resolverの設定
		templateEngine.addDialect(new PDialect()); //addDialect(Dialectのインスタンス)で既存のthymeleafの機能にカスタムタグ機能を追加する
		templateEngine.addDialect(new EXDialect());
		return templateEngine;
	}
}
