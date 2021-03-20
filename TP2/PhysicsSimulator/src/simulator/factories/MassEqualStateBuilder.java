package simulator.factories;

import org.json.JSONObject;

import simulator.control.StateComparator;

public class MassEqualStateBuilder<T> extends Builder<StateComparator>{

	public MassEqualStateBuilder() {
		this.typeTag = "masseq";
		this.desc = "igualdad de masas";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject j) {
		// TODO Auto-generated method stub
		return null;
	}
}
