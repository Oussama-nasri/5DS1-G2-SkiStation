package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PisteDTO {
    private String namePiste;
    private Color color;
    private int length;
    private int slope;
}

