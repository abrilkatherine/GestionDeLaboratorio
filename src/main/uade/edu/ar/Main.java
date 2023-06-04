package main.uade.edu.ar;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.controller.PracticasYPeticionesController;
import main.uade.edu.ar.controller.SucursalController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.enums.Genero;
import main.uade.edu.ar.enums.Resultado;

import java.text.SimpleDateFormat;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {

            //PACIENTE
            PacienteController pacienteController = PacienteController.getInstance();

            pacienteController.crearPaciente(new PacienteDto(1, "Calabaza", 123, "", "", "pepe", 20, Genero.MASCULINO));
            pacienteController.crearPaciente(new PacienteDto(2, "Calabaza", 123, "", "", "pepe", 20, Genero.MASCULINO));
            pacienteController.borrarPaciente(1);
            pacienteController.modificarPaciente(new PacienteDto(2, "Calabaza", 123, "", "", "alpaca", 20, Genero.MASCULINO));

            //SUCURSAL
            SucursalController sucursalController = SucursalController.getInstance();

            sucursalController.crearSucursal(new SucursalDto(1, 222, "calle siempre viva"));
            sucursalController.modificarSucursal(new SucursalDto(1, 555, "calle siempre viva v2"));
            sucursalController.borrarSucursal(1);

            //PRACTICAS Y PETICIONES
            PracticasYPeticionesController practicasYPeticionesController = PracticasYPeticionesController.getInstance();

            //TODO: FECHAS
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPeticion = formatoFecha.parse("2023-06-01");
            Date fechaEntrega = formatoFecha.parse("2023-06-04");

            practicasYPeticionesController.crearPeticion(new PeticionDto(4, "Osde", fechaPeticion, fechaEntrega, Resultado.CRITICO));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}