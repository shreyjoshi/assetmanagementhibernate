package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Asset {
	@Override
	public String toString() {
		return "Asset [emailid=" + emailid + ", assetname=" + assetname + ", requestdate=" + requestdate
				+ ", requeststatus=" + requeststatus + ", assetrequestid=" + assetrequestid + "]";
	}
	String emailid,assetname,requestdate;
	@Column(columnDefinition="int default 0")
	int requeststatus;
	@Id
	@GeneratedValue
	int assetrequestid;
	public String getemailid() {
		return emailid;
	}
	public void setemailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public int getRequeststatus() {
		return requeststatus;
	}
	public void setRequeststatus(int requeststatus) {
		this.requeststatus = requeststatus;
	}
	public int getAssetid() {
		return getAssetrequestid();
	}
	public int getAssetrequestid() {
		return assetrequestid;
	}
	public void setAssetid(int assetid) {
		setAssetrequestid(assetid);
	}
	public void setAssetrequestid(int assetid) {
		this.assetrequestid = assetid;
	}
	

}
