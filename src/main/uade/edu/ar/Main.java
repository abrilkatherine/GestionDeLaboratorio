package main.uade.edu.ar;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.model.Genero;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            PacienteController pacienteController = PacienteController.getInstance();

            pacienteController.crearPaciente(new PacienteDto(1, "Calabaza", 123, "", "", "pepe", 20, Genero.MASCULINO));
            pacienteController.crearPaciente(new PacienteDto(2, "Calabaza", 123, "", "", "pepe", 20, Genero.MASCULINO));
            pacienteController.borrarPaciente(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}