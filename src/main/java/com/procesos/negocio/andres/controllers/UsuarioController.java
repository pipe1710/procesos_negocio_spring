package com.procesos.negocio.andres.controllers;

import com.procesos.negocio.andres.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {
    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Andres");
        usuario.setApellidos("Montaño Sanchez");
        usuario.setDireccion("cra 5 milanes");
        usuario.setDocumento("1007");
        usuario.setFechaNacimiento(new Date(2022,9,15));
        usuario.setTelefono("31772");
        return usuario;

    }
}
