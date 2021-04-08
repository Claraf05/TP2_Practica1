package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;
import simulator.model.NoForce;

public class MassEqualStateBuilder<T> extends Builder<StateComparator>{

	public MassEqualStateBuilder() {
		this.typeTag = "masseq";
		this.desc = "igualdad de masas";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject j) {
		JSONObject data = j.getJSONObject("data");
		
		MassEqualStates masseq = new MassEqualStates();
		
		return masseq;
		
	}
}
