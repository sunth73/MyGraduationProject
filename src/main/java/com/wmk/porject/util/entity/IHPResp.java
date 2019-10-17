package com.wmk.porject.util.entity;

public class IHPResp {
	private IHPRespHeader header;
	private Object body;
	
	public IHPResp(){
		header = new IHPRespHeader();
		body = new Object();		
	}
	
	public IHPRespHeader getHeader() {
		return header;
	}
	public void setHeader(IHPRespHeader header) {
		this.header = header;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	
}
