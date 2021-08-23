package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Mountain;

public interface MountainRepository extends JpaRepository<Mountain, Integer>{
	
}
