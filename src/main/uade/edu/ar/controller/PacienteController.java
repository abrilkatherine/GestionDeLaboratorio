package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PacienteDao;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.mappers.PacienteMapper;
import main.uade.edu.ar.mappers.PeticionMapper;
import main.uade.edu.ar.model.Paciente;
import main.uade.edu.ar.model.Peticion;

import java.util.ArrayList;
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
                .map(PacienteMapper::toDto)
                .orElse(null);
    }

    public PacienteDto getPacientePorDni(int dni) {
        return pacientes.stream()
                .filter(p -> p.getDni() == dni)
                .findFirst()
                .map(PacienteMapper::toDto)
                .orElse(null);
    }

    public void crearPaciente(PacienteDto pacienteDTO) throws Exception {
        if (getPaciente(pacienteDTO.getId()) == null) {
            Paciente paciente = PacienteMapper.toModel(pacienteDTO);
            pacienteDao.save(paciente);
            pacientes.add(paciente);
        }
    }

    public void agregarPeticion(int idPaciente, PeticionDto peticionDto) throws Exception {
        PacienteDto pacienteDto = getPaciente(idPaciente);

        if (pacienteDto != null) {
            if (pacienteDto.getPeticiones() != null) {
                pacienteDto.getPeticiones().add(peticionDto);
            } else {
                List<PeticionDto> peticionesPaciente = new ArrayList<>();
                peticionesPaciente.add(peticionDto);
                pacienteDto.setPeticiones(peticionesPaciente);
            }

            modificarPaciente(pacienteDto);
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
            paciente.setPeticiones(PeticionMapper.toModel(pacienteDto.getPeticiones()));

            pacienteDao.update(paciente);
        }
    }

    public void borrarPaciente(int id) throws Exception {
        Paciente paciente = pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (puedeBorrarse(paciente)) {
            pacienteDao.delete(id);
            pacientes.remove(paciente);
        } else {
            System.out.println("El paciente no cumple las condiciones para ser borrado");
        }
    }

    private boolean puedeBorrarse(Paciente paciente) throws Exception {
        if (paciente == null) {
            return false;
        }

        for (Peticion peticion : paciente.getPeticiones()) {
            if (peticion.tieneResultado()) {
                return false;
            }
        }

        return true;
    }
}
