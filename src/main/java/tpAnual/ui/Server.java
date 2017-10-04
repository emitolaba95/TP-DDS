package tpAnual.ui;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import tpAnual.util.Reseter;
import tpAnual.util.bd.mongo.MongoDatastoreSingleton;

public class Server {
	public static void main(String[] args) {
		PerThreadEntityManagers.getEntityManager().clear();
		Reseter.resetSingletons();
		
		Reseter.resetDatastore(MongoDatastoreSingleton.getDatastore("busquedas"));
		
		configurarSpark();
		Router.configure();
		Spark.after((req,res)-> {
			//PerThreadEntityManagers.getEntityManager().clear(); //si no va, poner close.
		});
		
		DebugScreen.enableDebugScreen();
	}
	
	
	public static void configurarSpark(){
		Spark.port(4567);
		Spark.staticFileLocation("/ui");
	}
	
	public static String paginaPrincipal(Request req, Response res){
		return "hola";
	}
}
