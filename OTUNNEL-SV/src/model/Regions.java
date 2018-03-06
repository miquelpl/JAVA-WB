package model;

import java.io.Serializable;

import javafx.beans.InvalidationListener;

public class Regions implements Serializable, Tabellen {

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
	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	

}
