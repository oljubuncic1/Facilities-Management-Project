package com.oljubuncic.model;

// Generated 03-Mar-2015 23:19:13 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FacilityAddress generated by hbm2java
 */
@Entity
@Table(name = "facility_address")
public class FacilityAddress implements java.io.Serializable {

	private int id;
	private Address address;
	private Facility facility;

	public FacilityAddress() {
	}

	public FacilityAddress(int id, Address address, Facility facility) {
		this.id = id;
		this.address = address;
		this.facility = facility;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", nullable = false)
	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

}
