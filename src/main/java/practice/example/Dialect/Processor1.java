package practice.example.Dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

public class Processor1 extends AbstractAttributeTagProcessor{
	private static final String ATTR_NAME = "sayto";
    private static final int PRECEDENCE = 10000;


    public Processor1(final String dialectPrefix) {
        super(TemplateMode.HTML,dialectPrefix,null,false,ATTR_NAME,true,PRECEDENCE,true);      
    }


    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        structureHandler.setBody(
                "Hello, " + HtmlEscape.escapeHtml5(attributeValue) + "!", false);

    }
}
