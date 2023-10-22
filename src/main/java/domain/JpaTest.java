package domain;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaTest {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		
		Departamento d = new Departamento();
		d.setNombre("Contabilidad");
		
		// Insertar
		manager.persist(d);
		
		tx.commit();
		
		
		List<Departamento> lista = manager.createQuery("from Depa", Departamento.class).getResultList();
		for (Departamento departamento : lista) {
			System.out.println(departamento);
		}
		
	}

}
