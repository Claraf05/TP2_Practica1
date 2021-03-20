package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder<T> extends Builder<ForceLaws>{

	public NoForceBuilder() {
		this.typeTag = "nf";
		this.desc = "sin fuerza";
	}
	
	@Override
	protected ForceLaws createTheInstance(JSONObject j) {
		
		JSONObject data = j.getJSONObject("data");
		
		NoForce nf = new NoForce();
		
		return nf;
	}
}
