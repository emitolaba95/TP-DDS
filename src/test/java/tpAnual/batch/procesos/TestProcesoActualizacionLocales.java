package tpAnual.batch.procesos;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.Mapa;
import tpAnual.POIs.Negocio;
import tpAnual.POIs.Poi;
import tpAnual.externo.adapters.LocalComercialAdapter;
import tpAnual.externo.sistemasExternos.LocalComercialExternoDTO;
import tpAnual.util.Reseter;
import tpAnual.util.wrapper.PointWrapper;

public class TestProcesoActualizacionLocales implements WithGlobalEntityManager{
	
	private LocalComercialAdapter lcAdapter = new LocalComercialAdapter("src/test/resources/localesComerciales.txt");
	private ProcesoActualizarLocales procesoLocales = new ProcesoActualizarLocales();
	
	@Before
	public void init(){
		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
	}
	
	@After
	public void finalizar(){
		entityManager().getTransaction().rollback();
	}
	
	
	@Test
	public void testNuevoLocalComercialExterno(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("chocolates");
		palabrasClave.add("helado");
		Assert.assertTrue(palabrasClave.equals(externo.getPalabrasClave()));
	}
	
	@Test
	public void testCambioTagsLocalComercial(){
		LocalComercialExternoDTO externo= lcAdapter.adaptar("negocio1;chocolates helado");
		
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Poi poi = new Negocio(new PointWrapper(0,0),"negocio1",palabrasClave,"Venta",10);
		Mapa.getInstance().alta(poi);
		procesoLocales.cambiarLocalComercial(externo);
		Assert.assertFalse(poi.getTags().contains("pepas")); //se pierde al actualizar el local
		Assert.assertTrue(poi.getTags().contains("chocolates"));
		Assert.assertTrue(poi.getTags().contains("helado"));
	}
	
	@Test
	public void testIntercambiarLocalComercial(){
		Set<String> palabrasClave = new HashSet<String>();
		palabrasClave.add("pepas");
		Poi poi = new Negocio(new PointWrapper(0,0),"negocio1",palabrasClave,"Venta",10);

		Mapa.getInstance().alta(poi);
		procesoLocales.realizarProceso();
		Assert.assertFalse(poi.getTags().contains("pepas")); //se pierde al actualizar el local
		Assert.assertTrue(poi.getTags().contains("chocolates"));
		Assert.assertTrue(poi.getTags().contains("helado"));
	}
}
