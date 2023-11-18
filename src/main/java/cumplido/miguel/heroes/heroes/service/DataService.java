package cumplido.miguel.heroes.heroes.service;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;

import java.util.List;

public interface DataService {
    List<HeroesEntity> getAllHeroes();
    HeroesEntity getHeroById(int id) ;
    List<HeroesEntity> getHeroByText(String text) ;
    void updateHero(int id, String inputHeroName) ;
    void deleteHero(int id) ;
}
