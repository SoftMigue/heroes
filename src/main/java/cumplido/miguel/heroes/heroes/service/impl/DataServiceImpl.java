package cumplido.miguel.heroes.heroes.service.impl;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.repository.HeroesRepository;
import cumplido.miguel.heroes.heroes.service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class DataServiceImpl implements DataService {


    private final HeroesRepository heroesRepository;

    public DataServiceImpl(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    public List<HeroesEntity> getAllHeroes() {
        return heroesRepository.findAll();
    }

    public HeroesEntity getHeroById(int id) {
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
        }
        return foundHeroById.get();
    }

    public List<HeroesEntity> getHeroByText(String text) {
        List<HeroesEntity> heroesList = heroesRepository.findByNameContainingIgnoreCase(text);
        if (heroesList.isEmpty()) {
        }
        return heroesList;
    }

    public void updateHero(int id, String inputHeroName) {
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
        } else {
            foundHeroById.get().setName(inputHeroName);
            heroesRepository.save(foundHeroById.get());
        }
    }

    public void deleteHero(int id) {
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
        } else heroesRepository.deleteById(id);
    }

}
