package br.com.walmart.animals.service;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.walmart.animals.exceptions.ResourceBadGatewayException;
import br.com.walmart.animals.exceptions.ResourceNotFoundException;
import br.com.walmart.animals.model.Animal;
import br.com.walmart.animals.model.Passaro;
import br.com.walmart.animals.repository.AnimalRepository;



@Controller
public class GreetingController {
	final static Logger LOGGER = Logger.getLogger(GreetingController.class);

    @Autowired
    public AnimalRepository animalRepository;
    
    
   
    @RequestMapping(value = "/animal_get/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value="Passaro")
    public Animal getAnimal(@PathVariable(value = "id",required = true) String id){     
    	
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal == null){
    		LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
    		throw new ResourceNotFoundException(id);
    	}
    		LOGGER.info("Animal Found --> " + animal.getName());
    		return animal;
    		
    }
    
    @RequestMapping(value ="/animal_post/",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal postAnimal(@Valid @RequestBody Passaro passaro) throws URISyntaxException {
    	
    	try {
    		LOGGER.info("Registered animal!!"+ "\n ID: " + passaro.getId() + "\n Name: " + passaro.getName() + "\n Species: " + passaro.getSpecies() + "\n Habitat: "+passaro.getHabitat());
    		return animalRepository.save(passaro);
    	}catch (Exception e) {
    		LOGGER.error("Erro: 503 - BadGatewayException, Database not connected");
    		throw new ResourceBadGatewayException();
    	}
    }
    
    
    @RequestMapping(value = "/animal_put/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CachePut(value="Passaro", key="#id")
    public Animal putAnimal(@PathVariable(value = "id")String id, @Valid @RequestBody Passaro passaro){
    	
    	try{
    		Animal passaroEncontrado = animalRepository.findOne(id);
			passaroEncontrado.setHabitat(passaro.getHabitat());
			passaroEncontrado.setName(passaro.getName());
			LOGGER.info("Data updated successfully!");
			return animalRepository.save(passaroEncontrado);
    	}catch(NullPointerException ex){
    		LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
    		throw new ResourceNotFoundException();
    	}catch(DataAccessException ex){
    		LOGGER.error("Erro: 503 - BadGatewayException, Database not connected");
    		throw new ResourceBadGatewayException();
    	}
    	
}
    
    
    @RequestMapping(value = "/animal_del/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @CacheEvict(value = "Passaro", key = "#id")
    public Animal deleteAnimal(@PathVariable(value = "id",required = true)String id){
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal ==null){
    		LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
    		throw new ResourceNotFoundException();
    	}else{
    		LOGGER.info("Deleted animal!");
    		animalRepository.delete(id);
    		return null;
    	}
    }  
    
}
