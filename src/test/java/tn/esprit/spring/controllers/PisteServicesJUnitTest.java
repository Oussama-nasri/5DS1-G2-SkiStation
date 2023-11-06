import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // Add this annotation to enable Spring testing
public class PisteServicesJUnitTest {

    @Autowired
    private IPisteServices pisteServices;

    @BeforeEach
    void setUp() {
        // No additional setup needed here
    }

    @Test
    void testAddPiste() {
        // Test adding a Piste
        Piste piste = new Piste();
        piste.setNamePiste("Blue Piste");
        Piste addedPiste = pisteServices.addPiste(piste);
        assertNotNull(addedPiste);
        assertEquals("Blue Piste", addedPiste.getNamePiste());
        // Add more assertions as needed
    }

    @Test
    void testRemovePiste() {
        // Test removing a Piste
        Piste piste = new Piste();
        piste.setNamePiste("Red Piste");
        Piste addedPiste = pisteServices.addPiste(piste);

        Long pisteId = addedPiste.getNumPiste();
        pisteServices.removePiste(pisteId);

        Piste retrievedPiste = pisteServices.retrievePiste(pisteId);
        assertNull(retrievedPiste);
    }

    @Test
    void testRetrieveAllPistes() {
        // Test retrieving all pistes
        List<Piste> allPistes = pisteServices.retrieveAllPistes();
        assertNotNull(allPistes);
        // Add assertions to check the retrieved pistes.
    }
}
