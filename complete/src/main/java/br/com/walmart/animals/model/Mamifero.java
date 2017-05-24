package br.com.walmart.animals.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Mamifero extends Animal {
	@NotNull (message = "Este campo não é NULO")
	@NotEmpty(message = "Informe o habitat do animal no JSON")
	private String habitat;
	@NotNull (message = "Este campo não é NULO")
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
