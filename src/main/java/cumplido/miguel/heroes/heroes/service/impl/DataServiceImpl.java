package cumplido.miguel.heroes.heroes.service.impl;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.error.NotFoundExceptionHandler;
import cumplido.miguel.heroes.heroes.repository.HeroesRepository;
import cumplido.miguel.heroes.heroes.service.DataService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import static cumplido.miguel.heroes.heroes.constants.Constants.NOT_FOUND_HERO_BY_ID_MESSAGE;
import static cumplido.miguel.heroes.heroes.constants.Constants.NOT_FOUND_HERO_BY_TEXT_MESSAGE;


@Service
public class DataServiceImpl implements DataService {

    private final HeroesRepository heroesRepository;

    public DataServiceImpl(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }

    @Cacheable("allHeroes")
    public List<HeroesEntity> getAllHeroes() {
        return heroesRepository.findAll();
    }

    @Cacheable("heroById")
    public HeroesEntity getHeroById(int id) throws NotFoundExceptionHandler {
        return heroesRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id));
    }

    @Cacheable("heroByText")
    public List<HeroesEntity> getHeroByText(String text) throws NotFoundExceptionHandler {
        List<HeroesEntity> heroesList = heroesRepository.findByNameContainingIgnoreCase(text);
        if (heroesList.isEmpty()) {
            throw new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_TEXT_MESSAGE + text);
        }

        return heroesList;
    }

    public void updateHero(int id, String inputHeroName) throws NotFoundExceptionHandler {
        HeroesEntity foundHeroById = heroesRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id));

        foundHeroById.setName(inputHeroName);
        heroesRepository.save(foundHeroById);
    }

    public void deleteHero(int id) throws NotFoundExceptionHandler {
        heroesRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id));

        heroesRepository.deleteById(id);
    }

}
