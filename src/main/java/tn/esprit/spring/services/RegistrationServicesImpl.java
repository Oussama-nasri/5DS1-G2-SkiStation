package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);

        if (registration != null && course != null) {
            registration.setCourse(course);
            return registrationRepository.save(registration);
        } else {
            // Handle the case where registration or course is not found.
            return null;
        }
    }


    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse()) >= 1) {
            log.info("Sorry, you're already registered for this course of the week: " + registration.getNumWeek());
            return null;
        }

        int ageSkieur = Period.between(skier.getDateOfBirth(), LocalDate.now()).getYears();
        log.info("Age " + ageSkieur);

        TypeCourse courseType = course.getTypeCourse();

        if (courseType == TypeCourse.INDIVIDUAL) {
            log.info("Add without tests");
            return assignRegistration(registration, skier, course);
        }

        if (courseType == TypeCourse.COLLECTIVE_CHILDREN && ageSkieur < 16) {
            if (isCourseAvailable(course, registration.getNumWeek())) {
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course! Please choose another week to register.");
                return null;
            }
        }

        if (courseType != TypeCourse.COLLECTIVE_CHILDREN && ageSkieur >= 16) {
            if (isCourseAvailable(course, registration.getNumWeek())) {
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course! Please choose another week to register.");
                return null;
            }
        }

        log.info("Sorry, your age doesn't allow you to register for this course! Try to register for a " +
                (courseType == TypeCourse.COLLECTIVE_CHILDREN ? "Collective Adult Course." : "Collective Child Course."));
        return registration;
    }

    private boolean isCourseAvailable(Course course, int numWeek) {
        return registrationRepository.countByCourseAndNumWeek(course, numWeek) < 6;
    }

    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
