package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.SucursalDao;
import main.uade.edu.ar.dao.UsuarioDao;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.model.Sucursal;
import main.uade.edu.ar.model.Usuario;

import java.util.List;

public class SucursalYUsuarioController {

    private static SucursalYUsuarioController sucursalController;
    private static SucursalDao sucursalDao;
    private static List<Sucursal> sucursales;
    private static UsuarioDao usuarioDao;
    private static List<Usuario> usuarios;

    private SucursalYUsuarioController() {
    }

    public static synchronized SucursalYUsuarioController getInstance() throws Exception {
        if (sucursalController == null) {
            sucursalController = new SucursalYUsuarioController();
            sucursalDao = new SucursalDao();
            usuarioDao = new UsuarioDao();
            sucursales = sucursalDao.getAll();
            usuarios = usuarioDao.getAll();
        }

        return sucursalController;
    }

    //Sucursal
    public SucursalDto getSucursalPorId(int id) {
        return sucursales.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .map(SucursalYUsuarioController::toDto)
                .orElse(null);
    }

    public void crearSucursal(SucursalDto sucursalDTO) throws Exception {
        if (getSucursalPorId(sucursalDTO.getId()) == null) {
            Sucursal sucursal = toModel(sucursalDTO);
            sucursalDao.save(toModel(sucursalDTO));
            sucursales.add(sucursal);
        }
    }

    public void modificarSucursal(SucursalDto sucursalDTO) throws Exception {
        Sucursal sucursalExistente = sucursales.stream()
                .filter(s -> s.getId() == sucursalDTO.getId())
                .findFirst()
                .orElse(null);

        if (sucursalExistente != null) {
            sucursalDao.update(toModel(sucursalDTO));
        }
    }

    public void borrarSucursal(int id) throws Exception {
        Sucursal sucursalExistente = sucursales.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        if (sucursalExistente != null) {
            sucursalDao.delete(id);
            sucursales.remove(sucursalExistente);
        }
    }

    public static Sucursal toModel(SucursalDto sucursalDto) {
        return new Sucursal(
                sucursalDto.getId(),
                sucursalDto.getNumero(),
                sucursalDto.getDireccion(),
                SucursalYUsuarioController.toModel(sucursalDto.getResponsableTecnico())
        );
    }

    public static SucursalDto toDto(Sucursal sucursal) {
        return new SucursalDto(
                sucursal.getId(),
                sucursal.getNumero(),
                sucursal.getDireccion(),
                SucursalYUsuarioController.toDto(sucursal.getResponsableTecnico())
        );
    }

    //Usuario

    public UsuarioDto getUsuario(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(SucursalYUsuarioController::toDto)
                .orElse(null);
    }

    public Usuario crearUsuario(UsuarioDto usuarioDTO) throws Exception {
        if (getUsuario(usuarioDTO.getId()) == null) {
            Usuario usuario = toModel(usuarioDTO);
            usuarioDao.save(usuario);
            usuarios.add(usuario);
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