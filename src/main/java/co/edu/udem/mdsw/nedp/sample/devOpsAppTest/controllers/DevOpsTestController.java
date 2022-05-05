package co.edu.udem.mdsw.nedp.sample.devOpsAppTest.controllers;


import co.edu.udem.mdsw.nedp.sample.devOpsAppTest.service.ManejoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DevOpsTestController {



    @GetMapping("/test")
    public String metodoEjemplo(@RequestParam(name="name", required = false) String name, Model model )
    {
        model.addAttribute("frase", "Bienvenido "+ name);
        return "home";
    }
}
