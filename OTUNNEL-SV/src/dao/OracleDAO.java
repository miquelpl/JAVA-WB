package dao;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import model.Countries;
import model.Departments;
import model.Employees;
import model.JobHistory;
import model.Jobs;
import model.Locations;
import model.Regions;
import model.Tabellen;
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
        //System.out.println(dml);
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
				+ "FROM USER_TAB_COLUMNS WHERE TABLE_NAME =? ORDER BY COLUMN_ID ASC";
        //System.out.println(dml);
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

	public List<Tabellen> getRows(String table, String where) throws RemoteException {
		List<Tabellen> rows = new ArrayList<>();
        List<List<Object>> data = new ArrayList<>();
		String dml = "SELECT * FROM "+table+" "+where;
        System.out.println(dml);
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement(dml)){
			
			ResultSet rs = ps.executeQuery();
			List<String> columnNames = new ArrayList<>();
			int columnCount = rs.getMetaData().getColumnCount();
			System.out.println(columnCount);
			while (rs.next()) {
				List<Object> row = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (rows);
		// return(data);
	}

	public List<?> getRows(String table) throws RemoteException {
		List<Tabellen> rows = new ArrayList<>();
        List<List<Object>> data = new ArrayList<>();
		String dml = "SELECT * FROM "+table;
        System.out.println(dml);
		try (PreparedStatement ps = dbconnect.getConnection().prepareStatement(dml)){
			
			ResultSet rs = ps.executeQuery();

			switch(table) {
				case "COUNTRIES":
					while (rs.next()) {
						Countries country = new Countries();
						country.setCountryId(rs.getString(1));
						country.setCountryName(rs.getString(2));
						country.setRegionId(rs.getInt(3));
						rows.add(country);
					}
					break;
				case "DEPARTMENTS":
					while (rs.next()) {
						Departments department = new Departments();
						department.setDepartmentId(rs.getInt(1));
						department.setDepartmentName(rs.getString(2));
						department.setManagerId(rs.getInt(3));
						department.setLocationId(rs.getInt(4));
						rows.add(department);
					}
					break;
				case "EMPLOYEES":
					while (rs.next()) {
						Employees employee = new Employees();
						employee.setEmployeeId(rs.getInt(1));
						employee.setFirstName(rs.getString(2));
						employee.setLastName(rs.getString(3));
						employee.setEmail(rs.getString(4));
						employee.setPhoneNumber(rs.getString(5));
						employee.setHireDate(rs.getDate(6).toLocalDate());
						employee.setJobId(rs.getString(7));
						employee.setSalary(rs.getInt(8));
						employee.setCommissionPct(rs.getInt(9));
						employee.setManagerId(rs.getInt(10));
						employee.setDepartmentId(rs.getInt(11));
						rows.add(employee);
					}
					break;
				case "JOB_HISTORY":
					while (rs.next()) {
						JobHistory jobHistory = new JobHistory();
						jobHistory.setEmployeeId(rs.getInt(1));
						jobHistory.setStartDate(rs.getDate(2).toLocalDate());
						jobHistory.setEndDate(rs.getDate(3).toLocalDate());
						jobHistory.setJobId(rs.getString(4));
						jobHistory.setDepartmentId(rs.getInt(5));
						rows.add(jobHistory);
					}
					break;
				case "JOBS":
					while (rs.next()) {
						Jobs job = new Jobs();
						job.setJobId(rs.getString(1));
						job.setJobTitle(rs.getString(2));
						job.setMinSalary(rs.getInt(3));
						job.setMaxSalary(rs.getInt(4));
						rows.add(job);
					}
					break;
				case "LOCATIONS":
					while (rs.next()) {
						Locations location = new Locations();
						location.setLocationId(rs.getInt(1));
						location.setStreetAddress(rs.getString(2));
						location.setPostalCode(rs.getString(3));
						location.setCity(rs.getString(4));
						location.setStateProvince(rs.getString(5));
						location.setCountryId(rs.getString(6));
						rows.add(location);
					}
					break;
				case "REGIONS":
					while (rs.next()) {
						Regions region = new Regions();
						region.setRegionId(rs.getInt(1));
						region.setRegionName(rs.getString(2));
						rows.add(region);
					}
					break;
				default:
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return(rows);
//		return(data);
	}


}
