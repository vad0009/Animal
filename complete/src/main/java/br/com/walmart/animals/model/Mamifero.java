package br.com.walmart.animals.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Mamifero extends Animal {
	@NotNull (message = "This field is not NULL")
	@NotEmpty(message = "Inform the animal's habitat in JSON")
	private String habitat;
	@NotNull (message = "This field is not NULL")
	private String species = "Mamifero";
	private static final long serialVersionUID = 1L;
	
	Mamifero(){
	}
	Mamifero(String id, String name, String species, String habitat){
		super(id , name, habitat);
		this.species = species;
		
	}
	
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	
	
}
