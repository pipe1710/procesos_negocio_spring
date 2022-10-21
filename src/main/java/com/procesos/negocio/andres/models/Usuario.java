package com.procesos.negocio.andres.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    @NotBlank(message = "Nombre No debe ser null, ni blanco")
    private String nombre;
    @Column(length = 300,nullable = false)
    @NotBlank(message = "apellido No debe ser null, ni blanco")
    private String apellidos;
    @Column(length = 20,nullable = false,unique = true)
    @NotBlank(message = "documento No debe ser null, ni blanco")
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column(length = 20)
    private String telefono;


}
