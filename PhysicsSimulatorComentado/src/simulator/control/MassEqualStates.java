package simulator.control;

import org.json.JSONObject;

import simulator.model.Body;

public class MassEqualStates implements StateComparator{

	public MassEqualStates() { //constructor
		//vacio porque no necesita ningun atributo
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) { //compara 2 estados con el metodo de igualdad de masas
		
		boolean ok = false;

		if(s1.get("time") == s2.get("time")) { //si le tiempo actual de ambos estados son iguales
			if(s1.getJSONArray("bodies").length() == s2.getJSONArray("bodies").length()) { //si ambos estados tienen el mismo numero de bodies
				for(int i = 0; i< s1.getJSONArray("bodies").length(); i++) { //recorre la lista de bodies
					JSONObject obj1 = s1.getJSONArray("bodies").getJSONObject(i);
					JSONObject obj2 = s2.getJSONArray("bodies").getJSONObject(i);
					if(obj1.get("id") == obj2.get("id")) { //si los identificadores de los bodies a comparar son iguales
						if(obj1.get("m") == obj2.get("m")) { //y las masas tambien son iguales
							ok = true; //se dice que son iguales con el metodo de igualdad de masas
						}
					}
				}
			}
		}
		return ok;
	}
 //deberia ser primero true y luego false ***************************************
}
