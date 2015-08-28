package com.belhard.beans;

import java.io.Serializable;
import java.util.Date;

public class SocialBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean gender;
	private String country;
	private boolean permissions;
	private boolean active;
	private String status;
	private String newsMessage;
	private String mus_Str;
	private Date dt;
	private String mainPic;
	
	public SocialBean() {
	}
	
	public String getMus_Str() {
		return mus_Str;
	}

	public void setMus_Str(String mus_Str) {
		this.mus_Str = mus_Str;
	}
	
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isPermissions() {
		return permissions;
	}

	public void setPermissions(boolean permissions) {
		this.permissions = permissions;
	}
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getMainPic() {
		return mainPic;
	}

	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
		
	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNewsMessage() {
		return newsMessage;
	}

	public void setNewsMessage(String newsMessage) {
		this.newsMessage = newsMessage;
	}

}
