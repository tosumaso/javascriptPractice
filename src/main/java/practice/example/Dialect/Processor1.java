package practice.example.Dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

//thymeleafの自作タグで処理する内容
public class Processor1 extends AbstractAttributeTagProcessor{ 
	private static final String ATTR_NAME = "sayto";
    private static final int PRECEDENCE = 10000;


    public Processor1(final String dialectPrefix) { //引数 1:processorの対象,2:thymeleafの接頭辞,3:独自タグを作る場合のタグ名,4:独自タグにprefixを適応するか,5:独自属性を作る場合の属性名,6:prefixを属性として使うか,7:優先度,8:要素の属性を削除するか
        super(TemplateMode.HTML,dialectPrefix,null,false,ATTR_NAME,true,PRECEDENCE,true);
    }


    protected void doProcess( //引数 1:コンテキス, 2:thymeleafの属性を記載したタグ全体の情報, 3:属性、prefix情報, 4:thymeleafに記載した属性値情報, 5:thymeleafの書かれたタグを編集するObject
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {
    	//tag.getAttributeValue("class"); 指定した属性の値を取得 or null
    	//structureHandler.setAttribute("属性","属性値"); 属性をセット
    	IModelFactory modelFactory = context.getModelFactory(); //IModelFactory IModelの作成をするインターフェース
        IModel model = modelFactory.createModel(); //IModel HTMLタグ(開始、終了タグ、ボディなど)をコレクションで持つオブジェクト
        model.add(modelFactory.createOpenElementTag("div", "class", "userName")); //IModelに作成した開始タグ、body,終了タグを追加する
        model.add(modelFactory.createText("SSString"));
        model.add(modelFactory.createCloseElementTag("div"));
        structureHandler.setBody(model, false); //structureHandler.seBody(model or "text",boolean); IModelに登録したhtml要素 or テキストを処理結果としてタグのbodyに描画する
    }
    
}
