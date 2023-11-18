package cumplido.miguel.heroes.heroes.constants;


public class Constants {

    private Constants() {}

    //Message for resultMessageError responses
    public static final String NOT_FOUND_HERO_BY_ID_MESSAGE = "Not found hero with id: ";
    public static final String NOT_FOUND_HERO_BY_TEXT_MESSAGE = "Not found hero with text: ";

    //Message HttpStatus Error
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    public static final String DATA_ACCESS_ERROR_MESSAGE = "Data Access Error";
    public static final String MIS_MATCH_ERROR_MESSAGE = "MistMatched input parameter. Required valid type: ";

    //Endpoints URLs names
    public static final String ROOT_URL = "/heroes";
    public static final String GET_ALL_HEROES = "/getAllHeroes";
    public static final String GET_HERO_BY_ID_URL = "/getHeroById/{id}";
    public static final String GET_HERO_BY_TEXT_URL = "/getHeroesByText/{text}";
    public static final String UPDATE_HERO_URL = "/updateHero/{id}";
    public static final String DELETE_HERO_URL = "/deleteHero/{id}";


}
