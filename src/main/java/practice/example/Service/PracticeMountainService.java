package practice.example.Service;

import java.util.List;

import practice.example.Entity.Mountain;

public interface PracticeMountainService { //Serviceで実装するためのinterfaceを作成。可読性、テスト面で役立つ

	List<Mountain> findAll();
	
	List<Mountain> CreateOrUpdate(String name, int height);
}
