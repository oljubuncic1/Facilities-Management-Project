package com.oljubuncic1.entities;

public class Facility {
	private int id;
	private String name;
	private String description;
	private String website;
	private String address;
	private String postal_code;
	private String category;
	private String city;
	private String country;
	private String phone;
	private String email;
	
	private static int currentID = 0;
	public static int maxID = 0;
	
	public Facility()
	{
		this.id = NextID();
	}
	
	
	public Facility(String name, String description, String website,
			String address, String postal_code, String category, String city,
			String country, String phone, String email) {
		super();
		this.id = NextID();
		this.name = name;
		this.description = description;
		this.website = website;
		this.address = address;
		this.postal_code = postal_code;
		this.category = category;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.email = email;
		
	}



	public Facility(Facility f)
	{
		this.id = f.id;
		this.name = f.name;
		this.description = f.description;
		this.website = f.website;
		this.address = f.address;
		this.postal_code = f.postal_code;
		this.category = f.category;
		this.city = f.city;
		this.country = f.country;
		this.phone = f.phone;
		this.email = f.email;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostal_code() {
		return postal_code;
	}


	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public static int NextID()
	{
		maxID = currentID + 1;
		return ++currentID;
	}
	
	public static void previousID()
	{
		currentID--;
	}
	
	public static void setcurrentID(int val)
	{
		currentID = val;
	}

}
