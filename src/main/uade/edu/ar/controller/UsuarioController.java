package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.UsuarioDao;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.model.Usuario;

import java.util.List;

public class UsuarioController {
    private static UsuarioController usuarioController;
    private static UsuarioDao usuarioDao;
    private static List<Usuario> usuarios;

    private UsuarioController() {
    }

    public static synchronized UsuarioController getInstance() throws Exception {
        if (usuarioController == null) {
            usuarioController = new UsuarioController();
            usuarioDao = new UsuarioDao();
            usuarios = usuarioDao.getAll();
        }

        return usuarioController;
    }

    //TODO: FIX METODOS; add id in model
//    public UsuarioDto getUsuario(int id) {
//        return usuarios.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .map(UsuarioController::toDto)
//                .orElse(null);
//    }

//    public Usuario crearUsuario(UsuarioDto usuarioDTO) {
//        if (getUsuario(usuarioDTO.getId()) == null) {
//            usuarioDao.save(toModel(usuarioDTO));
//        }
//        return null;
//    }
//
//    public void actualizarRol(UsuarioDto usuarioDTO) throws Exception {
//        if (getUsuario(usuarioDTO.getId()) == null) {
//            usuarioDao.save(toModel(usuarioDTO));
//        }
//    }
//
//    public void eliminarUsuario() {
//        Practica usuario = usuarios.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        if (usuario != null) {
//            usuarioDao.delete(id);
//            usuarios.remove(usuario);
//        }
//    }
}

