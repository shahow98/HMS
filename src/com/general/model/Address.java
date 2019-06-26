package com.general.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//居民地址
public class Address {
	private String addr_id;
	private String householder_id;
	private String collective_id;
	private String province_name;
	private String city_name;
	private String district_name;
	private String town_name;
	private String village_name;
	private String expand;
	@Temporal(TemporalType.DATE)
	private Date evacuation_date;//迁出
	@Temporal(TemporalType.DATE)
	private Date immigratory_date;//迁入
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	public String getHouseholder_id() {
		return householder_id;
	}
	public void setHouseholder_id(String householder_id) {
		this.householder_id = householder_id;
	}
	public String getCollective_id() {
		return collective_id;
	}
	public void setCollective_id(String collective_id) {
		this.collective_id = collective_id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	public String getExpand() {
		return expand;
	}
	public void setExpand(String expand) {
		this.expand = expand;
	}
	public Date getEvacuation_date() {
		return evacuation_date;
	}
	public void setEvacuation_date(Date evacuation_date) {
		this.evacuation_date = evacuation_date;
	}
	public Date getImmigratory_date() {
		return immigratory_date;
	}
	public void setImmigratory_date(Date immigratory_date) {
		this.immigratory_date = immigratory_date;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return province_name+city_name+district_name+town_name+village_name+expand;
	}
}
