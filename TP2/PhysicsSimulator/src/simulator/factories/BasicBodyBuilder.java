package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;


public class BasicBodyBuilder<T> extends Builder<Body>{
	
	public BasicBodyBuilder() {
		this.typeTag = "basic";
		this.desc = "cuerpo basico";
	}
	
	@Override
	protected Body createTheInstance(JSONObject j) {
		
		JSONObject data = j.getJSONObject("data");
		
		Vector2D v = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
		Vector2D p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		
		Body b = new Body(data.getString("id"), v, p, data.getDouble("m"));
		
		return b;
	}
	
	protected JSONObject createData() {
		
		JSONObject j = new JSONObject();
		
		j.put("id", "identificador del cuerpo basico");
		j.put("p", "vector posicion del cuerpo basico");
		j.put("v", "vector velocidad del cuerpo basico");
		j.put("m", "masa del cuerpo basico");
		
		return j;
	}
	
}
