package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service;

import org.springframework.stereotype.Service;

@Service
public class ManejoUsuarioService implements ManejoUsuarioServiceInt{
    @Override
    public String procesarNombre(String nombre) {
        return nombre + " Vicente";
    }
}
