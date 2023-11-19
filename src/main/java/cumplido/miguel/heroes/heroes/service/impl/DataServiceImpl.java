package cumplido.miguel.heroes.heroes.service.impl;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.error.NotFoundExceptionHandler;
import cumplido.miguel.heroes.heroes.repository.HeroesRepository;
import cumplido.miguel.heroes.heroes.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cumplido.miguel.heroes.heroes.constants.Constants.NOT_FOUND_HERO_BY_ID_MESSAGE;
import static cumplido.miguel.heroes.heroes.constants.Constants.NOT_FOUND_HERO_BY_TEXT_MESSAGE;


@Service
public class DataServiceImpl implements DataService {

    private static final Logger log = LoggerFactory.getLogger(DataServiceImpl.class);


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
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
            log.error(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
            throw new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
        }
        return foundHeroById.get();
    }

    @Cacheable("heroByText")
    public List<HeroesEntity> getHeroByText(String text) throws NotFoundExceptionHandler {
        List<HeroesEntity> heroesList = heroesRepository.findByNameContainingIgnoreCase(text);
        if (heroesList.isEmpty()) {
            log.error(NOT_FOUND_HERO_BY_TEXT_MESSAGE + text);
            throw new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_TEXT_MESSAGE + text);
        }
        return heroesList;
    }

    public void updateHero(int id, String inputHeroName) throws NotFoundExceptionHandler {
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
            log.error(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
            throw new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
        } else {
            foundHeroById.get().setName(inputHeroName);
            heroesRepository.save(foundHeroById.get());
        }
    }

    public void deleteHero(int id) throws NotFoundExceptionHandler {
        Optional<HeroesEntity> foundHeroById = heroesRepository.findById(id);
        if (!foundHeroById.isPresent()) {
            log.error(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
            throw new NotFoundExceptionHandler(NOT_FOUND_HERO_BY_ID_MESSAGE + id);
        } else heroesRepository.deleteById(id);
    }

}
