package com.api.utilities;

public class Program {
	
	public int programId;
	public String programName;
	public String programDescription;
	public String programStatus;
	public String creationTime;
	public String lastModTime;
	
	public Program(int programId, String programName, String programDescription, String programStatus,
			String creationTime, String lastModTime) {
		this.programId = programId;
		this.programName = programName;
		this.programDescription = programDescription;
		this.programStatus = programStatus;
		this.creationTime = creationTime;
		this.lastModTime = lastModTime;
	}
	
	public Program() {
		
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
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
