package practice.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
	List<Category> findByName(String name);
}
