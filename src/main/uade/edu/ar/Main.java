package main.uade.edu.ar;

import main.uade.edu.ar.controller.*;
import main.uade.edu.ar.dto.*;
import main.uade.edu.ar.enums.Roles;
import main.uade.edu.ar.enums.TipoResultado;

import java.util.List;

import static main.uade.edu.ar.util.DateUtil.getFecha;

public class Main {
    public static void main(String[] args) {
        try {
            // Controller peticiones, prácticas y resultados
            PeticionController peticionesController = PeticionController.getInstance();
            testPeticiones(peticionesController);
            // Controller sucursal y usuario
            SucursalYUsuarioController sucursalYUsuarioController = SucursalYUsuarioController.getInstance();
            testUsuarios(sucursalYUsuarioController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testPeticiones(PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica = new PracticaDto(1, 999, "Análisis de orina", 3, 3);

        // ABM Peticiones
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal));
        peticionesController.crearPeticion(new PeticionDto(2, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, List.of(practica)));
        // TODO: Revisar update de la libreria
         peticionesController.modificarPeticion(new PeticionDto(2, "OSDE", getFecha("2023-05-01"), getFecha("2023-05-02"), sucursal, List.of(practica)));
        peticionesController.borrarPeticion(2);

        // ABM Prácticas
        peticionesController.crearPractica(1, practica);
        // TODO: Revisar registros duplicados
        peticionesController.modificarPractica(new PracticaDto(1, 111, "Oftalmología", 60, 10));
        peticionesController.borrarPractica(1);

        // ABM Resultados

        peticionesController.crearResultado(1, new ResultadoDto("valor", TipoResultado.CRITICO));
        peticionesController.modificarResultado(1, new ResultadoDto("hola", TipoResultado.CRITICO));
        peticionesController.eliminarResultado(1);
    }

    private static void testUsuarios(SucursalYUsuarioController sucursalYUsuarioController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);

        // ABM Usuarios
        sucursalYUsuarioController.crearUsuario(new UsuarioDto(1, "Didy", "", getFecha("1990-06-04"), Roles.LABORTISTA));
        sucursalYUsuarioController.modificarUsuario(new UsuarioDto(1, "Didy", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR));
        sucursalYUsuarioController.eliminarUsuario(1);

        // ABM Sucursales
        sucursalYUsuarioController.crearSucursal(new SucursalDto(1, 100, "Av Santa Fe", responsable));
        sucursalYUsuarioController.modificarSucursal(new SucursalDto(1, 100, "Av Scalabrini Ortiz", responsable));
        sucursalYUsuarioController.borrarSucursal(1);

    }
}