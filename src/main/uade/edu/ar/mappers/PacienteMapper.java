package main.uade.edu.ar.mappers;

import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.model.Paciente;

public class PacienteMapper {

    private PacienteMapper() {
    }

    public static Paciente toModel(PacienteDto pacienteDto) {
        return new Paciente(
                pacienteDto.getId(),
                pacienteDto.getNombre(),
                pacienteDto.getDni(),
                pacienteDto.getDomicilio(),
                pacienteDto.getEmail(),
                pacienteDto.getApellido(),
                pacienteDto.getEdad(),
                pacienteDto.getGenero()
        );
    }

    public static PacienteDto toDto(Paciente paciente) {
        return new PacienteDto(
                paciente.getId(),
                paciente.getEdad(),
                paciente.getGenero(),
                paciente.getNombre(),
                paciente.getDni(),
                paciente.getDomicilio(),
                paciente.getEmail(),
                paciente.getApellido()
        );
    }

}
