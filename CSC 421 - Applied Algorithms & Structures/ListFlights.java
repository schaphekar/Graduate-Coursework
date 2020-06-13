
// Importing necessary modules

import java.util.ArrayList;
import java.util.Collections;
import stdlib.*;

public class ListFlights {

	public static void main(String[] args) {
		
		// Initializing array list and data source path
		StdIn.fromFile("sampleflightdata.txt");
		ArrayList<Flight> flights = new ArrayList<>();
		
		// Loop to populate the array list
		while (!StdIn.isEmpty()) {
			String line = StdIn.readLine();
			String[] fields = line.split("\\s+");
			
			// Parse integer values
			Integer flightnum = Integer.parseInt(fields[1]);
			
			// Parse string values
			String airline = fields[0];
			String origin = fields[2];
			String destination = fields[3];
			String timedepart = fields[4];
            String timearrive = fields[5];
            
            // Populate list with values
			flights.add(new Flight(airline, flightnum, origin, destination,
					timedepart, timearrive));
		}
	
		// Sort flights based on airline
		Collections.sort(flights);
		StdOut.println("--- Flights ordered by airline ---");
		StdOut.println();
		for (Flight flight: flights) {
			StdOut.println(flight);
		}
		
		// Sort flights based on departure airport
		Collections.sort(flights, Flight.byOrigin);
		StdOut.println();
		StdOut.println("--- Flights ordered by origin ---");
		StdOut.println();
		for (Flight flight: flights) {
			StdOut.println(flight);
		}
		
		// Sort flights based on destination airport
		Collections.sort(flights, Flight.byDestination);
		StdOut.println();
		StdOut.println("--- Flights ordered by destination ---");
		StdOut.println();
		for (Flight flight: flights) {
			StdOut.println(flight);
		}
		
		
}}