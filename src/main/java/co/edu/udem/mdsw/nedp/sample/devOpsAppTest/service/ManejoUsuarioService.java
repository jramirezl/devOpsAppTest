package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service;

import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.entities.Usuario;
import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ManejoUsuarioService implements ManejoUsuarioServiceInt{
    UsuariosRepository usuariosRepository;

    @Autowired
    public ManejoUsuarioService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public String procesarNombre(String nombre) {
        return nombre + " Vicente";
    }

    public Usuario insertarUsuario(Usuario usuario){
        return usuariosRepository.save(usuario);
    }

    public Collection<Usuario> listarUsuarios(){
        Collection<Usuario> usuarioCollection = new ArrayList<>();
        Iterable<Usuario> listaBd = usuariosRepository.findAll();
        listaBd.forEach(usuarioCollection::add);
        return usuarioCollection;
    }

}
