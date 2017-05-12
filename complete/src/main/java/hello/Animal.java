package hello;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Animal implements Serializable {	
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@NotEmpty(message = "Digete o ID do Animal")
	@JsonIgnore
	private String id;
	@NotNull
	@NotEmpty(message = "Informe o nome do animal no JSON")
	private String name;
	@NotNull (message = "Este campo não é NULO")
	@NotEmpty (message = "Informe o habitat do animal no JSON")
	private String habitat;
	
	public Animal(){
		this.id = UUID.randomUUID().toString();
	}
	
	public Animal(String id, String name, String habitat){
		this.id = id;
		this.name = name;
		this.habitat = habitat;
	}
	public Animal( String name, String habitat){
		this.name = name;
		this.habitat = habitat;
	}

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getHabitat() {
		return habitat;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
}
