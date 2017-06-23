package it.polito.tdp.flight.model;

import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import it.polito.tdp.flight.model.Event.EventType;



public class Simulatore {
	
	private final int TIMEOUT  = 60*2 ;
	
	private  final int FINE = 60*48;
	private final int FINE_VOLI = 23;
	private final int INIZIO_VOLI = 7;
	
	private PriorityQueue<Event> queue;
	DirectedWeightedMultigraph<Airport,DefaultWeightedEdge> grafo;
	
	public Simulatore(){
		queue = new PriorityQueue<Event>();
	}

	
	public void add(Passeggero p,int time ,Airport airport,DirectedWeightedMultigraph<Airport,DefaultWeightedEdge> grafo){
		Event e = new Event(p,time,airport,EventType.FERMO);
		queue.add(e);
		this.grafo = grafo;
	
	}
	
	public void run(){
		while(!queue.isEmpty()){
			Event e = queue.poll();
			while(e.getTime()<60*48){
				switch (e.getType()){
				case FERMO:
					//genera parti
					//while(e.getTime())
					if(e.getTime()>=INIZIO_VOLI && e.getTime()<=FINE_VOLI){
						
						Event e1 = new Event(e.getP(),e.getTime()+TIMEOUT,this.generaAeroporto(e.getAirport()),EventType.PARTI);
										
					}
					break;
				case PARTI:
				//	Event e2 = new Event
					//genera fermo
					break;
				default:
					break;
				
					
				}
			return;
			}
		}
	
		
	}
	
	public Airport generaAeroporto(Airport airport){
		int c = (int) Math.random()*Graphs.neighborListOf(grafo,airport).size();
		return Graphs.neighborListOf(grafo,airport).get(c);
		}
		
	}


