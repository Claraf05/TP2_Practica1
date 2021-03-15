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
		
		
		for(Body b1: bs) {
			for(Body b2: bs) {
				if(bs.indexOf(b2) != bs.indexOf(b1)) {
					res = (b1.getMass() * b2.getMass());
					res /= ((b1.getPosition().distanceTo(b2.getPosition()))*(b1.getPosition().distanceTo(b2.getPosition())));
					res *= G;
					f = (b1.getPosition().minus(b2.getPosition())).scale(res);
					b1.addForce(f);
				}
			}
		}
	}

	public String toString() {
		//TODO
		return "Newton Universal Gravitation Law";
	}
}
