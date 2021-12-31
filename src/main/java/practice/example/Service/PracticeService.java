package practice.example.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.example.Entity.Mountain;
import practice.example.Repository.MountainRepository;

@Service //ビジネスロジック
@Transactional //トランザクション(ひとまとまりの処理中、例外が発生すると処理開始時点の状態に戻る)
//DIされたクラスから直接メソッドを呼ぶ必要がある、デフォルトでは非検査例外(RuntimeExceptionとそれを継承したExceptionクラス)しかrollbackしない
//publicメソッドにつける
public class PracticeService implements PracticeMountainService{

	@Autowired //Repositoryをinject
	MountainRepository MtRepository;
	
	@Autowired
	Utility utility;

	@Override //Serviceのinterfaceを通すことでどういった処理を実装するか明確化でき、可読性に繋がる
	public List<Mountain> findAll() {
		
		List<Mountain> mountains = MtRepository.findAll();
		return mountains;
	}

	@Override
	public List<Mountain> CreateOrUpdate(String name, int height) {
		if (MtRepository.findByName(name) == null) {
			Mountain m = new Mountain();
			m.setName(name);
			m.setHeight(height);
			MtRepository.save(m);
		}
		return findAll();
	}

}
