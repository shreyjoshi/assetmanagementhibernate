package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class AllocatedAsset {
	@Id
	@GeneratedValue
int allocatedid;
	String emailid,assetname,dateofallocation;
public int getAllocatedid() {
		return allocatedid;
	}
	public void setAllocatedid(int allocatedid) {
		this.allocatedid = allocatedid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String email) {
		this.emailid = email;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public String getDateofallocation() {
		return dateofallocation;
	}
	@Override
	public String toString() {
		return "AllocatedAsset [allocatedid=" + allocatedid + ", emailid=" + emailid + ", assetname=" + assetname
				+ ", dateofallocation=" + dateofallocation + "]";
	}
	public void setDateofallocation(String dateofallocation) {
		this.dateofallocation = dateofallocation;
	}

}
