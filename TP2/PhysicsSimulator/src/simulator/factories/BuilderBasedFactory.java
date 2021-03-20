package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;
import simulator.model.MovingTowardsFixedPoint;
import simulator.model.NewtonUniversalGravitation;
import simulator.model.NoForce;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> list = new ArrayList<Builder<T>>();
	
	public BuilderBasedFactory(List<Builder<T>> l) {
		this.list = l;
		
	}

	@Override
	public T createInstance(JSONObject info) {
		
		
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		// TODO
		return null;
	}

}
