package busan_dining.dagil.repositories;

import busan_dining.dagil.entities.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {
    public Restaurants findByID(Long id);
}
