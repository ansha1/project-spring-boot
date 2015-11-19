package com.hcl.bnsf.domain;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 2609245339982143130L;

	private String rosterId;
	
	public String getRosterId() {
		return rosterId;
	}

	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}

	private String id;

	private String name;

	private String phoneNo;

	private Integer seqNo;

	public Employee() {
		super();
	}

	public Employee(String id, String rosterId, String name, String phoneNo, Integer seqNo) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.seqNo = seqNo;
		this.rosterId = rosterId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String toString() {
		//return "Employee [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", seqNo=" + seqNo + "]";
		return null;
	}

}