package dao;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.UserTabColumns;
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

	public List<UserTabColumns> getTabColumns(String table) throws RemoteException {
		List<UserTabColumns> userTabColumnsList = new ArrayList<>();
		String dml = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, DATA_PRECISION, DATA_SCALE, NULLABLE, COLUMN_ID "
				+ "FROM USER_TABLES WHERE TABLE_NAME =? ORDER BY COLUMN_ID ASC";
        System.out.println(dml);
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement(dml)){
			
			ps.setString(1, table);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UserTabColumns utc = new UserTabColumns();
				utc.setTableName(rs.getString("TABLE_NAME"));
				utc.setColumnName(rs.getString("COLUMN_NAME"));
				utc.setDataType(rs.getString("DATA_TYPE"));
				utc.setDataLength(rs.getInt("DATA_LENGTH"));
				utc.setDataPrecision(rs.getInt("DATA_PRECISION"));
				utc.setDataScale(rs.getInt("DATA_SCALE"));
				utc.setNullable(rs.getString("NULLABLE"));
				utc.setColumnId(rs.getInt("COLUMN_ID"));
				userTabColumnsList.add(utc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return(userTabColumnsList);
	}


}
