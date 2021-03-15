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
			acceleration = acceleration.plus((b.getPosition().direction().scale(this.g * -1)));
			b.addForce(acceleration.scale(b.getMass()));
			acceleration.scale(0);
		}
	}
	
	public String toString() {
		//TODO
		return "Moving Towards Fixed Point";
	}
}
