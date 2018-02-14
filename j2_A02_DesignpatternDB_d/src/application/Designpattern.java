package application;

public class Designpattern {
	
	private String name;
	private String beschreibung;
	
	public Designpattern(String name, String beschreibung) {
		this.name = name;
		this.beschreibung = beschreibung;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	@Override
	public String toString() {
		return "Designpattern [name=" + name + ", beschreibung=" + beschreibung + "]";
	}
	
	

}
