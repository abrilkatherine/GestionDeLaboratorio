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

    public UsuarioDto getUsuario(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(UsuarioController::toDto)
                .orElse(null);
    }

    public Usuario crearUsuario(UsuarioDto usuarioDTO) throws Exception {
        if (getUsuario(usuarioDTO.getId()) == null) {
            usuarioDao.save(toModel(usuarioDTO));
        }
        return null;
    }

    public void actualizarRol(UsuarioDto usuarioDTO) throws Exception {
        if (getUsuario(usuarioDTO.getId()) == null) {
            usuarioDao.save(toModel(usuarioDTO));
        }
    }

    public void eliminarUsuario(int id) throws Exception {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            usuarioDao.delete(id);
            usuarios.remove(usuario);
        }
    }

    public static Usuario toModel(UsuarioDto usuarioDto) {
        return new Usuario(
                usuarioDto.getId(),
                usuarioDto.getNombre(),
                usuarioDto.getContrasenia(),
                usuarioDto.getNacimiento(),
                usuarioDto.getRol()
        );
    }

    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getContrasenia(),
                usuario.getNacimiento(),
                usuario.getRol()
        );
    }
}

