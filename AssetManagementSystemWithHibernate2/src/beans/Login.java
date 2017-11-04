package beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

@Id
String adminid;
String adminpass;
String admindesignation;
public String getAdmindesignation() {
	return admindesignation;
}

public void setAdmindesignation(String admindesignation) {
	this.admindesignation = admindesignation;
}

public String getAdminid() {
	return adminid;
}

public void setAdminid(String adminid) {
	this.adminid = adminid;
}

public String getAdminpass() {
	return adminpass;
}

public void setAdminpass(String adminpass) {
	this.adminpass = adminpass;
}

}
