package hello;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exceptions.ResourceBadGatewayException;
import exceptions.ResourceNotFoundException;


@RestController
public class GreetingController {
	static{
		init();
	}
	static Logger log = Logger.getLogger(GreetingController.class);
	
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private AnimalRepository animalRepository;
  
    public static void init(){
    	PropertyConfigurator.configure("/log4j.properties");
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }
    
    @Value("log4j.appender.ANIMALFILE.DatePattern")
    private String teste;
    
    
    
    @RequestMapping(value = "/animal_get/{id}" ,method = RequestMethod.GET)
    public Animal getAnimal(@PathVariable(value = "id",required = true) String id){
    
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal == null){
    		log.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException(id);
    	}
    		log.info("Animal encontrado --> " + animal.getName());
    		return animal;
    }
    
    @RequestMapping(value ="/animal_post/",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal postAnimal(@Valid @RequestBody Passaro passaro) throws URISyntaxException {
    	
    	try {
    		log.info("Animal cadastrado! \n ID: " + passaro.getId() + "\n Nome: " + passaro.getName() + "\n Especie: " + passaro.getSpecies() + "\n Habitat: "+passaro.getHabitat());
    		return animalRepository.save(passaro);
    	}catch (Exception e) {
    		log.error("Erro: BadGatewayException, Banco de dados não conectado");
    		throw new ResourceBadGatewayException();
    	}
    }
    
    
    @RequestMapping(value = "/animal_put/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animal putAnimal(@PathVariable(value = "id")String id, @Valid @RequestBody Passaro passaro){
    	
    	try{
    		Animal passaroEncontrado = animalRepository.findOne(id);
			passaroEncontrado.setHabitat(passaro.getHabitat());
			passaroEncontrado.setName(passaro.getName());
			log.info("Dados atualizado com sucesso!");
			return animalRepository.save(passaroEncontrado);
    	}catch(NullPointerException ex){
    		log.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException();
    	}catch(DataAccessException ex){
    		log.error("Erro: BadGatewayException, Banco de dados não conectado");
    		throw new ResourceBadGatewayException();
    	}
    	
}
    
    
    @RequestMapping(value = "/animal_del/{id}", method = RequestMethod.DELETE)
    public Animal deleteAnimal(@PathVariable(value = "id",required = true)String id){
    	
    	Animal animal = animalRepository.findOne(id);
    	if(animal ==null){
    		log.error("Erro: NotFoundException, favor informar um ID valido");
    		throw new ResourceNotFoundException();
    	}else{
    		log.info("Animal deletado");
    		animalRepository.delete(id);
    		return null;
    	}
    }  
    
}
