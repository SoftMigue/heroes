package cumplido.miguel.heroes.heroes.repository;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for accessing and managing hero entities in the database.
 * This interface extends JpaRepository, providing CRUD operations for HeroesEntity.
 */
@Repository
public interface HeroesRepository extends JpaRepository<HeroesEntity, Integer> {

    /**
     * Finds a list of heroes whose names contain the specified text, ignoring case.
     *
     * @param text The text to search for in hero names.
     * @return A list of heroes matching the search criteria.
     */
    List<HeroesEntity> findByNameContainingIgnoreCase(String text);

}
