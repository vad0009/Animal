package br.com.walmart.animals.rabbit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.walmart.animals.model.Animal;
import br.com.walmart.animals.repository.AnimalRepository;

@Component
public class AnimalsListener {
	final static Logger LOGGER = Logger.getLogger(AnimalsListener.class);
	@Autowired
	AnimalRepository animalRepository;

	public void receiveMessage(Animal animal) {
		LOGGER.info("Registered animal");
		animalRepository.save(animal);
	}
}
