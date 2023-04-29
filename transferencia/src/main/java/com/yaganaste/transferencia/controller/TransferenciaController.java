package com.yaganaste.transferencia.controller;

import com.yaganaste.transferencia.dto.request.TransferenciaRequestDTO;
import com.yaganaste.transferencia.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {
    @Autowired
    TransferenciaService transferenciaService;

    @PostMapping()
    void transferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO){
        transferenciaService.transferencia(transferenciaRequestDTO.getImporte(), transferenciaRequestDTO.getCliente1(),transferenciaRequestDTO.getCliente2());
    }
}
