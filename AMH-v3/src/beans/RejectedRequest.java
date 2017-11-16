package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RejectedRequest {
	@Id
	@GeneratedValue
int rejectedrequest;
	
int assetrequestid;

String emailid,assetname,dateofrejection,rejectedby;
public int getRejectedrequest() {
	return rejectedrequest;
}
public void setRejectedrequest(int rejectedrequest) {
	this.rejectedrequest = rejectedrequest;
}
public int getAssetrequestid() {
	return assetrequestid;
}
public void setAssetrequestid(int assetrequestid) {
	this.assetrequestid = assetrequestid;
}
public String getEmailid() {
	return emailid;
}
public void setEmailid(String emailid) {
	this.emailid = emailid;
}
public String getAssetname() {
	return assetname;
}
public void setAssetname(String assetname) {
	this.assetname = assetname;
}
public String getDateofrejection() {
	return dateofrejection;
}
public void setDateofrejection(String dateofrejection) {
	this.dateofrejection = dateofrejection;
}
public String getRejectedby() {
	return rejectedby;
}
public void setRejectedby(String rejectedby) {
	this.rejectedby = rejectedby;
}
}
