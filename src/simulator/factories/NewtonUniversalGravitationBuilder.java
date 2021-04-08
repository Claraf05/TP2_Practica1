package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder<T> extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		this.typeTag = "nlug";
		this.desc = "ley de Newton";
	}
	
	@Override
	protected ForceLaws createTheInstance(JSONObject j) {
		
		JSONObject data = j.getJSONObject("data");
		NewtonUniversalGravitation nlug;
		
		if(data.has("G")) {
			nlug = new NewtonUniversalGravitation(data.getDouble("G"));
		}
		else {
			nlug = new NewtonUniversalGravitation(NewtonUniversalGravitation.G);
		}
		
		return nlug;
	}
	
	protected JSONObject createData() {
		JSONObject j = new JSONObject();
		
		j.put("G", "constante gravitacional");
		
		return j;
	}

}
