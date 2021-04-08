package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	public final static double G = 6.67E-11;
	protected double g;
	
	public  NewtonUniversalGravitation(double g) {
		this.g = g;
	}

	@Override
	public void apply(List<Body> bs) {
		
		Vector2D f = new Vector2D();
		double res = 0.0;
		
		for(int i = 0; i< bs.size(); i++) {
			for(int j = 0; j< bs.size(); j++) {
				if(i != j) {
					res = (bs.get(i).getMass() * bs.get(j).getMass());
					Vector2D m = bs.get(j).getPosition().minus(bs.get(i).getPosition());
					double mag = m.magnitude();
					res /= (mag*mag);
					res *= g;
					f = (m.direction()).scale(res);
					bs.get(i).addForce(f);
				}
			}
		}
	}

	public String toString() {
		return "Newton Universal Gravitation Law";
	}
}
