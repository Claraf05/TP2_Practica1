package simulator.control;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualStates implements StateComparator{
	
	private double eps;

	public EpsilonEqualStates(double eps) {
		this.eps = eps;
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
						
						if(Math.abs(obj1.getDouble("m") - obj2.getDouble("m")) <= this.eps) {
							
							Vector2D v1, v2;
							v1 = new Vector2D(obj1.getJSONArray("p").getDouble(0), obj1.getJSONArray("p").getDouble(1));
							v2 = new Vector2D(obj2.getJSONArray("p").getDouble(0), obj2.getJSONArray("p").getDouble(1));
							
							if((v1.distanceTo(v2)) <= this.eps){
								v1 = new Vector2D(obj1.getJSONArray("v").getDouble(0), obj1.getJSONArray("v").getDouble(1));
								v2 = new Vector2D(obj2.getJSONArray("v").getDouble(0), obj2.getJSONArray("v").getDouble(1));
								
								if((v1.distanceTo(v2)) <= this.eps){
									v1 = new Vector2D(obj1.getJSONArray("f").getDouble(0), obj1.getJSONArray("f").getDouble(1));
									v2 = new Vector2D(obj2.getJSONArray("f").getDouble(0), obj2.getJSONArray("f").getDouble(1));
									
									if((v1.distanceTo(v2)) <= this.eps){
										ok = true;
									}
								}
							}
						}
					}
				}
			}
		}
		return ok;
	}

}
