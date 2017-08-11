package com.simpleprogrammer;

import java.util.Date;

public class UserHistory {
	
	private int id;
	private User user;
	
	public UserHistory(){}
	
	private Date entrytime;
	private String entry;
	public Date getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public UserHistory(Date entrytime, String entry) {
		super();
		this.entrytime = entrytime;
		this.entry = entry;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	
	/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
		result = prime * result + ((entrytime == null) ? 0 : entrytime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHistory other = (UserHistory) obj;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		if (entrytime == null) {
			if (other.entrytime != null)
				return false;
		} else if (!entrytime.equals(other.entrytime))
			return false;
		return true;
	}*/
	
	

}
