package it.polito.tdp.flight.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.flight.model.Airline;
import it.polito.tdp.flight.model.Airport;
import it.polito.tdp.flight.model.Route;

public class FlightDAO {

	public List<Airline> getAllAirlines() {
		String sql = "SELECT * FROM airline";
		List<Airline> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Airline(res.getInt("Airline_ID"), res.getString("Name"), res.getString("Alias"),
						res.getString("IATA"), res.getString("ICAO"), res.getString("Callsign"),
						res.getString("Country"), res.getString("Active")));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Route> getAllRoutes(Map<Integer, Airport> airportMap) {
		String sql = "SELECT * FROM route";
		List<Route> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Route(res.getString("Airline"), res.getInt("Airline_ID"),airportMap.get(res.getInt("Source_airport_ID")),
						res.getInt("Source_airport_ID"), airportMap.get(res.getInt("Destination_airport_ID")),
						res.getInt("Destination_airport_ID"), res.getString("Codeshare"), res.getInt("Stops"),
						res.getString("Equipment")));
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Airport> getAllAirports(Map<Integer, Airport> airportMap) {
		String sql = "SELECT * FROM airport";
		List<Airport> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airport airport = new Airport(res.getInt("Airport_ID"), res.getString("name"), res.getString("city"),
						res.getString("country"), res.getString("IATA_FAA"), res.getString("ICAO"),
						 new LatLng(res.getDouble("Latitude"), res.getDouble("Longitude")), res.getFloat("timezone"),
						res.getString("dst"), res.getString("tz"));
				list.add(airport);
				airportMap.put(airport.getAirportId(), airport);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public Airport getFiumicino(Map<Integer, Airport> airportMap) {
		String sql = "SELECT * FROM airport a WHERE a.Name= Fiumicino";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			Airport fiumicino = null;
			while (res.next()) {
				 fiumicino = airportMap.get(res.getInt("Airport_ID"));
				 System.out.println(fiumicino);
			}
			conn.close();
			return fiumicino;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public List<Airport> getAllAirportsConRotte(Map<Integer, Airport> airportMap) {
		String sql = "SELECT a.Airport_ID FROM airport a,route r WHERE r.Destination_airport_ID = a.Airport_ID ";
		List<Airport> list = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Airport airport = airportMap.get(res.getInt("Airport_ID"));
				list.add(airport);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	

//	public static void main(String args[]) {
//		FlightDAO dao = new FlightDAO();
//
//		List<Airline> airlines = dao.getAllAirlines();
//		System.out.println(airlines);
//
//		List<Airport> airports = dao.getAllAirports();
//		System.out.println(airports);
//
//		List<Route> routes = dao.getAllRoutes();
//		System.out.println(routes);
//	}

}
