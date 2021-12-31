package practice.example;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class CsvDataSetLoader extends AbstractDataSetLoader{ //AbstractDataSetLoaderを実装してCsvファイルから初期データを読み込めるようにする

	@Override
	protected IDataSet createDataSet(Resource resource) throws Exception {
		return new CsvDataSet(resource.getFile()); //csvファイルで書かれた初期データの情報をCsvDataSetにして返す
	}

	
}
