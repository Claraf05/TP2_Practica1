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
	
	public Controller(PhysicsSimulator ps, Factory<Body> b) {
		this.ps = ps;
		this.b = b;
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) {
		if(expOut != null) {
			JSONObject j = new JSONObject(new JSONTokener(expOut));
			JSONArray listaStates = j.getJSONArray("states");
			//List<JSONObject> lista = new ArrayList<JSONObject>();
			
			PrintStream p = new PrintStream(out);
			p.println("{");
			p.println("\"states\": [");
			
			p.println(ps.getState());
			//lista.add(ps.getState());
			
			for(int i = 0; i < n; i++) {
				p.println(" ,");
				this.ps.advance();
				p.println(ps.getState());
				//lista.add(ps.getState());
				if(!cmp.equal(ps.getState(), listaStates.getJSONObject(i))) {
					throw new NotEqualStatesException(listaStates.getJSONObject(i), ps.getState(), i);
				}
			}
			
			//j.put("states", lista);
			
			p.println("]");
			p.println("}");
			
		}
		else {
			PrintStream p = new PrintStream(out);
			p.println("{");
			p.println("\"states\": [");
			
			p.println(ps.getState());
			
			for(int i = 0; i < n; i++) {
				p.println(" ,");
				this.ps.advance();
				p.println(ps.getState());
			}
			
			p.println("]");
			p.println("}");
		}
	}
	
	public void loadBodies(InputStream in) {
		
		JSONObject j = new JSONObject(new JSONTokener(in));
		JSONArray listaStates = j.getJSONArray("bodies");
		List<JSONObject> lista = new ArrayList<JSONObject>();
		
		for(int i = 0; i< listaStates.length(); i++) {
			lista.add(listaStates.getJSONObject(i));
		}
		
		for(int k = 0; k< listaStates.length(); k++) {
			BuilderBasedFactory<Body> f = null;
			ps.addBody(this.b.createInstance(listaStates.getJSONObject(k)));
		}
	}
}
