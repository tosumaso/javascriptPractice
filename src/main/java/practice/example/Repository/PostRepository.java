package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{
	
}
