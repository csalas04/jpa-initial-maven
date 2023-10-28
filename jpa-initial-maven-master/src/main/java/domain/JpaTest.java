package domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class JpaTest {
	

    public static void main(String[] args) {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
            EntityManager manager = factory.createEntityManager();

            EntityTransaction tx = manager.getTransaction();

            tx.begin();

            // Insertar empleado
            Empleado empleado = new Empleado();
            empleado.setApellidoPaterno("Salas");
            empleado.setApellidoMaterno("Toledo");
            empleado.setNombres("Carlos");
            empleado.setNroHijos(0);
            empleado.setSueldo(18000.0);
            empleado.setFechaNacimiento(LocalDate.of(2000, 4, 18));
            manager.persist(empleado);

            tx.commit();

            // Obtener empleado por ID
            Empleado empleadoObtenido = obtenerEmpleadoPorId(manager, empleado.getId());
            if (empleadoObtenido != null) {
                System.out.println("Empleado obtenido: " + empleadoObtenido);
            } else {
                System.out.println("Empleado no encontrado.");
            }

            // Actualizar empleado
            tx.begin();

            if (empleadoObtenido != null) {
                empleadoObtenido.setSueldo(55000.0);
                manager.merge(empleadoObtenido);
            }

            tx.commit();

            // Eliminar empleado
            tx.begin();

            if (empleadoObtenido != null) {
                manager.remove(empleadoObtenido);
            }

            tx.commit();

            // Listar empleados
            List<Empleado> lista = manager.createNativeQuery("SELECT * FROM tbl_empleados", Empleado.class).getResultList();
            for (Empleado e : lista) {
                System.out.println(e);
            }

            manager.close();
            factory.close();
        }

        private static Empleado obtenerEmpleadoPorId(EntityManager manager, Long id) {
            return manager.find(Empleado.class, id);
        }
}


