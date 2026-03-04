import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.helpers.NOPLogger;

import modelo.Destinatario;
import servicios.EnviarEmailsService;

class EnviarEmailsServiceTest {
	private EnviarEmailsService servicio;
	private Logger logger;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
        logger = NOPLogger.NOP_LOGGER;
        servicio = new EnviarEmailsService(logger);
	}

	@AfterEach
	void tearDown() throws Exception {
		servicio = null;
	}

	@Test
	void testEnviarEmailDevuelveTrue() {
        Destinatario dest = new Destinatario("Clara", "clara@test.com");
        assertTrue(servicio.enviarEmail(dest, "clara@test.com"));	
    }
	
    @Test
    void testEnviarEmailConDestinatarioNulo() {
        assertDoesNotThrow(() -> servicio.enviarEmail(null, "test@test.com"));
    }

}
