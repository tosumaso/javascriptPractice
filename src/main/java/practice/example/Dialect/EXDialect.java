package practice.example.Dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

//Expression Objectを読み込むためにDialectをテンプレートエンジンに登録する
public class EXDialect implements IExpressionObjectDialect{

	private Set<String> names = new HashSet<String>() {
		{
			add("doubleString");
		}
	};
	
	@Override
	public String getName() {
		return "returnDouble()";
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		
		return new IExpressionObjectFactory() { //IExpressionObjectFactoryを実装した無名クラスを作成し、オーバーライドしている

			@Override
			public Set<String> getAllExpressionObjectNames() {
				return names;
			}

			@Override
			public Object buildObject(IExpressionContext context, String expressionObjectName) {
				if ("doubleString".equals(expressionObjectName)) {
					return new ExpressionObject1();
				}
				return null;
			}

			@Override //ExpressionObjectをキャッシュするかどうか。返す値が状態により異なる場合はfalse
			public boolean isCacheable(String expressionObjectName) {
				return true;
			}
			
		};
	}

}
