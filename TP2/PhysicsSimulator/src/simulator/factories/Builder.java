package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String typeTag;
	protected String desc;
	
	public Builder() {
		
	}
	
	protected abstract T createTheInstance(JSONObject j);
	
	public JSONObject getBuilderInfo() {
		
		JSONObject j = new JSONObject();
		j.put("type", this.typeTag);
		j.put("data", this.createData());
		
		return j;
		
	}
	
	protected JSONObject createData() {
		
		JSONObject j = new JSONObject();
		j.put("", "");
		
		return j;
	}
	
	public T createInstance(JSONObject info) {
		
		if(info == null) {
			throw new IllegalArgumentException();
		}
		
		String type = info.getString("type");
		JSONObject data = info.getJSONObject("data");
		T t;
		
		try {
			switch(type) {
			
			case "basic":
				this.createTheInstance(info);
				break;
				
			case "mlb":
				this.createTheInstance(info);
				break;
				
			case "nlug":
				this.createTheInstance(info);
				break;
				
			case "mtcp":
				this.createTheInstance(info);
				break;
				
			case "nf":
				this.createTheInstance(info);
				break;
				
			case "masseq":
				this.createTheInstance(info);
				break;
				
			case "epseq":
				this.createTheInstance(info);
				break;
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return null;
	}
}
