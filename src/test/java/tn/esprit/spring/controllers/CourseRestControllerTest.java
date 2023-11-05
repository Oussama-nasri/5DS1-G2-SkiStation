package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.services.ICourseServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseRestControllerTest {

    @Mock
    private ICourseServices courseServices;

    @InjectMocks
    private CourseRestController courseRestController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseRestController).build();
    }

    @Test
    void whenAddCourse_thenCreateCourse() throws Exception {
        // Arrange
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setLevel(1);
        courseDTO.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
        courseDTO.setSupport(Support.SNOWBOARD);
        courseDTO.setPrice(100.0f);
        courseDTO.setTimeSlot(2);

        Course createdCourse = new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SNOWBOARD, 100.0f, 2, null);

        given(courseServices.addCourse(any(Course.class))).willReturn(createdCourse);

        // Act & Assert
        mockMvc.perform(post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"level\": \"1\", \"typeCourse\": \"SKI\", \"support\": \"EQUIPMENT\", \"price\": \"100.0\", \"timeSlot\": \"2\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.level").value(createdCourse.getLevel()))
                .andExpect(jsonPath("$.typeCourse").value(createdCourse.getTypeCourse().toString()));

        verify(courseServices, times(1)).addCourse(any(Course.class));
    }

    @Test
    void whenGetAllCourses_thenCourseListReturned() throws Exception {
        // Arrange
        List<Course> allCourses = Arrays.asList(new Course(1L, 1, TypeCourse.COLLECTIVE_ADULT, Support.SNOWBOARD, 100.0f, 2, null));
        given(courseServices.retrieveAllCourses()).willReturn(allCourses);

        // Act & Assert
        mockMvc.perform(get("/course/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].level").value(allCourses.get(0).getLevel()))
                .andExpect(jsonPath("$[0].typeCourse").value(allCourses.get(0).getTypeCourse().toString()));

        verify(courseServices, times(1)).retrieveAllCourses();
    }
}