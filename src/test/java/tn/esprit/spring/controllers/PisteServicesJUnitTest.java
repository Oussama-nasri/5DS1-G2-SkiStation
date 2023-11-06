import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PisteServicesJUnitTest {

    @Autowired
    private IPisteServices pisteServices;

    @BeforeEach
    void setUp() {
        // Initialize or inject your PisteServices for testing
        // You can use Spring's dependency injection to get an instance of pisteServices
        // For example, you can use the @Autowired annotation here.
    }

    @Test
    void testAddPiste() {
        Piste piste = new Piste();
        piste.setNamePiste("Blue Piste");
        Piste addedPiste = pisteServices.addPiste(piste);
        assertNotNull(addedPiste);
        assertEquals("Blue Piste", addedPiste.getNamePiste());
        // Add more assertions as needed
    }

    @Test
    void testRemovePiste() {
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
        // You can implement this test to retrieve all pistes and verify the results.
        List<Piste> allPistes = pisteServices.retrieveAllPistes();
        assertNotNull(allPistes);
        // Add assertions to check the retrieved pistes.
    }
}
