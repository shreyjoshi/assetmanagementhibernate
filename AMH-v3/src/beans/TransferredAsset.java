package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TransferredAsset {

	String fromEmp,toEmp,transferredDate,assetname;
	int managerid,transferReqId;
	@Id
	@GeneratedValue
	int transferId;
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
	public String getTransferredDate() {
		return transferredDate;
	}
	public void setTransferredDate(String transferredDate) {
		this.transferredDate = transferredDate;
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
	public int getTransferReqId() {
		return transferReqId;
	}
	public void setTransferReqId(int transferReqId) {
		this.transferReqId = transferReqId;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	
	
}
