package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NoForce implements ForceLaws{
	
	public NoForce() {
		//vacio
	}

	@Override
	public void apply(List<Body> bs) {
		//vacio
	}
	
	public String toString() {
		//TODO
		return "forze = 0";
	}
}
