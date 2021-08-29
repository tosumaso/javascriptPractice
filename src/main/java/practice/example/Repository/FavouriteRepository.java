package practice.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.example.Entity.Favourite;

public interface FavouriteRepository extends JpaRepository<Favourite,Integer>{
	
}
