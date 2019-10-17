package com.wmk.porject.util.entity;

public class IHPRespHeader {
	private String respondId;
	private long timeStamp;
	private String resultCode;
	private String resultDesc;
	private String errorCode;
	private String errorInfo;
	private String requestId;
	
	public IHPRespHeader(){
		
	}
	
	public String getRespondId() {
		return respondId;
	}
	public void setRespondId(String respondId) {
		this.respondId = respondId;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
}
