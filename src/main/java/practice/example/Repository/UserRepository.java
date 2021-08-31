package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
