package main.uade.edu.ar;

import main.uade.edu.ar.controller.*;
import main.uade.edu.ar.dto.*;
import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.enums.Roles;
import main.uade.edu.ar.enums.TipoResultado;
import main.uade.edu.ar.model.Peticion;

import java.util.List;

import static main.uade.edu.ar.util.DateUtil.getFecha;

public class Main {
    public static void main(String[] args) {
        try {
            PeticionController peticionesController = PeticionController.getInstance();
            SucursalYUsuarioController sucursalYUsuarioController = SucursalYUsuarioController.getInstance();
            PacienteController pacienteController = PacienteController.getInstance();

//            testPacientes(pacienteController, peticionesController);
//            testPeticiones(peticionesController);
//            testUsuarios(sucursalYUsuarioController, peticionesController);
//            testPeticionesConValoresCriticos(peticionesController);
            testPeticionesConValoresReservados(peticionesController);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: Pruebas temporales, cuando tengamos la interfaz grafica, se eliminaran estas pruebas y adicionalmente se agregaran test unitarios.
    private static void testPacientes(PacienteController pacienteController, PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Test", "1dh68lpxz*", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        SucursalDto sucursalA = new SucursalDto(2, 100, "Av Santa Fe", responsable);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "Test", 1234, "dom", "garciatest@gmail.com", "Garcia");

        List<PracticaDto> practicas = List.of(
                new PracticaDto(1, 999, "Análisis 2", 3, 3, new ResultadoDto("60", TipoResultado.CRITICO)),
                new PracticaDto(1, 999, "Análisis 3", 3, 3, new ResultadoDto("60", TipoResultado.CRITICO))
        );

        // ABM Peticiones
        peticionesController.borrarPeticion(1);
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, practicas));

        // ABM Pacientes
        pacienteController.crearPaciente(paciente);

        paciente.setApellido("Paez");

        pacienteController.modificarPaciente(paciente);
        pacienteController.borrarPaciente(1);
    }


    private static void testPeticiones(PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica = new PracticaDto(1, 999, "Análisis", 3, 3);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "Test", 1234, "dom", "test@gmail.com", "Testing");

        // ABM Peticiones
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente));
        peticionesController.crearPeticion(new PeticionDto(2, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, List.of(practica)));
        // TODO: Revisar update de la libreria
        peticionesController.modificarPeticion(new PeticionDto(2, "OSDE", getFecha("2023-05-01"), getFecha("2023-05-02"), sucursal, paciente, List.of(practica)));
        peticionesController.borrarPeticion(2);

        // ABM Prácticas
        peticionesController.crearPractica(1, practica);
        // TODO: Revisar registros duplicados
        peticionesController.modificarPractica(new PracticaDto(1, 111, "Practica 1", 60, 10));
        peticionesController.borrarPractica(1);

        // ABM Resultados

        peticionesController.crearResultado(1, new ResultadoDto("40", TipoResultado.CRITICO));
        peticionesController.modificarResultado(1, new ResultadoDto("50", TipoResultado.CRITICO));
        peticionesController.eliminarResultado(1);
    }

    private static void testUsuarios(SucursalYUsuarioController sucursalYUsuarioController, PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        SucursalDto sucursalA = new SucursalDto(2, 100, "Av Santa Fe", responsable);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "Paciente test", 12349977, "dom", "pereztest@gmail.com", "Perez");

        List<PracticaDto> practicas = List.of(
                new PracticaDto(1, 999, "Análisis de sangre", 3, 3),
                new PracticaDto(2, 999, "Análisis de orina", 3, 3)
        );

        // ABM Peticiones
        peticionesController.borrarPeticion(1);
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, practicas));

        // ABM Usuarios
        sucursalYUsuarioController.crearUsuario(new UsuarioDto(1, "Didy", "", getFecha("1990-06-04"), Roles.LABORTISTA));
        sucursalYUsuarioController.modificarUsuario(new UsuarioDto(1, "Didy", "", getFecha("1990-06-04"), Roles.ADMINISTRADOR));
        sucursalYUsuarioController.eliminarUsuario(1);

        // ABM Sucursales
        sucursalYUsuarioController.crearSucursal(sucursal);
        sucursalYUsuarioController.crearSucursal(sucursalA);
        sucursalYUsuarioController.modificarSucursal(sucursal);
        sucursalYUsuarioController.modificarSucursal(sucursalA);
        sucursalYUsuarioController.borrarSucursal(1);
    }

    private static void testPeticionesConValoresCriticos(PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "13jfso*jd37", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica1 = new PracticaDto(1, 999, "Análisis de sangre", 3, 3);
        PracticaDto practica2 = new PracticaDto(2, 999, "Análisis de sangre", 3, 3);
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "Test", 12345678, "dom", "test@gmail.com", "Gomez");

        // ABM Peticiones
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente));
        peticionesController.crearPeticion(new PeticionDto(2, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, List.of(practica2)));

        // ABM Prácticas
        peticionesController.crearPractica(1, practica1);

        // ABM Resultados
        peticionesController.crearResultado(1, new ResultadoDto("40", TipoResultado.CRITICO));
        peticionesController.crearResultado(2, new ResultadoDto("40", TipoResultado.RESERVADO));

        List<Peticion> listaValoreCriticos = peticionesController.getPeticionesConResultadosCriticos();

        System.out.println("Peticiones con valores críticos:");
        listaValoreCriticos.forEach(peticion -> System.out.println(peticion.getId()));
    }

    private static void testPeticionesConValoresReservados(PeticionController peticionesController) throws Exception {
        UsuarioDto responsable = new UsuarioDto(1, "Hugo", "13jfso*jd37", getFecha("1990-06-04"), Roles.ADMINISTRADOR);
        SucursalDto sucursal = new SucursalDto(1, 100, "Av Santa Fe", responsable);
        PracticaDto practica1 = new PracticaDto(1, 999, "Análisis de sangre", 3, 3);
        PracticaDto practica2 = new PracticaDto(2, 999, "Análisis de sangre", 3, 3);
        PracticaDto practica3 = new PracticaDto(3, 999, "Análisis de sangre", 3, 3, new ResultadoDto("No debe mostrarse", TipoResultado.RESERVADO));
        PacienteDto paciente = new PacienteDto(1, 22, Genero.MASCULINO, "Test", 12345678, "dom", "test@gmail.com", "Gomez");
        ResultadoDto resultado1 = new ResultadoDto("40", TipoResultado.CRITICO);
        ResultadoDto resultado2 = new ResultadoDto("40", TipoResultado.RESERVADO);

        // ABM Peticiones
        peticionesController.crearPeticion(new PeticionDto(1, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente));
        peticionesController.crearPeticion(new PeticionDto(2, "Swiss Medical", getFecha("2023-06-01"), getFecha("2023-06-02"), sucursal, paciente, List.of(practica2)));

        // ABM Prácticas
        peticionesController.crearPractica(1, practica1);
        peticionesController.crearPractica(1, practica3);

        // ABM Resultados
        peticionesController.crearResultado(1, resultado1);
        peticionesController.crearResultado(2, resultado2);

        peticionesController.getPracticasConResultadosReservados(1).forEach(p -> System.out.println("Resultado práctica " + p.getId() + " = " + p.getResultado().getValor()));
    }

}