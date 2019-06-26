package com.general.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//双亲
public class Parent {
	private String self_id;
	private String father_id;
	private String mother_id;
	private String spouse_id;
	@Temporal(TemporalType.DATE)
	private Date update_date;
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getSelf_id() {
		return self_id;
	}
	public void setSelf_id(String self_id) {
		this.self_id = self_id;
	}
	public String getFather_id() {
		return father_id;
	}
	public void setFather_id(String father_id) {
		this.father_id = father_id;
	}
	public String getMother_id() {
		return mother_id;
	}
	public void setMother_id(String mother_id) {
		this.mother_id = mother_id;
	}
	public String getSpouse_id() {
		return spouse_id;
	}
	public void setSpouse_id(String spouse_id) {
		this.spouse_id = spouse_id;
	}
}
