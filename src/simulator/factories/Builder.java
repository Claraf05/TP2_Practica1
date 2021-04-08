package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String typeTag;
	protected String desc;
	
	protected abstract T createTheInstance(JSONObject j);
	
	public JSONObject getBuilderInfo() {
		
		JSONObject j = new JSONObject();
		j.put("type", this.typeTag);
		j.put("desc", this.desc);
		
		return j;
		
	}
	
	protected JSONObject createData() {
		
		JSONObject j = new JSONObject();
		
		return j;
	}
	
	public T createInstance(JSONObject info) {
		
		if(info == null) {
			throw new IllegalArgumentException();
		}
		
		String type = info.getString("type");
		JSONObject data = info.getJSONObject("data");
		
		T t = null;
		
		if(type.equalsIgnoreCase(typeTag)) {
			t = createTheInstance(info);
		}
		
		return t;
	}
}
