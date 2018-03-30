package model;

public class Adresse {

	private String adresse;
	private String position;

	
	public Adresse() {
	}
	public Adresse(String adresse, String position) {
		this.adresse = adresse;
		this.position = position;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Adresse [adresse=" + adresse + ", position=" + position + "]";
	}
	
}
