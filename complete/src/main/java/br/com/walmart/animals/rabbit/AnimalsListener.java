package br.com.walmart.animals.rabbit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.walmart.animals.model.Animal;
import br.com.walmart.animals.repository.AnimalRepository;

/*
 * O Receiver é um POJO(Plain Old Java Object) simples que define um método para receber mensagens.
 * Quando você o registra para receber mensagens, você pode nomeá-lo qualquer
 * coisa que desejar.
 */
@Component
public class AnimalsListener {
	final static Logger LOGGER = Logger.getLogger(AnimalsListener.class);
	
	@Autowired
	AnimalRepository animalRepository;
	
	public void receiveMessage(Animal animal) {
		animalRepository.save(animal);
	}
}
