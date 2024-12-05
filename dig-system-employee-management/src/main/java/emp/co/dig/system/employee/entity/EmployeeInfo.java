package emp.co.dig.system.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(nullable = false) 
	private String name;

	@Column(nullable = false, unique = true) 
	private String email;

	@ManyToOne @JoinColumn(name = "department_id") 
	private DepartmentInfo department;

	@ManyToOne @JoinColumn(name = "role_id") 
	private RoleInfo role;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DepartmentInfo getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentInfo department) {
		this.department = department;
	}

	public RoleInfo getRole() {
		return role;
	}

	public void setRole(RoleInfo role) {
		this.role = role;
	}
	
	
}
