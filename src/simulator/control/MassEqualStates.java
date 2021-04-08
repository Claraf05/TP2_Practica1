package simulator.control;

import org.json.JSONObject;

import simulator.model.Body;

public class MassEqualStates implements StateComparator{

	public MassEqualStates() {
		
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		
		boolean ok = false;
		
		if(s1.get("time") == s2.get("time")) {
			if(s1.getJSONArray("bodies").length() == s2.getJSONArray("bodies").length()) {
				for(int i = 0; i< s1.getJSONArray("bodies").length(); i++) {
					JSONObject obj1 = s1.getJSONArray("bodies").getJSONObject(i);
					JSONObject obj2 = s2.getJSONArray("bodies").getJSONObject(i);
					if(obj1.get("id") == obj2.get("id")) {
						if(obj1.get("m") == obj2.get("m")) {
							ok = true;
						}
					}
				}
			}
		}
		return ok;
	}

}
