package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseServicesJunitTest {

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenAddCourse_thenCourseIsSaved() {
        // Arrange
        Course course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_ADULT); // Replace with the appropriate TypeCourse enum value
        course.setSupport(Support.SNOWBOARD); // Replace with the appropriate Support enum value
        course.setPrice(100.0f);
        course.setTimeSlot(60);

        when(courseRepository.save(any(Course.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Course savedCourse = courseService.addCourse(course);

        // Assert
        assertNotNull(savedCourse);
    }

    @Test
    void whenRetrieveCourseById_thenCorrectCourseIsReturned() {
        // Arrange
        Long courseId = 1L;
        Course course = new Course();
        course.setNumCourse(courseId);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // Act
        Course foundCourse = courseService.retrieveCourse(courseId);

        // Assert
        assertNotNull(foundCourse);
        assertEquals(courseId, foundCourse.getNumCourse());
    }
}
