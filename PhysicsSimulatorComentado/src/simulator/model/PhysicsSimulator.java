package simulator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONObject;

public class PhysicsSimulator {
	
	private ForceLaws f; //ley de fuerza para aplicar en el simulador
	private double time;
	private List<Body> list; //lista de bodys del simulador
	private double actualTime;
	
	public PhysicsSimulator (ForceLaws f, double time) { //constructor
		
		if(time <= 0 || f == null) { //si el tiempo es igual a 0 o no existe la fuerza salta excepcion
			throw new IllegalArgumentException();
		}
		
		this.f = f;
		this.time = time;
		this.actualTime = 0.0;
		this.list = new ArrayList<Body>();
	}
	
	//annade un body a la lista de bodies
	public void addBody(Body b) {
		
		for(Body l: this.list ) { //recore la lista de bodies para comprobar si el body que queremos meter esta ya o no
			if(l.getId() == b.getId()) { //si lo esta salta excepcion
				throw new IllegalArgumentException();
			}
		}
		
		this.list.add(b); //annade el body a la lista
	}
	
	//ejecuta un paso de simulacion
	public void advance() {
		
		for(Body l: this.list ) { //se resetean las fuerzas de todos los bodies
			l.resetForce();
		}
		
		f.apply(this.list); //aplica la fuerza pasada como argumento
		
		for(Body l2: this.list ) { //recorre la lista de bodies para moverlos
			l2.move(time);
		}
		
		this.actualTime += time; //actualiza el tiempo actual
	}
	
	//genera el estado actual de la simulacion
	public JSONObject getState() {
		
		JSONObject j = new JSONObject();
		List<JSONObject> lista = new ArrayList<JSONObject>();
		
		for(Body l: this.list ) { //recorre la lista de bodies para sacar sus estados
			lista.add(l.getState());
		}
		
		j.put("time", this.actualTime); //incluye el tiempo actual en el estado
		j.put("bodies", lista); //incluye la lista de estados de los bodies
	
		return j;	
	}
	
	public String toString() {
		return getState().toString();
	}
}
