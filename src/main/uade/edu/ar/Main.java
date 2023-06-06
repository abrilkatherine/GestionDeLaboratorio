package main.uade.edu.ar;

import main.uade.edu.ar.controller.*;
import main.uade.edu.ar.dto.*;
import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.enums.Roles;
import main.uade.edu.ar.enums.TipoResultado;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        try {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPeticion = formatoFecha.parse("2023-06-01");
            Date fechaEntrega = formatoFecha.parse("2023-06-04");

            //PACIENTE
            PacienteController pacienteController = PacienteController.getInstance();

            pacienteController.crearPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabaza", 123, "", "", "pepe"));
            pacienteController.crearPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabaza", 123, "", "", "pepe"));
            pacienteController.borrarPaciente(1);
            pacienteController.modificarPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabacin", 123, "", "", "pepe"));

            //SUCURSAL
            SucursalController sucursalController = SucursalController.getInstance();

            sucursalController.crearSucursal(new SucursalDto(1, 222, "calle siempre viva", new UsuarioDto(1, "", "", formatoFecha.parse("2023-06-01"), Roles.LABORTISTA)));
            sucursalController.modificarSucursal(new SucursalDto(1, 555, "calle siempre viva v2", new UsuarioDto(1, "", "", formatoFecha.parse("2023-06-01"), Roles.LABORTISTA)));
            sucursalController.borrarSucursal(1);

            //PRACTICAS Y PETICIONES
            PeticionYPracticaController peticionYPracticaController = PeticionYPracticaController.getInstance();

            peticionYPracticaController.crearPractica(new PracticaDto(5, 10, "Practica 1", 22, 3, new ResultadoDto("lalala", TipoResultado.CRITICO)));
            peticionYPracticaController.modificarPractica(new PracticaDto(5, 11, "Practica 2", 23, 3, new ResultadoDto("lalala", TipoResultado.RESERVADO)));
            peticionYPracticaController.borrarPractica(5);
            peticionYPracticaController.crearPeticion(new PeticionDto(4, "Osde", fechaPeticion, fechaEntrega, TipoResultado.CRITICO, null, null));
            peticionYPracticaController.modificarPeticion(new PeticionDto(4, "Osde", fechaPeticion, fechaEntrega, TipoResultado.RESERVADO, null, null));
            peticionYPracticaController.borrarPeticion(4);

            //USUARIOS
            UsuarioController usuarioController = UsuarioController.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}