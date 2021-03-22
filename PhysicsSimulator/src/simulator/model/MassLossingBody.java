package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body{
	
	private double lossFactor; 
	private double lossFrequency;

	public MassLossingBody(String id, Vector2D speed, Vector2D position, double mass, double lossFactor, double lossFrequency){
		
		super(id, speed, position, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
	}
	
	@Override
	public void move(double t) {
		
		Vector2D aceleration = new Vector2D();
		Vector2D at2;
		
		if(t >= this.lossFrequency) {
			this.mass = this.mass * (1-this.lossFactor);
		}
		aceleration = this.force.scale(1/this.mass);

		position = this.position.plus(this.speed.scale(t));
		at2 = aceleration.scale(1/2 * (t * t));
		position = this.position.plus(at2);
		this.speed = this.speed.plus(aceleration.scale(t));
	}
}
