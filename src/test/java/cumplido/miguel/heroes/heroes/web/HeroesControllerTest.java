package cumplido.miguel.heroes.heroes.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static cumplido.miguel.heroes.heroes.constants.Constants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HeroesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final int CORRECT_ID_VALUE_1 = 1;
    private static final int NOT_FOUND_ID_VALUE = 99;
    private static final String BAD_ID_VALUE = "-";
    private static final String CORRECT_TEXT_VALUE = "man";
    private static final String BAD_TEXT_VALUE = "XXX";


    @Test
    void getAllHeroesStatus200AndOKResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_ALL_HEROES))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Batman"))
                .andExpect(jsonPath("$[1].name").value("MsMarvel"))
                .andExpect(jsonPath("$[2].name").value("CapitanaMarvel"))
                .andExpect(jsonPath("$[3].name").value("Thor"))
                .andExpect(jsonPath("$[4].name").value("Ironman"));
    }

    @Test
    void getHeroByIdStatus200AndOKResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_HERO_BY_ID_URL, CORRECT_ID_VALUE_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Batman"));
    }

    @Test
    void getHeroByIdStatus404NotFoundHero() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_HERO_BY_ID_URL, NOT_FOUND_ID_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.resultMessageError").value(NOT_FOUND_HERO_BY_ID_MESSAGE + NOT_FOUND_ID_VALUE));
    }

    @Test
    void getHeroByIdStatus400WhenBadInputId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_HERO_BY_ID_URL, BAD_ID_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.resultMessageError").value( MIS_MATCH_ERROR_MESSAGE + "int"));
    }

    @Test
    void getHeroByTextOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_HERO_BY_TEXT_URL, CORRECT_TEXT_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Batman"))
                .andExpect(jsonPath("$[1].name").value("Ironman"));
    }

    @Test
    void getHeroByText404NotFoundHeroes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ROOT_URL + GET_HERO_BY_TEXT_URL, BAD_TEXT_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.resultMessageError").value(NOT_FOUND_HERO_BY_TEXT_MESSAGE + BAD_TEXT_VALUE));
    }

    @Test
    void updateHeroOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_URL + UPDATE_HERO_URL, CORRECT_ID_VALUE_1)
                        .content("flash"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateHero400WhenBadInputId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_URL + UPDATE_HERO_URL, BAD_ID_VALUE)
                        .content("flash"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.resultMessageError").value( MIS_MATCH_ERROR_MESSAGE + "int"));
    }

    @Test
    void updateHero404NotFoundHeroToUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(ROOT_URL + UPDATE_HERO_URL, NOT_FOUND_ID_VALUE)
                        .content("flash"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.resultMessageError").value(NOT_FOUND_HERO_BY_ID_MESSAGE + NOT_FOUND_ID_VALUE));
    }

    @Test
    void deleteHeroOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_URL + DELETE_HERO_URL, CORRECT_ID_VALUE_1))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteHero400WhenBadInputId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_URL + DELETE_HERO_URL, BAD_ID_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.resultMessageError").value( MIS_MATCH_ERROR_MESSAGE + "int"));
    }

    @Test
    void deleteHero404NotFoundHeroToDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(ROOT_URL + DELETE_HERO_URL, NOT_FOUND_ID_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.resultMessageError").value(NOT_FOUND_HERO_BY_ID_MESSAGE + NOT_FOUND_ID_VALUE));
    }


}