package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.repositories;

import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuario, Integer> {
}
