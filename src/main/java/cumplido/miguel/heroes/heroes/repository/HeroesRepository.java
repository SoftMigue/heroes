package cumplido.miguel.heroes.heroes.repository;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HeroesRepository extends JpaRepository<HeroesEntity, Integer> {

    List<HeroesEntity> findByNameContainingIgnoreCase(String text);

}
