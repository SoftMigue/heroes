package cumplido.miguel.heroes.heroes.web;

import cumplido.miguel.heroes.heroes.entity.HeroesEntity;
import cumplido.miguel.heroes.heroes.error.NotFoundExceptionHandler;
import cumplido.miguel.heroes.heroes.service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cumplido.miguel.heroes.heroes.constants.Constants.*;


@RestController
@Validated
@RequestMapping(ROOT_URL)
public class HeroesController {

    private final DataService dataService;

    public HeroesController(DataService dataService) {
        this.dataService = dataService;
    }


    @GetMapping(GET_ALL_HEROES)
    public List<HeroesEntity> getAllHeroes() {
        return dataService.getAllHeroes();
    }

    @GetMapping(GET_HERO_BY_ID_URL)
    public HeroesEntity getHeroById(@PathVariable int id) throws NotFoundExceptionHandler{
        return dataService.getHeroById(id);
    }

    @GetMapping(GET_HERO_BY_TEXT_URL)
    public List<HeroesEntity> getHeroesByText(@PathVariable String text) throws NotFoundExceptionHandler {
        return dataService.getHeroByText(text);
    }

    @PutMapping(UPDATE_HERO_URL)
    public ResponseEntity<Void> updateHero(@PathVariable int id, @RequestBody String inputHeroName) throws NotFoundExceptionHandler {
        dataService.updateHero(id, inputHeroName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(DELETE_HERO_URL)
    public ResponseEntity<Void> deleteHero(@PathVariable int id) throws NotFoundExceptionHandler {
        dataService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }

}
