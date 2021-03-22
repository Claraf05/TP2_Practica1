package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder<T> extends Builder<ForceLaws>{

	public MovingTowardsFixedPointBuilder() {
		this.typeTag = "mtcp";
		this.desc = "movimiento hacia un punto fijo";
	}
	
	@Override
	protected ForceLaws createTheInstance(JSONObject j) {
		
		JSONObject data = j.getJSONObject("data");
		
		Vector2D c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
		MovingTowardsFixedPoint mtcp = new MovingTowardsFixedPoint(c, data.getDouble("g"));
		
		return mtcp;
	}
	
	protected JSONObject createData() {
		JSONObject j = new JSONObject();
		
		j.put("c", "vector de la posicion a la que se movera el cuerpo");
		j.put("g", "aceleracion");
		
		return j;
	}

}
