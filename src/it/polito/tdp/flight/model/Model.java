package it.polito.tdp.flight.model;


import java.util.*;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import it.polito.tdp.flight.db.FlightDAO;

public class Model {
	
	private DirectedWeightedMultigraph<Airport,DefaultWeightedEdge> grafo;
	private FlightDAO dao;
	private List<Airport> aeroporti;
	private List<Route> viaggi;
	private Map<Integer,Airport> airportMap;
	
	public Model(){
		
		dao = new FlightDAO();
		airportMap = new HashMap<Integer,Airport>();
		aeroporti = dao.getAllAirports(airportMap);
		viaggi = dao.getAllRoutes(airportMap);	
		
		for( Airport a :aeroporti){
			airportMap.put(a.getAirportId(), a);
		}
		
	}
	
	public Airport creaGrafo(int numero){
		
		this.grafo = new DirectedWeightedMultigraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		aeroporti = dao.getAllAirportsConRotte(airportMap);
		Graphs.addAllVertices(grafo, aeroporti);
		
		for(Airport a1 : aeroporti){
			for(Airport a2: aeroporti){
				if(!a1.equals(a2) && LatLngTool.distance(a1.getCoordinate(),a2.getCoordinate(),LengthUnit.KILOMETER)<numero){
					System.out.println(LatLngTool.distance(a1.getCoordinate(),a2.getCoordinate(),LengthUnit.KILOMETER));
					Graphs.addEdgeWithVertices(grafo, a1, a2, LatLngTool.distance(a1.getCoordinate(),a2.getCoordinate(),LengthUnit.KILOMETER)/800.0);
				}
			}
		}
	
		Airport fiumicino = dao.getFiumicino(airportMap);
		
		Airport best = new Airport(0, null, null, null, null, null, null, 0, null, null);
		double max = Integer.MIN_VALUE;
		
		for(Airport a: grafo.vertexSet()){
			if(!a.equals(fiumicino)){
			DijkstraShortestPath<Airport,DefaultWeightedEdge> dsp = new DijkstraShortestPath<Airport,DefaultWeightedEdge>(this.grafo,fiumicino,a);
			
			if(dsp.getPathLength()>max){
				max = dsp.getPathLength();
				best = a;
			}
		}
			
	}
return best;
		//new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy"))
		//double spazio = LatLngTool.distance(c.getStazioneA().getCoords(),c.getStazioneB().getCoords(),LengthUnit.KILOMETER);
	}
		
		public boolean isConnected(){
			ConnectivityInspector<Airport,DefaultWeightedEdge> con = new ConnectivityInspector<Airport,DefaultWeightedEdge>(grafo);
			return con.isGraphConnected();
		}
	
		
		public Airport creaGrafo2(int numero){

			this.grafo = new DirectedWeightedMultigraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
			//aeroporti = dao.getAllAirportsConRotte(airportMap);
			//Graphs.addAllVertices(grafo,aeroporti);
			
			for(Route r :viaggi){
					if(LatLngTool.distance(r.getSourceAirport().getCoordinate(),r.getDestinationAirport().getCoordinate(),LengthUnit.KILOMETER)<numero){
						
	Graphs.addEdgeWithVertices(grafo, r.getSourceAirport(), r.getDestinationAirport(), LatLngTool.distance(r.getSourceAirport().getCoordinate(),r.getDestinationAirport().getCoordinate(),LengthUnit.KILOMETER)/800.0);
					
					}
				
				}
		
//		Airport fiumicino = dao.getFiumicino(airportMap);
			Airport fiumicino = dao.getFiumicino(airportMap);
			
			Airport best = null;
			double max = Integer.MIN_VALUE;
			
			for(Airport a: grafo.vertexSet()){
				if(!a.equals(fiumicino)){
				DijkstraShortestPath<Airport,DefaultWeightedEdge> dsp = new DijkstraShortestPath<Airport,DefaultWeightedEdge>(this.grafo,fiumicino,a);
				
				if(dsp.getPathLength()>max){
					max = dsp.getPathLength();
					best = a;
				}
			}
			
				
		}
	return best;
			//new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy"))
			//double spazio = LatLngTool.distance(c.getStazioneA().getCoords(),c.getStazioneB().getCoords(),LengthUnit.KILOMETER);
		}
		
	public void riduciGrafo(){

		Set<Airport> togliere = new HashSet<>() ;
			
		Iterator<Airport> iter = grafo.vertexSet().iterator() ;
		for(int i=0; i<grafo.vertexSet().size(); i++) {
			Airport a = iter.next();
			if(Graphs.neighborListOf(grafo,a).isEmpty())
			togliere.add(a) ;
		}
	grafo.removeAllVertices(togliere) ;
		}

}
