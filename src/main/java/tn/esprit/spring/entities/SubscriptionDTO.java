package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Float price;
}