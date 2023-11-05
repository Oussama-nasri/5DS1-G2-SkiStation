package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.PisteDTO;
import tn.esprit.spring.services.IPisteServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PisteRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPisteServices pisteServices;

    @InjectMocks
    private PisteRestController pisteRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pisteRestController).build();
    }

    @Test
    void addPiste() throws Exception {
        PisteDTO pisteDTO = new PisteDTO();
        pisteDTO.setNamePiste("Blue Piste");
        // ... other properties

        Piste piste = new Piste();
        piste.setNamePiste(pisteDTO.getNamePiste());
        // ... other properties

        given(pisteServices.addPiste(any(Piste.class))).willReturn(piste);

        mockMvc.perform(post("/piste/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pisteDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPistes() throws Exception {
        Piste piste1 = new Piste(); // mock piste with some test data
        Piste piste2 = new Piste(); // mock piste with some test data
        List<Piste> allPistes = Arrays.asList(piste1, piste2);

        given(pisteServices.retrieveAllPistes()).willReturn(allPistes);

        mockMvc.perform(get("/piste/all"))
                .andExpect(status().isOk());
    }
}
