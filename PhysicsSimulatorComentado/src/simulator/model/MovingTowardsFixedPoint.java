package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	private Vector2D direction; //vector direccion
	private final double g; //
	
	public MovingTowardsFixedPoint(Vector2D direction, double g) { //Constructor
		this.direction = direction;
		this.g = g;
	}
	
	@Override
	public void apply(List<Body> bs) {
		//Vector2D acceleration = new Vector2D();
		
		for (Body b: bs) { //recorre la lista de bodies y les annade la fuerza mtfp a todos
			b.addForce(direction.minus(b.getPosition()).direction().scale(g*b.getMass()));
		}
	}
	
	public String toString() { //devuelve el nombre de la fuerza
		return "Moving Towards Fixed Point";
	}
}
