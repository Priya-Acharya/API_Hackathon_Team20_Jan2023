package com.api.utilities;

public class Batch {
	
	public int batchId;
	public String batchName;
	public String batchDescription;
	public String batchStatus;
	public String creationTime;
	public String lastModTime;
	
	public Batch(int batchId, String batchName, String batchDescription, String batchStatus,
			String creationTime, String lastModTime) {
		this.batchId = batchId;
		this.batchName = batchName;
		this.batchDescription = batchDescription;
		this.batchStatus = batchStatus;
		this.creationTime = creationTime;
		this.lastModTime = lastModTime;
	}
	
	public Batch() {
		
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getBatchDescription() {
		return batchDescription;
	}
	public void setBatchDescription(String batchDescription) {
		this.batchDescription = batchDescription;
	}
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	
}


