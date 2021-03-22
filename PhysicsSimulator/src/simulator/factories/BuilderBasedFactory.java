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
	private List<JSONObject> listaObj = new ArrayList<JSONObject>();
	
	public BuilderBasedFactory(List<Builder<T>> l) {
		this.list = l; 
	}

	@Override
	public T createInstance(JSONObject info) {
		T t = null;
		for(Builder<T> b: this.list) {
			t = b.createInstance(info);
		}
		
		return t;
	}

	@Override
	public List<JSONObject> getInfo() {

		for(Builder<T> b: this.list) {
			for(int i = 0; i<this.list.size(); i++) {
				this.listaObj.add(b.getBuilderInfo());
			}
		}
		
		return this.listaObj;
	}

}
