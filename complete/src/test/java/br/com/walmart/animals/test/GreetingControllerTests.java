/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.walmart.animals.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.walmart.animals.model.Passaro;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests extends TestCase {


	
	
    @Autowired
    private MockMvc mockMvc;
    
    //Criando Mock.
	//Obs você pode simular classes concretas, não apenas interfaces
    @Mock
    Passaro mockedBird;
    
    @Before
    public void iniciaTeste(){
    	MockitoAnnotations.initMocks(this);
    }
    
    //Arrumar
    @Test
    public void getAnimal() throws Exception{
    	
    			// stubbing aparece antes da execução real
    			Mockito.when(mockedBird.getName()).thenReturn("Aguia");
    			Mockito.when(mockedBird.getHabitat()).thenReturn("Montanha");
    			Mockito.when(mockedBird.getSpecies()).thenReturn("Passaro");
    			//Devolve o Valor do index
    			System.out.println("");
    			System.out.println("RESULTADO DO TESTE GetAnimal()");
    			System.out.println(" Nome --> "+mockedBird.getName()+"\n Espécie --> " + mockedBird.getSpecies() +"\n Habitat --> "+ mockedBird.getHabitat());
    	
    }
    
    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
    }

}
