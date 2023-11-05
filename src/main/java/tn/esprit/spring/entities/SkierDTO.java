package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SkierDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String city;
    // You can include additional fields if needed.
}
