package simulator.control;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.BasicBodyBuilder;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.MassLossingBodyBuilder;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	
	private PhysicsSimulator ps;
	private Factory<Body> b;
	
	public Controller(PhysicsSimulator ps, Factory<Body> b) { //constructor
		this.ps = ps;
		this.b = b;
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) { //ejecucion del simulador fisico
		if(expOut != null) { //si se ha especificado una salida esperada
			JSONObject j = new JSONObject(new JSONTokener(expOut)); //pasamos expected output a json object
			JSONArray listaStates = j.getJSONArray("states");
			
			PrintStream p = new PrintStream(out); //generamos un flujo de salida para escribir los estados
			p.println("{");
			p.println("\"states\": [");
			
			p.println(ps.getState()); //pintamos el estado actual (estado de tiempo = 0)
			
			for(int i = 0; i < n; i++) { //ejecutamos n veces advance de PhysicsSimulator
				p.println(" ,");
				this.ps.advance(); //llamamos a advance
				p.println(ps.getState()); //escribimos el estado actual del simulador
				if(!cmp.equal(ps.getState(), listaStates.getJSONObject(i))) { //si los estados a comparar no son iguales salta la excepcion personalizada
					throw new NotEqualStatesException(listaStates.getJSONObject(i), ps.getState(), i);
				}
			}
			
			p.println("]");
			p.println("}");
			
		}
		else { //si no se ha especificado una salida esperada
			PrintStream p = new PrintStream(out);
			p.println("{");
			p.println("\"states\": [");
			
			p.println(ps.getState()); //pintamos el estado actual (estado de tiempo = 0)
			
			for(int i = 0; i < n; i++) { //ejecutamos n veces advance de PhysicsSimulator
				p.println(" ,");
				this.ps.advance(); //llamamos a advance
				p.println(ps.getState()); //escribimos el estado actual del simulador
			}
			
			p.println("]");
			p.println("}");
		}
	}
	
	public void loadBodies(InputStream in) { //metodo para cargar los bodies del input en el simulador fisico
		
		JSONObject j = new JSONObject(new JSONTokener(in));
		JSONArray listaStates = j.getJSONArray("bodies");
		List<JSONObject> lista = new ArrayList<JSONObject>();
		
		for(int i = 0; i< listaStates.length(); i++) { //recorremos la lista de estados y guardamos sus bodies (formato JSON) en una lista nueva
			lista.add(listaStates.getJSONObject(i));
		}
		
		for(int k = 0; k< listaStates.length(); k++) { //recorremos la lista de estados
			BuilderBasedFactory<Body> f = null;
			ps.addBody(this.b.createInstance(listaStates.getJSONObject(k))); //annadimos los bodies de los estados
		}
	}
}
