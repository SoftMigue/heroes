package cumplido.miguel.heroes.heroes.service.impl;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.error.NotFoundExceptionHandler;
import cumplido.miguel.heroes.heroes.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataServiceImplTest {

    @InjectMocks
    private DataServiceImpl dataService;
    @Mock
    private HeroesRepository heroesRepository;

    @Test
    void getAllHeroes() throws NotFoundExceptionHandler {
        HeroesEntity hero1 = new HeroesEntity();
        hero1.setName("Superman");
        hero1.setId(1);

        HeroesEntity hero2 = new HeroesEntity();
        hero2.setName("Batman");
        hero2.setId(2);
        List<HeroesEntity> listOfFoundHeroes = Arrays.asList(hero1, hero2);

        when(heroesRepository.findAll()).thenReturn(listOfFoundHeroes);

        List<HeroesEntity> result = dataService.getAllHeroes();

        assertEquals(2, result.size());
        assertEquals("Superman", result.get(0).getName());
        assertEquals(1, result.get(0).getId());

        assertEquals("Batman", result.get(1).getName());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    void getAllHeroesNotFound() {
        when(heroesRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(NotFoundExceptionHandler.class, () ->
                dataService.getAllHeroes());
    }

    @Test
    void getHeroByIdOK() throws NotFoundExceptionHandler {
        HeroesEntity foundHero = new HeroesEntity();
        foundHero.setName("Superman");
        foundHero.setId(1);

        when(heroesRepository.findById(any())).thenReturn(Optional.of(foundHero));

        HeroesEntity result = dataService.getHeroById(1);

        assertNotNull(result);
        assertEquals(1, foundHero.getId());
        assertEquals("Superman", foundHero.getName());
    }

    @Test
    void getHeroByIdNotFound() {
        when(heroesRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundExceptionHandler.class, () ->
                dataService.getHeroById(1));
    }

    @Test
    void getHeroByTextOK() throws NotFoundExceptionHandler {
        HeroesEntity hero1WithManText = new HeroesEntity();
        hero1WithManText.setName("Superman");
        hero1WithManText.setId(1);

        HeroesEntity hero2WithManText = new HeroesEntity();
        hero2WithManText.setName("Spiderman");
        hero2WithManText.setId(2);

        List<HeroesEntity> listOfFoundHeroes_withManText = Arrays.asList(hero1WithManText, hero2WithManText);

        when(heroesRepository.findByNameContainingIgnoreCase("man")).thenReturn(listOfFoundHeroes_withManText);

        List<HeroesEntity> result = dataService.getHeroByText("man");

        assertEquals(2, result.size());
        assertEquals("Superman", result.get(0).getName());
        assertEquals(1, result.get(0).getId());

        assertEquals("Spiderman", result.get(1).getName());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    void getHeroByTextNotFound() {
        when(heroesRepository.findByNameContainingIgnoreCase("anyText")).thenReturn(Arrays.asList());

        assertThrows(NotFoundExceptionHandler.class, () ->
                dataService.getHeroByText("anyText"));
    }

    @Test
    void updateHeroOK() throws NotFoundExceptionHandler {
        HeroesEntity hero = new HeroesEntity();
        when(heroesRepository.findById(1)).thenReturn(Optional.of(hero));

        dataService.updateHero(1, "NewName");

        assertEquals("NewName", hero.getName());
        verify(heroesRepository, times(1)).save(hero);
    }

    @Test
    void updateHero_NotFound() {
        when(heroesRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundExceptionHandler.class, () ->
                dataService.updateHero(1, "NewName"));
    }

    @Test
    void deleteHero_Success() throws NotFoundExceptionHandler {
        HeroesEntity hero = new HeroesEntity();
        when(heroesRepository.findById(1)).thenReturn(Optional.of(hero));

        dataService.deleteHero(1);

        verify(heroesRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteHero_NotFound() {
        when(heroesRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundExceptionHandler.class, () ->
                dataService.deleteHero(1));
    }

}
