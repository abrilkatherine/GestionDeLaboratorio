package main.uade.edu.ar;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.controller.SucursalController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.SucursalDto;
import main.uade.edu.ar.enums.Genero;

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}