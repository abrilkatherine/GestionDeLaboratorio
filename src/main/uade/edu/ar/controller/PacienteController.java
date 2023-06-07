package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PacienteDao;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.model.Paciente;

import java.util.List;

public class PacienteController {

    private static PacienteController pacienteController;
    private static PacienteDao pacienteDao;

    private static List<Paciente> pacientes;

    private PacienteController() {
    }

    public static synchronized PacienteController getInstance() throws Exception {
        if (pacienteController == null) {
            pacienteController = new PacienteController();
            pacienteDao = new PacienteDao();
            pacientes = pacienteDao.getAll();
        }

        return pacienteController;
    }

    public PacienteDto getPaciente(int id) {
        return pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PacienteController::toDto)
                .orElse(null);
    }

    public PacienteDto getPacientePorDni(int dni) {
        return pacientes.stream()
                .filter(p -> p.getDni() == dni)
                .findFirst()
                .map(PacienteController::toDto)
                .orElse(null);
    }

    public void crearPaciente(PacienteDto pacienteDTO) throws Exception {
        if (getPacientePorDni(pacienteDTO.getDni()) == null) {
            Paciente paciente = toModel(pacienteDTO);
            pacienteDao.save(paciente);
            pacientes.add(paciente);
        }
    }

    public void modificarPaciente(PacienteDto pacienteDto) throws Exception {
        Paciente paciente = pacientes.stream()
                .filter(p -> p.getId() == pacienteDto.getId())
                .findFirst()
                .orElse(null);

        if (paciente != null) {
            paciente.setNombre(pacienteDto.getNombre());
            paciente.setDni(pacienteDto.getDni());
            paciente.setDomicilio(pacienteDto.getDomicilio());
            paciente.setEmail(pacienteDto.getEmail());
            paciente.setApellido(pacienteDto.getApellido());
            paciente.setEdad(pacienteDto.getEdad());
            paciente.setGenero(pacienteDto.getGenero());

            pacienteDao.update(paciente);
        }
    }

    public void borrarPaciente(int id) throws Exception {
        Paciente paciente = pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (paciente != null) {
            pacienteDao.delete(id);
            pacientes.remove(paciente);
        }
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
