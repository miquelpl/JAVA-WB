package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.UserTables;

public class OracleDAO {

	private DBConnect dbconnect;
	
	public OracleDAO() {
		dbconnect = DBConnect.getInstance();
	}

	public List<UserTables> getTables() {
		
		List<UserTables> userTablesList = new ArrayList<>();
		String dml = "SELECT TABLE_NAME, TABLESPACE_NAME, STATUS FROM USER_TABLES";
        System.out.println(dml);
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement(dml)){

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserTables ut = new UserTables();
				ut.setTableName(rs.getString("TABLE_NAME"));
				ut.setTablespaceName(rs.getString("TABLESPACE_NAME"));
				ut.setStatus(rs.getString("STATUS"));
				userTablesList.add(ut);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return(userTablesList);
	}


}
