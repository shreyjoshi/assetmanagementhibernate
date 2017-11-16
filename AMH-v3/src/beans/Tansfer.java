package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tansfer {

	String fromEmp,toEmp,transferRequestDate,assetname;
	int managerid;
	@Column(columnDefinition="int default 0")
	int status;
	int allocatedid;
	public int getAllocatedid() {
		return allocatedid;
	}
	public void setAllocatedid(int allocatedid) {
		this.allocatedid = allocatedid;
	}
	@Id
	@GeneratedValue
	int transferReqId;
	public String getFromEmp() {
		return fromEmp;
	}
	public void setFromEmp(String fromEmp) {
		this.fromEmp = fromEmp;
	}
	public String getToEmp() {
		return toEmp;
	}
	public void setToEmp(String toEmp) {
		this.toEmp = toEmp;
	}
	public String getTransferRequestDate() {
		return transferRequestDate;
	}
	public void setTransferRequestDate(String transferRequestDate) {
		this.transferRequestDate = transferRequestDate;
	}
	public String getAssetname() {
		return assetname;
	}
	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTransferReqId() {
		return transferReqId;
	}
	public void setTransferReqId(int transferReqId) {
		this.transferReqId = transferReqId;
	}

}
