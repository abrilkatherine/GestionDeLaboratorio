package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.UsuarioDao;
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

    //TODO: UPDATE METODOS
    public Usuario crearUsuario() {
        // Lógica para crear un nuevo usuario
        return null;
    }

    public void actualizarRol() {
        // Lógica para actualizar el rol del usuario
    }

    public void eliminarUsuario() {
        // Lógica para eliminar un usuario
    }
}

