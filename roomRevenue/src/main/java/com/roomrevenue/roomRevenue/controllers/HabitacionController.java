package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.models.HabitacionEntity;
import com.roomrevenue.roomRevenue.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/getHabitacion")
    public Iterable<HabitacionEntity> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    @GetMapping("/getHabitacion/{id}")
    public ResponseEntity<HabitacionEntity> getHabitacionById(@PathVariable Long id) {
        Optional<HabitacionEntity> habitacion = habitacionRepository.findById(id);
        return habitacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/postHabitacion")
    public HabitacionEntity createHabitacion(@RequestBody HabitacionEntity habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @PutMapping("/updateHabitacion/{id}")
    public ResponseEntity<HabitacionEntity> updateHabitacion(@PathVariable Long id, @RequestBody HabitacionEntity habitacionDetails) {
        Optional<HabitacionEntity> optionalHabitacion = habitacionRepository.findById(id);
        if (optionalHabitacion.isPresent()) {
            HabitacionEntity habitacion = optionalHabitacion.get();
            habitacion.setTipoHabitacion(habitacionDetails.getTipoHabitacion());
            // Añade los demás campos que desees actualizar
            return ResponseEntity.ok(habitacionRepository.save(habitacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteHabitacion/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}