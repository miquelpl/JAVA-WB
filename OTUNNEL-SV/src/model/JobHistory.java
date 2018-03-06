package model;

import java.io.Serializable;
import java.time.LocalDate;

public class JobHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	private int employeeId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String jobId;
	private int departmentId;
	
	public JobHistory() {
		super();
	}
	
	public JobHistory(int employeeId, LocalDate startDate, LocalDate endDate, String jobId, int departmentId) {
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.jobId = jobId;
		this.departmentId = departmentId;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "JobHistory [employeeId=" + employeeId + ", startDate=" + startDate + ", endDate=" + endDate + ", jobId="
				+ jobId + ", departmentId=" + departmentId + "]";
	}

}
