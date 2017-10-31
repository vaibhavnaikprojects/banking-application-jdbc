package com.zensar.banking.beans;
import java.io.Serializable;
public class Transaction implements Serializable{
	int transId,transAmt;
	String transType;
	public Transaction() {
	}
	public Transaction( int transId,int  transAmt, String transType) {
		super();
		this.transId=transId;
		this.transAmt = transAmt;
		this.transType = transType;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(int transAmt) {
		this.transAmt = transAmt;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transAmt;
		result = prime * result + transId;
		result = prime * result
				+ ((transType == null) ? 0 : transType.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (transAmt != other.transAmt)
			return false;
		if (transId != other.transId)
			return false;
		if (transType == null) {
			if (other.transType != null)
				return false;
		} else if (!transType.equals(other.transType))
			return false;
		return true;
	}
	public String toString() {
		return "\n"+transId + "\t\t" + transAmt+ "\t\t" + transType;
	}
}
