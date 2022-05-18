package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service;

import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.entities.Usuario;

import java.util.Collection;

public interface ManejoUsuarioServiceInt {

    String procesarNombre(String nombre);
    Collection<Usuario> listarUsuarios();
    Usuario insertarUsuario(Usuario usuario);
}
