package com.procesos.negocio.andres.controllers;

import com.procesos.negocio.andres.models.Usuario;
import com.procesos.negocio.andres.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable long id){
        Optional<Usuario> usuario =usuarioRepository.findById(id);

        return usuario;

    }
    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }
    @GetMapping("/usuarios")
    public List<Usuario>listaUsuarios(){
        return usuarioRepository.findAll();

    }
    @GetMapping("/usuario/{nombre}/{apellidos}")
    public List<Usuario>listarPorNombreApellidos(@PathVariable String nombre, @PathVariable String apellidos){

        return usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);


    }
    @GetMapping("/usuario/apellidos/{apellidos}")
    public List<Usuario>listarPorApellidos( @PathVariable String apellidos){

        return usuarioRepository.findAllByApellidos(apellidos);


    }
    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario>listarPorNombre( @PathVariable String nombre){

        return usuarioRepository.findAllByNombre(nombre);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id ,@RequestBody Usuario usuario){
        Usuario usuarioBD =usuarioRepository.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellidos(usuario.getApellidos());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBD);
            return usuarioBD;


        }catch (Exception e){
            return null;
        }

    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD =usuarioRepository.findById(id).get();
        try{
            usuarioRepository.delete(usuarioBD);
            return usuarioBD;


        }catch (Exception e){
            return null;
        }

    }


}
