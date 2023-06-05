package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.SucursalDao;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.model.Sucursal;

import java.util.List;

public class SucursalController {

    private static SucursalController sucursalController;
    private static SucursalDao sucursalDao;
    private static List<Sucursal> sucursales;

    private SucursalController() {
    }

    public static synchronized SucursalController getInstance() throws Exception {
        if (sucursalController == null) {
            sucursalController = new SucursalController();
            sucursalDao = new SucursalDao();
            sucursales = sucursalDao.getAll();
        }

        return sucursalController;
    }

    public SucursalDto getSucursalPorId(int id) {
        return sucursales.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .map(SucursalController::toDto)
                .orElse(null);
    }

    public void crearSucursal(SucursalDto sucursalDTO) throws Exception {
        if (getSucursalPorId(sucursalDTO.getId()) == null) {
            sucursalDao.save(toModel(sucursalDTO));
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
                UsuarioController.toModel(sucursalDto.getResponsableTecnico())
        );
    }

    public static SucursalDto toDto(Sucursal sucursal) {
        return new SucursalDto(
                sucursal.getId(),
                sucursal.getNumero(),
                sucursal.getDireccion(),
                UsuarioController.toDto(sucursal.getResponsableTecnico())
        );
    }

}
