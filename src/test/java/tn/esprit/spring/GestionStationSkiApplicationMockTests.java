package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GestionStationSkiApplicationMockTests {

    @InjectMocks
    private InstructorServicesImpl instructorService;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Test
    void testAddInstructorWithMock() {
        // Create an Instructor object
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        // Mock the behavior of the repository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Add the instructor
        Instructor addedInstructor = instructorService.addInstructor(instructor);

        // Verify that the instructor is added successfully
        assertEquals("John", addedInstructor.getFirstName());
        assertEquals("Doe", addedInstructor.getLastName());
    }

    @Test
    void testRetrieveAllInstructorsWithMock() {
        // Create a list of sample instructors
        List<Instructor> instructorList = new ArrayList<>();
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("John");
        instructor1.setLastName("Doe");
        instructorList.add(instructor1);

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Jane");
        instructor2.setLastName("Smith");
        instructorList.add(instructor2);

        // Mock the behavior of the repository to return the list of instructors
        when(instructorRepository.findAll()).thenReturn(instructorList);

        // Retrieve all instructors from the service
        List<Instructor> instructors = instructorService.retrieveAllInstructors();

        // Verify that the list of instructors is not null
        assertEquals(2, instructors.size());
    }
}
