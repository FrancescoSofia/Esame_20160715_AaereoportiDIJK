package it.polito.tdp.flight.model;

public class Event implements Comparable<Event> {
	
	
	public enum EventType { 
		FERMO,
		PARTI
		};
		
	private Passeggero p;
	private int time;
	private Airport airport;
	private EventType type;
	
	

	public Event(Passeggero p, int time,Airport airport,EventType type ) {
		super();
		this.p = p;
		this.time = time;
		this.airport=airport;
		this.type = type	;
	}

	public Passeggero getP() {
		return p;
	}

	public void setP(Passeggero p) {
		this.p = p;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(Event altro) {
		return this.time-altro.time;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

}
