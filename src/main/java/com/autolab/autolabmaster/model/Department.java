package com.autolab.autolabmaster.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.autolab.autolabmaster.model.Employee;
@Entity
@Table(name="Department")
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "dept_no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long deptId;
	@Column(name= "dept_name", nullable = false)
	private String depName;
	
	@Column(name= "dept_location", nullable = false)
	private String deptLocation;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id")
	private List<Employee> employees;
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public String getDeptLocation() {
		return deptLocation;
	}
	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
}