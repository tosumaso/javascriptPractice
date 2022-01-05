package practice.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import lombok.extern.slf4j.Slf4j;
import practice.example.Entity.Mountain;
import practice.example.Entity.User;
import practice.example.Repository.UserRepository;
import practice.example.Service.PracticeMountainService;


@Slf4j
@SpringBootTest
//@TestPropertySource(locations="classpath:test.properties") //Test時に読み込む設定ファイルを"src/test/resources"からの相対パスで明示的に指定
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class) //DbUnitの設定、dataSetLoader = DBのデータ定義ファイルの種類を指定(csvで読み込むために"AbstractDataSetLoader"を継承したクラスを作成)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class
}) //Test中に発生するListener "DependencyInjectionTestExecutionListener": DI機能の提供, "TransactionDbUnitTestExecutionListener": Transaction機能の提供
@Transactional
public class MountainServiceTest {

	@Autowired
	private PracticeMountainService mountainService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DatabaseSetup("/testdata/init-data") //Testメソッドを実行する前に"src/test/resources"下のパスにある初期データを投入
	@ExpectedDatabase(value="/testdata/init-data", assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	//事後データの検証を行う value: 事後データファイルのパス, assertionMode: 事後データで検証対象になるカラム、テーブル、順番の有無
	//検査対象のcsvファイルとtable-ordering.txt(テーブルを読み込む順番を指定したファイル)は２つで１つ
	void findAllMountains() {
		List<Mountain> expected = new ArrayList<Mountain>(); //手動でレコードを作成
		expected.add(new Mountain(1,"富士山",3776));
		expected.add(new Mountain(2,"八ヶ岳",2000));
		
		List<Mountain> actual = mountainService.findAll(); //h2の初期データからレコードを取得
		assertTrue(expected.size() == actual.size()); //Assertionsでレコードを比較
		for (int i=0; i < expected.size(); i++) {
			assertEquals(expected.get(i).getName(), actual.get(i).getName()); 
		}
	}
	@Test
	@DatabaseSetup("/testdata/init-data")
	@ExpectedDatabase(value="/testdata/init-data", assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	void getUserPost() {
		User expected = new User();
		expected.setName("テストユーザー1");
		expected.setPassword("あいうえお");
		User actual = userRepository.findById(1).get();
		assertEquals(expected.getName(),actual.getName());
	}
	
	
}
