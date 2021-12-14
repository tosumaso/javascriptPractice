package practice.example.Dialect;

import java.util.ArrayList;
import java.util.List;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class Processor2 extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "show";
	private static final int PRECEDENCE = 10000;

	protected Processor2(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTR_NAME, true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		List<String> texts = new ArrayList<String>() {
			{
				add("A");
				add("F");
				add("Z");
			}
		};
		IModelFactory factory = context.getModelFactory();
		IModel model = factory.createModel();
		for (String text : texts) {
			model.add(factory.createOpenElementTag("div"));
			model.add(factory.createText(text));
			model.add(factory.createCloseElementTag("div"));
		}
		structureHandler.setBody(model, true);
	}

}
