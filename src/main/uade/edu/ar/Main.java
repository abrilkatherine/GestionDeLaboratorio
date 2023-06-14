package main.uade.edu.ar;

import main.uade.edu.ar.controller.*;
import main.uade.edu.ar.dto.*;
import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.enums.Roles;
import main.uade.edu.ar.enums.TipoResultado;

import java.util.List;

import static main.uade.edu.ar.util.DateUtil.getFecha;

public class Main {
    public static void main(String[] args) {
        try {
            PeticionController peticionesController = PeticionController.getInstance();
            SucursalYUsuarioController sucursalYUsuarioController = SucursalYUsuarioController.getInstance();
            PacienteController pacienteController = PacienteController.getInstance();

            testPacientes(pacienteController, peticionesController);
//            testPeticiones(peticionesController);
//            testUsuarios(sucursalYUsuarioController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testPacientes(PacienteController pacienteController, PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica = new PracticaDto(1, 999, "Análisis de orina", 3, 3);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "nombre", 1234, "dom", "@algo", "apellido");

        List<PracticaDto> practicas = List.of(
                new PracticaDto(1, 999, "Análisis de orina", 3, 3, new ResultadoDto("valor", TipoResultado.CRITICO)),
                new PracticaDto(1, 999, "Análisis de orina", 3, 3, new ResultadoDto("valor", TipoResultado.CRITICO))

//        new PracticaDto(1, 999, "Análisis de orina", 3, 3),
//                new PracticaDto(1, 999, "Análisis de orina", 3, 3, new ResultadoDto("valor", TipoResultado.CRITICO))
        );

        // ABM Peticiones
        peticionesController.borrarPeticion(1);
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, practicas));

        pacienteController.crearPaciente(paciente);

        paciente.setApellido("nuevo apellido");

        pacienteController.modificarPaciente(paciente);
        pacienteController.borrarPaciente(1);
    }

    private static void testPeticiones(PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica = new PracticaDto(1, 999, "Análisis de orina", 3, 3);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "nombre", 1234, "dom", "@algo", "apellido");

        // ABM Peticiones
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente));
        peticionesController.crearPeticion(new PeticionDto(2, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, List.of(practica)));
        // TODO: Revisar update de la libreria
        peticionesController.modificarPeticion(new PeticionDto(2, "OSDE", getFecha("2023-05-01"), getFecha("2023-05-02"), sucursal, paciente, List.of(practica)));
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
        sucursalYUsuarioController.crearSucursal(sucursal);
        sucursalYUsuarioController.modificarSucursal(sucursal);
        sucursalYUsuarioController.borrarSucursal(1);
    }

}