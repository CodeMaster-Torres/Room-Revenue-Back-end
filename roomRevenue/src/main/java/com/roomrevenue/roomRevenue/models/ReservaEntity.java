package com.roomrevenue.roomRevenue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "num_habitacion", referencedColumnName = "id")
    private HabitacionEntity habitacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity cliente;

    @Column(nullable = false)
    private Date fechaEntrada;

    @Column(nullable = false)
    private Date fechaSalida;

    @Column(nullable = false)
    private int numeroHuespedes;

    @Column(nullable = false)
    private double tarifa;

    @Enumerated(EnumType.STRING)
    private EstatusReserva estatus;


}