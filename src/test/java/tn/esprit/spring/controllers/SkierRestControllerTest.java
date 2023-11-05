package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.services.ISkierServices;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class SkierRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISkierServices skierServices;

    @InjectMocks
    private SkierRestController skierRestController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(skierRestController).build();
    }

    @Test
    public void testAddSkier() throws Exception {
        // Prepare a mock Skier
        Skier mockSkier = new Skier();
        mockSkier.setFirstName("John");
        mockSkier.setLastName("Doe");
        mockSkier.setDateOfBirth(LocalDate.of(1990, 1, 1));
        mockSkier.setCity("MountainView");

        // When the addSkier method is called, return the mock Skier
        when(skierServices.addSkier(any(Skier.class))).thenReturn(mockSkier);

        // Prepare the request JSON
        String skierJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"dateOfBirth\":\"1990-01-01\",\"city\":\"MountainView\"}";

        // Perform the test by simulating a POST request
        mockMvc.perform(post("/skier/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(skierJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.city").value("MountainView"));
    }

    @Test
    public void testDeleteById() throws Exception {
        // Assuming the delete operation does not throw an exception
        Long skierId = 1L;
        doNothing().when(skierServices).removeSkier(skierId);

        // Perform the test by simulating a DELETE request
        mockMvc.perform(delete("/skier/delete/{id-skier}", skierId))
                .andExpect(status().isOk());

        // Verify that the service method was called once with the correct parameter
        verify(skierServices).removeSkier(skierId);
    }
}
