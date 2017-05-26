package br.com.walmart.animals.service;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.walmart.animals.exceptions.ResourceBadGatewayException;
import br.com.walmart.animals.exceptions.ResourceNotFoundException;
import br.com.walmart.animals.model.Animal;
import br.com.walmart.animals.model.Passaro;


@RestController
public class GreetingController {
	final static Logger LOGGER = Logger.getLogger(GreetingController.class);

    @Autowired
    private AnimalRepository animalRepository;
    
   
    @RequestMapping(value = "/animal_get/{id}" ,method = RequestMethod.GET)
    public Animal getAnimal(@PathVariable(value = "id",required = true) String id){
    	
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal == null){
    		LOGGER.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException(id);
    	}
    		LOGGER.info("Animal encontrado --> " + animal.getName());
    		return animal;
    		
    }
    
    @RequestMapping(value ="/animal_post/",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal postAnimal(@Valid @RequestBody Passaro passaro) throws URISyntaxException {
    	
    	try {
    		LOGGER.info("Animal cadastrado! \n ID: " + passaro.getId() + "\n Nome: " + passaro.getName() + "\n Especie: " + passaro.getSpecies() + "\n Habitat: "+passaro.getHabitat());
    		return animalRepository.save(passaro);
    	}catch (Exception e) {
    		LOGGER.error("Erro: BadGatewayException, Banco de dados não conectado");
    		throw new ResourceBadGatewayException();
    	}
    }
    
    
    @RequestMapping(value = "/animal_put/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal putAnimal(@PathVariable(value = "id")String id, @Valid @RequestBody Passaro passaro){
    	
    	try{
    		Animal passaroEncontrado = animalRepository.findOne(id);
			passaroEncontrado.setHabitat(passaro.getHabitat());
			passaroEncontrado.setName(passaro.getName());
			LOGGER.info("Dados atualizado com sucesso!");
			return animalRepository.save(passaroEncontrado);
    	}catch(NullPointerException ex){
    		LOGGER.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException();
    	}catch(DataAccessException ex){
    		LOGGER.error("Erro: BadGatewayException, Banco de dados não conectado");
    		throw new ResourceBadGatewayException();
    	}
    	
}
    
    
    @RequestMapping(value = "/animal_del/{id}", method = RequestMethod.DELETE)
    public Animal deleteAnimal(@PathVariable(value = "id",required = true)String id){
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal ==null){
    		LOGGER.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException();
    	}else{
    		LOGGER.info("Animal deletado");
    		animalRepository.delete(id);
    		return null;
    	}
    }  
    
}
