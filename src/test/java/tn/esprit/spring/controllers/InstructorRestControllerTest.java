package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.InstructorDTO;
import tn.esprit.spring.services.IInstructorServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class InstructorRestControllerTest {

    @InjectMocks
    private InstructorRestController instructorRestController;

    @Mock
    private IInstructorServices instructorServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addInstructor() {
        // Arrange
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setFirstName("John");
        instructorDTO.setLastName("Doe");
        instructorDTO.setDateOfHire(LocalDate.now());

        Instructor expectedInstructor = new Instructor();
        expectedInstructor.setFirstName(instructorDTO.getFirstName());
        expectedInstructor.setLastName(instructorDTO.getLastName());
        expectedInstructor.setDateOfHire(instructorDTO.getDateOfHire());

        given(instructorServices.addInstructor(any(Instructor.class))).willReturn(expectedInstructor);

        // Act
        Instructor result = instructorRestController.addInstructor(instructorDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedInstructor.getFirstName(), result.getFirstName());
        assertEquals(expectedInstructor.getLastName(), result.getLastName());
        assertEquals(expectedInstructor.getDateOfHire(), result.getDateOfHire());
    }

    @Test
    void getAllInstructors() {
        // Arrange
        List<Instructor> expectedInstructors = new ArrayList<>();
        expectedInstructors.add(new Instructor());
        expectedInstructors.add(new Instructor());

        given(instructorServices.retrieveAllInstructors()).willReturn(expectedInstructors);

        // Act
        List<Instructor> result = instructorRestController.getAllInstructors();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedInstructors, result);
    }
}
