package model;

import java.io.Serializable;

public class MetaData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String columnName;
	private String columnClassName;
	
	public MetaData() {
		super();
	}

	public MetaData(String columnName, String columnClassName) {
		this.columnName = columnName;
		this.columnClassName = columnClassName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

}
