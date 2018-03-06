package model;

import java.io.Serializable;

public class Countries  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String countryId;
	private String countryName;
	private int regionId;

	public Countries(String countryId, String countryName, int regionId) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.regionId = regionId;
	}
	
	public Countries() {
		super();
	}

	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "Countries [countryId=" + countryId + ", countryName=" + countryName + ", regionId=" + regionId + "]";
	}

}
