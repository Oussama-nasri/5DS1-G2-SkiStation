package tn.esprit.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.RegistrationDTO;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.IRegistrationServices;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationRestController.class)
public class RegistrationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRegistrationServices registrationServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddAndAssignToSkier() throws Exception {
        // Create a RegistrationDTO object to send in the request body
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setNumWeek(10);

        // Create a Registration object which will be returned by the mocked service call
        Registration registration = new Registration();
        registration.setNumWeek(registrationDTO.getNumWeek());

        // Stub the service call to return a Registration object
        given(registrationServices.addRegistrationAndAssignToSkier(any(Registration.class), eq(1L)))
                .willReturn(registration);

        // Perform the request and verify the response
        mockMvc.perform(put("/registration/addAndAssignToSkier/{numSkieur}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numWeek").value(registrationDTO.getNumWeek()));
    }



    @Test
    public void testNumWeeksCourseOfInstructorBySupport() throws Exception {
        Long instructorId = 1L;
        Support support = Support.SNOWBOARD; // Assuming Support is an enum with a value SNOWBOARD

        given(registrationServices.numWeeksCourseOfInstructorBySupport(instructorId, support))
                .willReturn(Arrays.asList(1, 2, 3));

        mockMvc.perform(get("/registration/numWeeks/{numInstructor}/{support}", instructorId, support))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0]").value(1))
                .andExpect(jsonPath("$[1]").value(2))
                .andExpect(jsonPath("$[2]").value(3));
    }
}
