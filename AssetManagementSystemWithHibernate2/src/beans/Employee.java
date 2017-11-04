package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Employee {
	
String name,mobile,password,doj,designation;
@Column(columnDefinition="int default 0")
int status;
int managerid;
@Column(unique = true)
String email;
@Id
@GeneratedValue
int eid;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getEid() {
	return eid;
}
public void setEid(int eid) {
	this.eid = eid;
}
public int getManagerid() {
	return managerid;
}
public void setManagerid(int managerid) {
	this.managerid = managerid;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDoj() {
	return doj;
}
public void setDoj(String doj) {
	this.doj = doj;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
@Override
public String toString() {
	return "Employee [name=" + name + ", mobile=" + mobile + ", password=" + password + ", doj=" + doj
			+ ", designation=" + designation + ", status=" + status + ", managerid=" + managerid + ", email=" + email
			+ ", eid=" + eid + "]";
}


}
