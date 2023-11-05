package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.SkierDTO;
import tn.esprit.spring.services.ISkierServices;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SkierRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISkierServices skierServices;

    @InjectMocks
    private SkierRestController skierRestController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(skierRestController).build();
    }

    @Test
    void addSkierTest() throws Exception {
        SkierDTO skierDTO = new SkierDTO();
        skierDTO.setFirstName("John");
        skierDTO.setLastName("Doe");
        skierDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        skierDTO.setCity("City");

        Skier skier = new Skier();
        skier.setFirstName(skierDTO.getFirstName());
        skier.setLastName(skierDTO.getLastName());
        skier.setDateOfBirth(skierDTO.getDateOfBirth());
        skier.setCity(skierDTO.getCity());

        given(skierServices.addSkier(skier)).willReturn(skier);

        mockMvc.perform(post("/skier/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skierDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(skierDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(skierDTO.getLastName()));
    }

    // Similar tests would follow for the other endpoints...
}
