package dev.bonilla.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	private int eid;
	private String name;
	private String description;
	private String location;
	private int category;
	private int employeetime;
	private String eventTime;
	private String eventDate;

	public Event() {
		super();
//		this.eventDate = Date.valueOf(LocalDate.now());
//		this.eventTime = Time.valueOf(LocalTime.now()).toString();
	}

	public Event(int eid, String name, String description, String location, int category, int employeetime,
			String eventTime, String eventDate) {
		super();
		this.eid = eid;
		this.name = name;
		this.description = description;
		this.location = location;
		this.category = category;
		this.employeetime = employeetime;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
	}

	public Event(String name, String description, String location, int category, int employeetime, String eventTime,
			String eventDate) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.category = category;
		this.employeetime = employeetime;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCategory() {

		return category;
	}

	public void setCategory(int category) {
		switch (category) {
		case (100):
			this.category = 100;
			break;
		case (90):
			this.category = 90;
			break;
		case (80):
			this.category = 80;
			break;
		case (75):
			this.category = 75;
			break;
		case (60):
			this.category = 60;
			break;
		default:
			this.category = 30;
		}
	}

	public void setCategory(String category) {
		category = category.toLowerCase();
		switch (category) {
		case ("certification"):
			this.category = 100;
			break;
		case ("techtraining"):
			this.category = 90;
			break;
		case ("university"):
			this.category = 80;
			break;
		case ("prepclass"):
			this.category = 75;
			break;
		case ("seminar"):
			this.category = 60;
			break;
		default:
			this.category = 30;
		}

	}

	public int getEmployeetime() {
		return employeetime;
	}

	public void setEmployeetime(int employeetime) {
		this.employeetime = employeetime;
	}

	public String getEventtime() {
		return eventTime;
	}

	public void setEventtime(String eventtime) {
		this.eventTime = eventtime;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "Event [eid=" + eid + ", name=" + name + ", description=" + description + "]";
	}

}
