package br.com.walmart.animals.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.walmart.animals.model.Passaro;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreetingControllerTests.class)
@AutoConfigureMockMvc
public class GreetingControllerTests extends TestCase {
	final static Logger LOGGER = Logger.getLogger(GreetingControllerTests.class);

	// Criando Mock.
	// Obs você pode simular classes concretas, não apenas interfaces
	@Mock
	Passaro mockedBird;

	@Before
	public void iniciaTeste() {
		MockitoAnnotations.initMocks(this);
	}

	// Arrumar
	@Test
	public void getAnimal() throws Exception {

		// stubbing aparece antes da execução real
		Mockito.when(mockedBird.getName()).thenReturn("Aguia");
		Mockito.when(mockedBird.getHabitat()).thenReturn("Montanha");
		Mockito.when(mockedBird.getSpecies()).thenReturn("Passaro");
		// Devolve o Valor do index
		LOGGER.info("RESULTADO DO TESTE GetAnimal()");
		LOGGER.info(" Nome --> " + mockedBird.getName() + "\n Espécie --> " + mockedBird.getSpecies()
				+ "\n Habitat --> " + mockedBird.getHabitat());

	}
}
