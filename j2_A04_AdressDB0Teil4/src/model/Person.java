package model;

import java.io.Serializable;

public class Person implements Serializable{
	
	private int id =-1;
	private String vorname;
	private String nachname;
	private String ort;
	private String plz;
	private String strasse;
	private String telefon;
	private String mobil;
	private String email;
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(String vorname, String nachname, String ort, String plz, String strasse, String telefon, String mobil,
			String email) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.ort = ort;
		this.plz = plz;
		this.strasse = strasse;
		this.telefon = telefon;
		this.mobil = mobil;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", ort=" + ort + ", plz=" + plz
				+ ", strasse=" + strasse + ", telefon=" + telefon + ", mobil=" + mobil + ", email=" + email + "]";
	}

	
}
