package com.procesos.negocio.andres.services;

import com.procesos.negocio.andres.models.Usuario;
import com.procesos.negocio.andres.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public ResponseEntity<Usuario> getUserById(Long id) {
        Optional<Usuario> usuario =usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);

        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Usuario> createUser(Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByNameAndLastName(String nombre, String apellidos) {
        List<Usuario> usuarios = usuarioRepository.findAllByNombreAndApellidos(nombre,apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByLastName(String apellidos) {
        List<Usuario> usuarios = usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> allUsersByName(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Usuario> editUser(Long id, Usuario usuario) {
        Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellidos(usuario.getApellidos());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuarioBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Usuario> deleteUser(Long id) {
        Optional<Usuario> usuarioBD =usuarioRepository.findById(id);
        if(usuarioBD.isPresent()){
            usuarioRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }
}
