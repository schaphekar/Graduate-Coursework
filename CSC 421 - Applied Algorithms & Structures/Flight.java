
// Importing necessary modules

package csc402examsolutions;

import java.util.Comparator;

public class Flight implements Comparable<Flight> {
	
	// Variables for flight data
	private Integer flightnum;
	private String airline;
	private String origin;
	private String destination;
	private String timedepart;
	private String timearrive;
	
	// Comparators for origin/destination airports
	public static final Comparator<Flight> byOrigin = new ByOrigin();
	public static final Comparator<Flight> byDestination = new ByDestination();
	
	
	public Flight(String airline, Integer flightnum, String origin, String destination,
			String timedepart, String timearrive) {
		
		this.airline = airline;
		this.flightnum = flightnum;
		this.origin = origin;
		this.destination = destination;
		this.timedepart = timedepart;
		this.timearrive = timearrive;
	}
	
	// Accessor methods for retrieving the value of each instance variable
	public String airline() {return airline;}
	public Integer flightnum() {return flightnum;}
	public String origin() {return origin;}
	public String destination() {return destination;}
	public String timedepart() {return timedepart;}
    public String timearrive() {return timearrive;}
	
    // Method to produce string of information
	public String toString() {
		return "\t" + airline + flightnum + " , " + origin + " , " + destination
				+ " , " + timedepart + " , " + timearrive;
	}
	
	// Method that compares Flight objects
	public int compareTo(Flight that) {
		return this.airline.compareTo(that.airline);
	}
	
	private static class ByOrigin implements Comparator<Flight> {
		public int compare(Flight a, Flight b) {
			return a.origin.compareTo(b.origin);
		}}
	
	private static class ByDestination implements Comparator<Flight> {
		public int compare(Flight a, Flight b) {
			return a.destination.compareTo(b.destination);
		}}
		
	}

