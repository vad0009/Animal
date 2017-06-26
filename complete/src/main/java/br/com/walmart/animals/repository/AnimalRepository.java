package br.com.walmart.animals.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.walmart.animals.model.Animal;

@RepositoryRestResource(collectionResourceRel = "ZooDB", path = "ZooDB")

public interface AnimalRepository extends MongoRepository<Animal, String>{


}
