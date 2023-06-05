package main.uade.edu.ar;

import main.uade.edu.ar.controller.*;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.enums.Resultado;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {

            //PACIENTE
            PacienteController pacienteController = PacienteController.getInstance();

            pacienteController.crearPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabaza", 123, "", "", "pepe"));
            pacienteController.crearPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabaza", 123, "", "", "pepe"));
            pacienteController.borrarPaciente(1);
            pacienteController.modificarPaciente(new PacienteDto(1, 20, Genero.MASCULINO, "Calabacin", 123, "", "", "pepe"));

            //SUCURSAL
            SucursalController sucursalController = SucursalController.getInstance();

            sucursalController.crearSucursal(new SucursalDto(1, 222, "calle siempre viva", null));
            sucursalController.modificarSucursal(new SucursalDto(1, 555, "calle siempre viva v2", null));
            sucursalController.borrarSucursal(1);

            //PRACTICAS
            PracticaController practicaController = PracticaController.getInstance();

            practicaController.crearPractica(new PracticaDto(5, "Practica 1", 22, 3, true));
            practicaController.modificarPractica(new PracticaDto(5, "Practica 2", 23, 3, true));
            practicaController.borrarPractica(5);


            //PETICIONES
            PeticionController peticionController = PeticionController.getInstance();

            //TODO: FECHAS
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPeticion = formatoFecha.parse("2023-06-01");
            Date fechaEntrega = formatoFecha.parse("2023-06-04");

            peticionController.crearPeticion(new PeticionDto(4, "Osde", fechaPeticion, fechaEntrega, Resultado.CRITICO));
            peticionController.modificarPeticion(new PeticionDto(4, "Osde", fechaPeticion, fechaEntrega, Resultado.RESERVADO));
            peticionController.borrarPeticion(4);

            //USUARIOS
            UsuarioController usuarioController = UsuarioController.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}