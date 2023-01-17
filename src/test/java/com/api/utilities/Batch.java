package com.api.utilities;

public class Batch {

	private int batchId;
	private String batchName;
	private String batchDescription;
	private String batchStatus;
	private int batchNoOfClasses;

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
	
	public String getbatchDescription() {
		return batchDescription;
	}

	public void setbatchDescription(String batchDescription) {
		this.batchDescription = batchDescription;
	}
	
	public String getbatchStatus() {
		return batchStatus;
	}

	public void setbatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	
	public int getbatchNoOfClasses() {
		return batchNoOfClasses;
	}

	public void setbatchNoOfClasses(int batchNoOfClasses) {
		this.batchNoOfClasses = batchNoOfClasses;
	}
}