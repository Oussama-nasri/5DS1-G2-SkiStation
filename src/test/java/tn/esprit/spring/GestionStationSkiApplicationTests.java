package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GestionStationSkiApplicationTests {

    @Autowired
    private InstructorServicesImpl instructorService;

    @Test
    void testAddInstructor() {
        // Create an Instructor object
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        // Add the instructor
        Instructor addedInstructor = instructorService.addInstructor(instructor);

        // Verify that the instructor is added successfully
        assertNotNull(addedInstructor);
    }

    @Test
    void testRetrieveAllInstructors() {
        // Retrieve all instructors from the service
        List<Instructor> instructors = instructorService.retrieveAllInstructors();

        // Verify that the list of instructors is not null
        assertNotNull(instructors);

    }
}
