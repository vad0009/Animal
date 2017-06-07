package br.com.walmart.animals.model;

public class Passaro extends Animal {

	private String species = "Passaro";

	private static final long serialVersionUID = 1L;

	public Passaro() {

	}

	public Passaro(String id, String name, String habitat, String species) {
		super(id, name, habitat);
		this.species = species;
	}

	public Passaro(String name, String habitat, String species) {
		super(name, habitat);
		this.species = species;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

}
