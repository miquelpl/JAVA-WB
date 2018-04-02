package model.hr;
// Generated 31.03.2018 17:56:29 by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Jobs generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "JOBS", schema = "HR")
public class Jobs implements java.io.Serializable {

	private String jobId;
	private String jobTitle;
	private Integer minSalary;
	private Integer maxSalary;
	private Set<Employees> employeeses = new HashSet<Employees>(0);
	private Set<JobHistory> jobHistories = new HashSet<JobHistory>(0);

	public Jobs() {
	}

	public Jobs(String jobId, String jobTitle) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
	}

	public Jobs(String jobId, String jobTitle, Integer minSalary, Integer maxSalary, Set<Employees> employeeses,
			Set<JobHistory> jobHistories) {
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employeeses = employeeses;
		this.jobHistories = jobHistories;
	}

	@Id

	@Column(name = "JOB_ID", unique = true, nullable = false, length = 10)
	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Column(name = "JOB_TITLE", nullable = false, length = 35)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "MIN_SALARY", precision = 6, scale = 0)
	public Integer getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	@Column(name = "MAX_SALARY", precision = 6, scale = 0)
	public Integer getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobs")
	public Set<Employees> getEmployeeses() {
		return this.employeeses;
	}

	public void setEmployeeses(Set<Employees> employeeses) {
		this.employeeses = employeeses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobs")
	public Set<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(Set<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

}
