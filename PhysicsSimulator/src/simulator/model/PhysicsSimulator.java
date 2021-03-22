package simulator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONObject;

public class PhysicsSimulator {
	
	private ForceLaws f;
	private double time;
	private List<Body> list;
	private double actualTime;
	
	public PhysicsSimulator (ForceLaws f, double time) {
		
		if(time <= 0 || f == null) {
			throw new IllegalArgumentException();
		}
		
		this.f = f;
		this.time = time;
		this.actualTime = 0.0;
		this.list = new ArrayList<Body>();
	}
	
	public void addBody(Body b) {
		
		for(Body l: this.list ) {
			if(l.getId() == b.getId()) {
				throw new IllegalArgumentException();
			}
		}
		
		this.list.add(b);
	}
	
	public void advance() {
		
		for(Body l: this.list ) {
			l.resetForce();
		}
		
		f.apply(this.list);
		
		for(Body l2: this.list ) {
			l2.move(time);
		}
		
		this.actualTime += time;
	}
	
	public JSONObject getState() {
		
		JSONObject j = new JSONObject();
		List<JSONObject> lista = new ArrayList<JSONObject>();
		
		for(Body l: this.list ) {
			lista.add(l.getState());
		}
		
		j.put("time", this.actualTime);
		j.put("bodies", lista);
	
		return j;	
	}
	
	public String toString() {
		return getState().toString();
	}
}
