package br.com.walmart.animals.service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.walmart.animals.config.RabbitConfig;
import br.com.walmart.animals.exceptions.ResourceBadGatewayException;
import br.com.walmart.animals.exceptions.ResourceInternalServerErrorException;
import br.com.walmart.animals.exceptions.ResourceNotFoundException;
import br.com.walmart.animals.model.Animal;
import br.com.walmart.animals.model.Passaro;
import br.com.walmart.animals.repository.AnimalRepository;
import io.swagger.annotations.Api;

@Controller
@RestController
@RequestMapping(value = "/live")
@Api(value = "animals")
public class GreetingController {
	final static Logger LOGGER = Logger.getLogger(GreetingController.class);
	private RabbitTemplate rabbitTemplate;
	@Autowired
	public AnimalRepository animalRepository;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Cacheable(value = "passaro")
	public Animal getAnimal(@PathVariable(value = "id", required = true) String id) {

		Animal animal = animalRepository.findOne(id);
		if (animal == null) {
			LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
			throw new ResourceNotFoundException(id);
		}
		LOGGER.info("Animal Found --> " + animal.getName());

		return animal;

	}

	@RequestMapping(value = "/post/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void postAnimal(@Valid @RequestBody Passaro passaro, HttpServletResponse response) {
		LOGGER.info("Sending animals from Queue");
		try {
			rabbitTemplate.convertAndSend(RabbitConfig.queueName, passaro);
			response.setStatus(202);

		} catch (Exception e) {
			LOGGER.error("Erro: 500 - InternalServerErrorException, An unexpected condition was encountered");
			throw new ResourceInternalServerErrorException();
		}
	}

	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CachePut(value = "Passaro", key = "#id")
	public Animal putAnimal(@PathVariable(value = "id") String id, @Valid @RequestBody Passaro passaro) {
		LOGGER.info("Updating animals in the database");
		try {
			Animal passaroEncontrado = animalRepository.findOne(id);

			passaroEncontrado.setHabitat(passaro.getHabitat());
			passaroEncontrado.setName(passaro.getName());
			return animalRepository.save(passaroEncontrado);
		} catch (NullPointerException ex) {
			LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
			throw new ResourceNotFoundException();
		} catch (DataAccessException ex) {
			LOGGER.error("Erro: 503 - BadGatewayException, Database not connected");
			throw new ResourceBadGatewayException();
		} catch (Exception ex) {
			LOGGER.error("Erro: 500 - InternalServerErrorException, An unexpected condition was encountered");
			throw new ResourceInternalServerErrorException();
		}

	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@CacheEvict(value = "Passaro", key = "#id")
	public Animal deleteAnimal(@PathVariable(value = "id", required = true) String id) {

		Animal animal = animalRepository.findOne(id);
		if (animal == null) {
			LOGGER.error("Erro: 404 - NotFoundException, Please enter a valid ID");
			throw new ResourceNotFoundException();
		} else {
			LOGGER.info("Deleted animal!");
			animalRepository.delete(id);
			return null;
		}
	}

	@Autowired
	public void setTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

}
