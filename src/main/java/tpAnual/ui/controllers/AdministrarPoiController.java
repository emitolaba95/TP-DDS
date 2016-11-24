package tpAnual.ui.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tpAnual.Mapa;
import tpAnual.POIs.EstacionDeColectivo;
import tpAnual.POIs.Poi;
import tpAnual.util.wrapper.PointWrapper;

public class AdministrarPoiController {

	public static ModelAndView get(Request req, Response res) {

		Map<String, Object> viewModel = new HashMap<String, Object>();

		// TODO borrar

		Set<String> tags = new HashSet<String>();
		PointWrapper ubicacion = new PointWrapper(54, 10);
		Poi poi = new EstacionDeColectivo(ubicacion, "107", tags, 0, "");

		viewModel.put("poi", poi);

		return new ModelAndView(viewModel, "administrar-poi.hbs");
	}

	public static ModelAndView listar(Request req, Response res) {

		String nombrePoi = req.queryParams("nombre");

		Map<String, List<Poi>> model = new HashMap<>();

		List<Poi> pois = (nombrePoi=="" || nombrePoi==null)?
				Mapa.getInstance().buscarPoi(nombrePoi)
				:Mapa.getInstance().getPois();

		model.put("pois", pois);
		return new ModelAndView(model, "administrar-pois.hbs");

	}

	public static ModelAndView editar(Request req, Response res) {
		String poiId = req.queryParams("id");
		
		Map<String, Poi> viewModel = new HashMap<>();

		Poi poi = Mapa.getInstance().poiDeId(poiId);
		viewModel.put("poi", poi);
		return new ModelAndView(poi, "editar-poi.hbs");
	}
}
