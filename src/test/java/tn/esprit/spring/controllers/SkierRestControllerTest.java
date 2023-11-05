package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.services.ISkierServices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SkierRestController.class)
class SkierRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISkierServices skierServices;

    private Skier testSkier;
    private String skierJson;

    @BeforeEach
    void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse("1990-01-01", formatter);

        // Set up a Skier object
        testSkier = new Skier();
        testSkier.setFirstName("John");
        testSkier.setLastName("Doe");
        testSkier.setDateOfBirth(dob);
        testSkier.setCity("TestCity");

        // JSON String representing the SkierDTO
        skierJson = String.format(
                "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"dateOfBirth\":\"%s\",\"city\":\"%s\"}",
                testSkier.getFirstName(), testSkier.getLastName(), dob.format(formatter), testSkier.getCity()
        );

        // Define the behavior of the service to return the test Skier when save is called
        Mockito.when(skierServices.addSkier(Mockito.any(Skier.class))).thenReturn(testSkier);
    }

    @Test
    void testAddSkier() throws Exception {
        mockMvc.perform(post("/skier/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(skierJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(testSkier.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testSkier.getLastName()))
                .andExpect(jsonPath("$.dateOfBirth").value(testSkier.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.city").value(testSkier.getCity()));
    }

    // Additional tests...
}
