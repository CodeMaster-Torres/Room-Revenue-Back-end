package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.models.ReservaEntity;
import com.roomrevenue.roomRevenue.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository repository;

    @GetMapping("/getReserva")
    public Iterable<ReservaEntity> getReserva(){
        return repository.findAll();
    }

    @GetMapping("/getReserva/{id}")
    public ResponseEntity<ReservaEntity> getReservaById(@PathVariable Long id){
        Optional<ReservaEntity> reserva = repository.findById(id);
        return reserva.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/postReserva")
    public ReservaEntity createReserva(@RequestBody ReservaEntity reserva){
        return repository.save(reserva);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ReservaEntity> updateReserva(@PathVariable Long id, @RequestBody ReservaEntity reservaDetails) {
        Optional<ReservaEntity> optionalReserva = repository.findById(id);
        if (optionalReserva.isPresent()) {
            ReservaEntity reserva = optionalReserva.get();
            reserva.setFechaEntrada(reservaDetails.getFechaEntrada());
            reserva.setFechaSalida(reservaDetails.getFechaSalida());
            // Añade los demás campos que desees actualizar
            return ResponseEntity.ok(repository.save(reserva));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
