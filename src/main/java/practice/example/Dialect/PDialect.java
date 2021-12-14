package practice.example.Dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
//Processor(thymeleafで表示する内容の処理)を読み込むためにDialectをテンプレートエンジンに追加する
public class PDialect extends AbstractProcessorDialect{ 

	private static final String NAME ="Hello Dialect";
	private static final String PREFIX ="hello";
	
	protected PDialect() {
		super(NAME, PREFIX, 1000); //引数1:Dialect名, 2:Processorのprefix, 3: 優先順位
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) { //ProcessorをDialectに登録
		Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new Processor1(dialectPrefix));
		processors.add(new Processor2(dialectPrefix));
		return processors;
	}

}
