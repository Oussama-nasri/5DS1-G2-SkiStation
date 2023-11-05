package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.CourseDTO;
import tn.esprit.spring.services.ICourseServices;

import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseRestController {
    
    private final ICourseServices courseServices;

    @Operation(description = "Add Course")
    @PostMapping("/add")
    public Course addCourse(@RequestBody CourseDTO courseDTO){
        Course course = new Course();
        course.setLevel(courseDTO.getLevel());
        course.setTypeCourse(courseDTO.getTypeCourse());
        course.setSupport(courseDTO.getSupport());
        course.setPrice(courseDTO.getPrice());
        course.setTimeSlot(courseDTO.getTimeSlot());

        return courseServices.addCourse(course);

    }

    @Operation(description = "Retrieve all Courses")
    @GetMapping("/all")
    public List<Course> getAllCourses(){
        return courseServices.retrieveAllCourses();
    }


    @Operation(description = "Update Course")
    @PutMapping("/update")
    public Course updateCourse(@RequestBody CourseDTO courseUpdateDTO) {
        Course course = new Course();
        course.setLevel(courseUpdateDTO.getLevel());
        course.setTypeCourse(courseUpdateDTO.getTypeCourse());
        course.setSupport(courseUpdateDTO.getSupport());
        course.setPrice(courseUpdateDTO.getPrice());
        course.setTimeSlot(courseUpdateDTO.getTimeSlot());

        return courseServices.updateCourse(course);
    }



    @Operation(description = "Retrieve Course by Id")
    @GetMapping("/get/{id-course}")
    public Course getById(@PathVariable("id-course") Long numCourse){
        return courseServices.retrieveCourse(numCourse);
    }

}
