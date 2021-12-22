package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Mountain;

//本来はドメイン層のRepositoryInterfaceでEntityを制御するためのメソッドを定義し、インフラ層のRepositoryImplで
//EntityをDBに永続化させるためのメソッドを実装する
//JpaRepositoryはRepositoryImplのような実装クラスを自動で作成し、細かな実装をスキップできる
public interface MountainRepository extends JpaRepository<Mountain, Integer>{
	
	Mountain findByName(String name);
}
