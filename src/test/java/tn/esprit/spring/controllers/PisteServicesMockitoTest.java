package tn.esprit.spring.controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.IPisteServices;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class PisteServicesMockitoTest {

    @InjectMocks
    private IPisteServices pisteServices;

    @Mock
    private IPisteRepository pisteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPisteWithMocking() {
        Piste piste = new Piste();
        piste.setNamePiste("Green Piste");

        when(pisteRepository.save(piste)).thenReturn(piste);

        Piste addedPiste = pisteServices.addPiste(piste);

        assertNotNull(addedPiste);
        assertEquals("Green Piste", addedPiste.getNamePiste());
        // Add more assertions as needed
    }

    @Test
    void testRemovePisteWithMocking() {
        Long pisteId = 1L;  // Replace with an actual ID
        Piste piste = new Piste();
        piste.setNumPiste(pisteId);

        when(pisteRepository.existsById(pisteId)).thenReturn(true);
        Mockito.doNothing().when(pisteRepository).deleteById(pisteId);

        pisteServices.removePiste(pisteId);

        Piste retrievedPiste = pisteServices.retrievePiste(pisteId);
        assertNull(retrievedPiste);
    }

    @Test
    void testRetrieveAllPistesWithMocking() {
        List<Piste> mockPistes = new ArrayList<>();
        mockPistes.add(new Piste());
        mockPistes.add(new Piste());

        when(pisteRepository.findAll()).thenReturn(mockPistes);

        List<Piste> allPistes = pisteServices.retrieveAllPistes();
        assertNotNull(allPistes);
        assertEquals(2, allPistes.size());
        // Add more assertions to check the retrieved pistes.
    }
}
