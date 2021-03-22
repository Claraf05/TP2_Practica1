package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;
import simulator.model.NewtonUniversalGravitation;

public class EpsilonEqualStateBuilder<T> extends Builder<StateComparator>{

	public EpsilonEqualStateBuilder() {
		this.typeTag = "epseq";
		this.desc = "igualdad modulo epsilon";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject j) {
		
		JSONObject data = j.getJSONObject("data");
		EpsilonEqualStates epseq;
		
		if(data != null) {
			epseq = new EpsilonEqualStates(data.getDouble("eps"));
		}
		else {
			epseq = new EpsilonEqualStates(0.0);
		}
		
		return epseq;
	}
	
	protected JSONObject createData() {
		JSONObject j = new JSONObject();
		
		j.put("eps", "modulo epsilon");
		
		return j;
	}
	
}
