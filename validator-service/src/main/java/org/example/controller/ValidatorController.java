package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class ValidatorController {


    @GetMapping("/")
    public String pruebaControllerValidator(){
        return "Retorno desde pruebaControllerValidator";
    }

}
