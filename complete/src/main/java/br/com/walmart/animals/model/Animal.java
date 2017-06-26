package br.com.walmart.animals.model;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

<<<<<<< HEAD
public class Animal implements Serializable {
=======
import com.fasterxml.jackson.annotation.JsonIgnore;

>>>>>>> parent of 49b9ba5... Update Project

public class Animal implements Serializable {	
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@NotNull
	@NotEmpty(message = "Enter the animal's name in JSON")
	private String name;
	@NotNull (message = "This field is not NULL")
	@NotEmpty (message = "Inform the animal's habitat in JSON")
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
