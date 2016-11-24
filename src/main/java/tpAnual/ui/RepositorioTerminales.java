package tpAnual.ui;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tpAnual.Terminal;
import tpAnual.util.Reseter;


public class RepositorioTerminales implements WithGlobalEntityManager{

	public static RepositorioTerminales instancia = new RepositorioTerminales();

	public void agregar(Terminal terminal) {
		entityManager().persist(terminal);
	}
	
	
	public Terminal buscar(long id){
		return entityManager().find(Terminal.class, id);
	}
	
	
	public List<Terminal> listar() {
		
		//TODO borrar ejemplitos de prueba
		Terminal terminalPrueba1 = new Terminal();
		terminalPrueba1.setNombre("terminal Abasto");
		terminalPrueba1.setNumeroComuna(1);
		
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba2.setNombre("terminal DOT");
		terminalPrueba2.setNumeroComuna(2);

		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
		entityManager().persist(terminalPrueba1);
		entityManager().persist(terminalPrueba2);
		
		List<Terminal> terminales =  entityManager().createQuery("from Terminal", Terminal.class).getResultList();
//		entityManager().getTransaction().rollback();

//		Reseter.resetSingletons();
		return terminales;
	}
	
	public List<Terminal> getTerminalesPorComuna(int comuna){
		return this.listar().stream().filter(t->t.getNumeroComuna().equals(comuna)).collect(Collectors.toList());
	}
	
	public Terminal buscarPorNombre(String nombre){
		
		Terminal terminalPrueba1 = new Terminal();
		terminalPrueba1.setNombre("abasto");
		
		Terminal terminalPrueba2 = new Terminal();
		terminalPrueba2.setNombre("dot");

		Reseter.resetSingletons();
		entityManager().getTransaction().begin();
		entityManager().persist(terminalPrueba1);

		entityManager().persist(terminalPrueba2);
		
		Terminal resultado =  entityManager().createQuery("FROM Terminal where nombre_terminal= :nombre ", Terminal.class).
				setParameter("nombre", nombre).getResultList().get(0);
		entityManager().getTransaction().rollback();

				
		Reseter.resetSingletons();
		return resultado;
		
	}
}