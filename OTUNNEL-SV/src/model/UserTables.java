package model;

import java.io.Serializable;

public class UserTables implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String tablespaceName;
	private String status;

	public UserTables(String tableName, String tablespaceName, String status) {
		this.tableName = tableName;
		this.tablespaceName = tablespaceName;
		this.status = status;
	}

	public UserTables() {
		super();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTablespaceName() {
		return tablespaceName;
	}

	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
