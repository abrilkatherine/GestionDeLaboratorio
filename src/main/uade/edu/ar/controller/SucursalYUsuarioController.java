package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dao.SucursalDao;
import main.uade.edu.ar.dao.UsuarioDao;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.dto.UsuarioDto;
import main.uade.edu.ar.model.Peticion;
import main.uade.edu.ar.model.Sucursal;
import main.uade.edu.ar.model.Usuario;


import java.util.List;
import java.util.stream.Collectors;

public class SucursalYUsuarioController {

    private static SucursalYUsuarioController sucursalController;
    private static SucursalDao sucursalDao;
    private static PeticionDao peticionDao;
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
            peticionDao = new PeticionDao();
            sucursales = sucursalDao.getAll();
            usuarios = usuarioDao.getAll();
        }

        return sucursalController;
    }

    //Sucursal
    public List<SucursalDto> getAllSucursales() {
        return sucursales.stream()
                .map(SucursalYUsuarioController::toDto)
                .collect(Collectors.toList());
    }
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

    public Sucursal getSucursalRandom(int sucursalEliminadaId) throws Exception {

        return sucursalDao.getAll()
                .stream()
                .filter(s -> s.getId() != sucursalEliminadaId)
                .findFirst()
                .orElse(null);
    }

    public void modificarSucursal(SucursalDto sucursalDTO) throws Exception {
        Sucursal sucursalExistente = sucursales.stream()
                .filter(s -> s.getId() == sucursalDTO.getId())
                .findFirst()
                .orElse(null);

        if (sucursalExistente != null) {
            sucursalExistente.setDireccion(sucursalDTO.getDireccion());
            sucursalExistente.setId(sucursalDTO.getId());
            sucursalExistente.setNumero(sucursalDTO.getNumero());
            sucursalExistente.setResponsableTecnico(toModel(sucursalDTO.getResponsableTecnico()));
            sucursalDao.update(sucursalExistente);
        }
    }

    public void borrarSucursal(int id) throws Exception {
        Sucursal sucursal = sucursales.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        System.out.print(sucursalDao.getAll());

        if (puedeBorrarse(sucursal)) {
            List<Peticion> peticiones = peticionDao.getAll()
                    .stream()
                    .filter(peticion -> peticion.getSucursal().getId() == sucursal.getId())
                    .toList();

            Sucursal sucursalADerivar = getSucursalRandom(id);

            for (Peticion peticion : peticiones) {
                peticion.setSucursal(sucursalADerivar);
                peticionDao.update(peticion);
            }

            sucursalDao.delete(id);
            sucursales.remove(sucursal);
        } else {
            throw new Exception("La sucursal no cumple las condiciones para ser borrada");
        }
    }

    private boolean puedeBorrarse(Sucursal sucursal) throws Exception {
        if (sucursal == null) {
            return false;
        }

        List<Peticion> peticiones = peticionDao.getAll()
                .stream()
                .filter(peticion -> peticion.getSucursal().getId() == sucursal.getId())
                .toList();

        for (Peticion peticion : peticiones) {
            if (peticion.tieneResultado()) {
                return false;
            }
        }

        return true;
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

    public List<UsuarioDto> getAllUsuarios() {
        return usuarios.stream()
                .map(SucursalYUsuarioController::toDto)
                .collect(Collectors.toList());
    }

    public Usuario crearUsuario(UsuarioDto usuarioDTO) throws Exception {
        if (getUsuario(usuarioDTO.getId()) == null) {
            Usuario usuario = toModel(usuarioDTO);
            usuarioDao.save(usuario);
            usuarios.add(usuario);
        }
        return null;
    }

    public void modificarUsuario(UsuarioDto usuarioDTO) throws Exception {
        Usuario usuarioExistente = usuarios.stream()
                .filter(u -> u.getId() == usuarioDTO.getId())
                .findFirst()
                .orElse(null);

        if (usuarioExistente != null) {
            usuarioExistente.setId(usuarioDTO.getId());
            usuarioExistente.setNombre(usuarioDTO.getNombre());
            usuarioExistente.setContrasenia(usuarioDTO.getContrasenia());
            usuarioExistente.setRol(usuarioExistente.getRol());
            usuarioExistente.setNacimiento(usuarioDTO.getNacimiento());
            usuarioDao.update(toModel(usuarioDTO));
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
