package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InstructorDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfHire;
    private Set<Course> courses;
}

