package dev.bonilla.DAO;

import java.util.List;

import dev.bonilla.model.Event;

public interface EventDAO {
	public Event getEvent(int eid);
	public Event getEvent(String name);
	public List<Event> getallEvents();
	public boolean createEvent(Event e);
	public boolean deleteEvent(Event e);
	public boolean updateEvent(Event e);
}
