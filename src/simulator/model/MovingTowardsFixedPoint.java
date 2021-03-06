package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	private Vector2D direction;
	private final double g;
	
	public MovingTowardsFixedPoint(Vector2D direction, double g) {
		this.direction = direction;
		this.g = g;
	}
	
	@Override
	public void apply(List<Body> bs) {
		Vector2D acceleration = new Vector2D();
		
		for (Body b: bs) {
			b.addForce(direction.minus(b.getPosition()).direction().scale(g*b.getMass()));
		}
	}
	
	public String toString() {
		return "Moving Towards Fixed Point";
	}
}
