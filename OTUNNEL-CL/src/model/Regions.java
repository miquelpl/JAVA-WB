package model;

import java.io.Serializable;

public class Regions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int regionId;
	private String regionName;

	public Regions() {
		super();
	}
	public Regions(int regionId, String regionName) {
		this.regionId = regionId;
		this.regionName = regionName;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	@Override
	public String toString() {
		return "Regions [regionId=" + regionId + ", regionName=" + regionName + "]";
	}

	

}
