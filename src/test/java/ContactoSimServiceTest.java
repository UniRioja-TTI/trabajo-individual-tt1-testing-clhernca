import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.DatosSolicitud;
import servicios.ContactoSimService;

class ContactoSimServiceTest {
    private ContactoSimService servicio;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
        servicio = new ContactoSimService();
	}

	@AfterEach
	void tearDown() throws Exception {
        servicio = null;
	}

	@Test
	void testGetEntitiesNoVacio() {
        assertFalse(servicio.getEntities().isEmpty());
	}
	
    @Test
    void testGetEntitiesTieneNombre() {
        assertNotNull(servicio.getEntities().get(0).getName());
    }

    @Test
    void testSolicitarSimulationDevuelveToken() {
        Map<Integer, Integer> nums = new HashMap<>();
        nums.put(1, 5);
        DatosSolicitud sol = new DatosSolicitud(nums);
        int token = servicio.solicitarSimulation(sol);
        assertTrue(token >= 0);
    }

    @Test
    void testIsValidEntityId() {
        assertTrue(servicio.isValidEntityId());
    }

}
