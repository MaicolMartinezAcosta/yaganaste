package com.yaganaste.transferencia.controller;

import com.yaganaste.transferencia.service.ContadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contador")
public class ContadorController {

    @Autowired
    private ContadorService contadorService;

    @GetMapping("/incrementar")
    public int incrementarContador() {
        return contadorService.incrementarContador();
    }
}
