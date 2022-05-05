package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.controllers;

import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service.ManejoUsuarioService;
import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service.ManejoUsuarioServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestDevOpsController {
    ManejoUsuarioServiceInt manejoUsuarioService;

    @Autowired
    public RestDevOpsController(ManejoUsuarioService manejoUsuarioService){
        this.manejoUsuarioService = manejoUsuarioService;
    }
    @GetMapping("/test2")
    public Map<String, String> getMapa(@RequestParam(name="name", required = false) String name){
        Map json = new HashMap();
        json.put("name", manejoUsuarioService.procesarNombre(name));
        return json;
    }
}
