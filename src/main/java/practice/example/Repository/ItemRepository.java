package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Item;

public interface ItemRepository extends JpaRepository<Item,Integer>{
	
}
