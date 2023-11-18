package cumplido.miguel.heroes.heroes.service;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.error.NotFoundExceptionHandler;

import java.util.List;

public interface DataService {
    List<HeroesEntity> getAllHeroes();
    HeroesEntity getHeroById(int id) throws NotFoundExceptionHandler;
    List<HeroesEntity> getHeroByText(String text) throws NotFoundExceptionHandler;
    void updateHero(int id, String inputHeroName) throws NotFoundExceptionHandler;
    void deleteHero(int id) throws NotFoundExceptionHandler;
}
