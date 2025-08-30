package com.restaurant.controller;

import com.restaurant.dto.MesaDTO;
import com.restaurant.model.Mesa;
import com.restaurant.service.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "http://localhost:3000") 
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @PostMapping
    public Mesa crearMesa(@RequestBody MesaDTO dto) {
        return mesaService.crearMesa(dto);
    }

    @GetMapping
    public List<Mesa> listarMesas() {
        return mesaService.listarMesas();
    }

    @PutMapping("/{id}")
    public Mesa actualizarMesa(@PathVariable Long id, @RequestBody MesaDTO dto) {
        return mesaService.actualizarMesaPosicion(id, dto);
    }
}
