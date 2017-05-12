package hello;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ZooDB", path = "ZooDB")
//@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT,reason = "Error connecting to Database")
public interface AnimalRepository extends MongoRepository<Animal, String>{


}
