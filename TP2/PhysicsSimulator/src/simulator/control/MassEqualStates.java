package simulator.control;

import org.json.JSONObject;

public class MassEqualStates implements StateComparator{

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		if(s1.get("time") == s2.get("time")) {

		}
		return false;
	}

}
