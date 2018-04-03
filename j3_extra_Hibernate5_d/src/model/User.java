package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import converter.LocalDateAttributeConverter;

@Entity
//@Table(name="user") default
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  //strategy=GenerationType.AUTO->default mysql 
	private int id;
	
	@Column(name="vorname",length=20)
	private String vorname;
	
	private String nachname;
	
	public LocalDate getStartdate() {
		return startdate;
	}




	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	private String email;
	
	private String passwort;
	
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate startdate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public User(String vorname, String nachname, String email, String passwort) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.passwort = passwort;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	
	
	

}
