package tpAnual.ui;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import tpAnual.ui.controllers.AdministrarPoiController;
import tpAnual.ui.controllers.BusquedasController;
import tpAnual.ui.controllers.PerfilController;
import tpAnual.ui.controllers.PoiController;
import tpAnual.ui.controllers.TerminalController;

public class Router {

public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.build();
		
		TerminalController terminalController = new TerminalController();
		AdministrarPoiController administrarPoiController = new AdministrarPoiController();
		
		//Spark.get("/", Server::paginaPrincipal);
		Spark.get("/perfil",PoiController::get,engine);
		//Spark.get("/perfil", PerfilController::mostrarPerfil,engine);
		
		Spark.post("/perfil", PerfilController::mostrarPerfil,engine);
		
		//caso de uso - una busqueda mostrando los pois
		Spark.get("/pois", PoiController::listar, engine);
		
		
		Spark.get("/poi", PoiController::get,engine);
		Spark.get("/busqueda", PoiController::listar,engine);
		Spark.get("/terminal", TerminalController::listar,engine);
		Spark.get("/altaTerminal", TerminalController::alta,engine);
		Spark.post("/altaTerminal", terminalController::altaAgregar);
		Spark.get("/modificarTerminal", TerminalController::modificar,engine);
		Spark.post("/modificarTerminal", terminalController::guardarModificar);
		Spark.post("/bajaTerminal", terminalController::baja);
				
		//Spark.get("/historico-consultas", BusquedasController::listarHardcodeado,engine);
		Spark.get("/historico-consultas", BusquedasController::listar,engine);
		
		
		Spark.get("/busqueda-pois", BusquedasController::verPois,engine);
		//Spark.get("/busqueda-pois", BusquedasController::poisDeBusqueda);
		Spark.get("/administrarPoi", AdministrarPoiController::listar,engine);
		Spark.get("/modificarPoi", AdministrarPoiController::editar,engine);
		Spark.post("/modificarPoi", administrarPoiController::guardar);
		Spark.post("/bajaPoi", administrarPoiController::baja);
		Spark.get("/altaPoi", AdministrarPoiController::alta,engine);
		Spark.post("/altaPoi", administrarPoiController::altaAgregar);
	}
}
