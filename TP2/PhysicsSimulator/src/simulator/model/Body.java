package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import jdk.nashorn.api.scripting.JSObject;
import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected Vector2D speed;
	protected Vector2D force;
	protected Vector2D position;
	protected double mass;
	
	public Body(String id, Vector2D speed, Vector2D position, double mass) {
		
		this.id = id;
		this.speed = speed;
		this.force = new Vector2D();
		this.position = position;
		this.mass = mass;
	}

	public String getId() {
		return id;
	}

	public Vector2D getSpeed() {
		return speed;
	}

	public Vector2D getForce() {
		return force;
	}

	public Vector2D getPosition() {
		return position;
	}

	public double getMass() {
		return mass;
	}
	
	public void addForce(Vector2D f) {
		this.force.plus(f);
	}
	
	public void resetForce() {
		this.force = new Vector2D();
	}
	
	public void move(double t) {
		
		Vector2D aceleration = new Vector2D(this.force.scale(1/this.mass));
		Vector2D at2;
		
		position = this.position.plus(this.speed.scale(t));
		at2 = aceleration.scale(1/2 * (t * t));
		position = this.position.plus(at2);
		
		this.speed = this.speed.plus(aceleration.scale(t));
	}
	
	public boolean equals(Object o) {
		
		JSONObject j1 = new JSONObject();
		JSONObject j2 = new JSONObject();
	
		//TODO
		
		return false;
	}
	
	public JSONObject getState() {
		
		JSONObject j = new JSONObject();
		j.put("id", this.id);
		j.put("m", this.mass);
		j.put("p", this.position);
		j.put("v", this.speed);
		j.put("f", this.force);
		
		return j;
	}
	
	public String toString() {
		return getState().toString();
	}
	
}
