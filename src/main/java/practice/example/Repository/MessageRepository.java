package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Message;

public interface MessageRepository extends JpaRepository<Message,Integer>{

	
}
