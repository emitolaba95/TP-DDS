package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Terminal;
import tpAnual.ui.RepositorioTerminales;

public class TerminalController implements WithGlobalEntityManager, TransactionalOps { // TransactionalOps

	public static ModelAndView get(Request req, Response res){
		
		Map<String, Object> viewModel = new HashMap<String, Object>();
		
		Terminal terminal = new Terminal(12);
		terminal.setNombre("test");
		
		viewModel.put("terminal",terminal);
		
		return new ModelAndView(viewModel, "terminal.hbs");
	}
	
	public static ModelAndView listar(Request req, Response res){
		
		int terminalBuscada = 0;
		terminalBuscada = Integer.parseInt(req.queryParams("comuna"));
		
		Map<String, List<Terminal>> model = new HashMap<>();
		
		List<Terminal> terminales = new ArrayList<Terminal>();
		
  		if(terminalBuscada==0){
  			terminales.addAll(RepositorioTerminales.getInstance().listar());
  		}
  		else{
  			terminales.addAll(RepositorioTerminales.getInstance().getTerminalesPorComuna(terminalBuscada));
  		}
  		
  		model.put("terminales", terminales);
  		return new ModelAndView(model, "terminal.hbs");
	}
	
	public static ModelAndView alta(Request req, Response res){
		Map<String, List<Terminal>> model = new HashMap<>();
  		
  		return new ModelAndView(model, "altaTerminal.hbs");
	
	}
	
	public Void altaAgregar(Request req, Response res){
		
		String nombre = req.queryParams("nombre");
		int comuna = Integer.parseInt(req.queryParams("comuna"));
		
		Terminal terminalNueva = new Terminal();
		terminalNueva.setNumeroComuna(comuna);
		terminalNueva.setNombre(nombre);
		
		withTransaction(() ->{
			RepositorioTerminales.getInstance().agregar(terminalNueva);
		});
		
		res.redirect("/terminal?comuna=0");
		
		return null;
	}
	
	public static ModelAndView modificar(Request req, Response res){
		Map<String, List<Terminal>> model = new HashMap<>();
		
		int terminal = Integer.parseInt(req.queryParams("nroTerminal"));
		
		Terminal terminalAModificar = RepositorioTerminales.getInstance().buscarPorId(terminal);
		
		return new ModelAndView(terminalAModificar, "modificarTerminal.hbs");
	}
	
	public Void guardarModificar(Request req, Response res){
		
		int numeroTerminal = Integer.parseInt(req.queryParams("numeroTerminal"));
		String nombre = req.queryParams("nuevoNombre");
		int comuna = Integer.parseInt(req.queryParams("nuevaComuna"));
		
		Terminal terminal = RepositorioTerminales.getInstance().buscarPorId(numeroTerminal);
		
		withTransaction(() ->{
//			terminal.setNumeroTerminal(numeroTerminal);
			terminal.setNombre(nombre);
			terminal.setNumeroComuna(comuna);
			RepositorioTerminales.getInstance().modificar(terminal);
		});
		
		res.redirect("/terminal?comuna=0");
		
		return null;
	}
	
	public Void baja(Request req, Response res){
		
		int numeroTerminal = Integer.parseInt(req.queryParams("nroTerminal"));
		Terminal terminal = RepositorioTerminales.getInstance().buscarPorId(numeroTerminal);
		
		withTransaction(() ->{
			RepositorioTerminales.getInstance().baja(terminal);
		});
		
		res.redirect("/terminal?comuna=0");
		return null;
	}
	
}

