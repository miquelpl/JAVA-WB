package model;

import java.io.Serializable;

import javafx.beans.InvalidationListener;

public class Locations implements Serializable, Tabellen {

	private static final long serialVersionUID = 1L;

	private int locationId;
	private String streetAddress;
	private String postalCode;
	private String city;
	private String stateProvince;
	private String countryId;
	
	public Locations() {
		super();
	}
	public Locations(int locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
		this.locationId = locationId;
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.stateProvince = stateProvince;
		this.countryId = countryId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return "Locations [locationId=" + locationId + ", streetAddress=" + streetAddress + ", postalCode=" + postalCode
				+ ", city=" + city + ", stateProvince=" + stateProvince + ", countryId=" + countryId + "]";
	}
	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	
}
