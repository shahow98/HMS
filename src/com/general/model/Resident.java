package com.general.model;
//居民信息

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Resident {
	private String name;
	private String id;
	private int sex;
	private int age;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Temporal(TemporalType.DATE)
	private Date registry_date;//注册
	private String collective_id;
	private String householder_id;
	private String former_name;
	private String religion;
	private String national;//民族
	private String native_place;//籍贯
	private String birthplace;//出生地
	private float stature;//身高
	private String education;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getRegistry_date() {
		return registry_date;
	}
	public void setRegistry_date(Date registry_date) {
		this.registry_date = registry_date;
	}
	public String getCollective_id() {
		return collective_id;
	}
	public void setCollective_id(String collective_id) {
		this.collective_id = collective_id;
	}
	public String getHouseholder_id() {
		return householder_id;
	}
	public void setHouseholder_id(String householder_id) {
		this.householder_id = householder_id;
	}
	public String getFormer_name() {
		return former_name;
	}
	public void setFormer_name(String former_name) {
		this.former_name = former_name;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getNative_place() {
		return native_place;
	}
	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public float getStature() {
		return stature;
	}
	public void setStature(float stature) {
		this.stature = stature;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
