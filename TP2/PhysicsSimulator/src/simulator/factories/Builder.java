package simulator.factories;

import org.json.JSONObject;

public class Builder<T> {
	
	public Builder() {
		
	}
	
	public T createInstance(JSONObject info) {
		
		if(info == null) {
			throw new IllegalArgumentException();
		}
		
		String type = info.getString("type");
		JSONObject data = info.getJSONObject("data");
		T t;
		
		switch(type) {
		case "basic":
			if(data.getJSONArray("v"). )
			Vector2D v = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
			Vector2D p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
			Body b = new Body(data.getString("id"), v, p, data.getDouble("m"));
			
			break;
			
		case "mlb":
			Vector2D v2 = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
			Vector2D p2 = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
			
			MassLossingBody mlb = new MassLossingBody(data.getString("id"), v2, p2, data.getDouble("m"), data.getDouble("factor"), data.getDouble("freq"));
			
			break;
			
		case "nlug":
			if(data != null) {
				NewtonUniversalGravitation nlug = new NewtonUniversalGravitation(data.getDouble("G"));
			}
			else {
				NewtonUniversalGravitation nlug = new NewtonUniversalGravitation(NewtonUniversalGravitation.G);
			}
			
			break;
			
		case "mtcp":
			Vector2D c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
			MovingTowardsFixedPoint mtcp = new MovingTowardsFixedPoint(c, data.getDouble("g"));
		
			break;
			
		case "nf":
			NoForce nf = new NoForce();		
		
			break;
			
		case "masseq":
			
			break;
			
		case "epseq":
			
			break;
				
		}
		
		return null;
	}
	
}
