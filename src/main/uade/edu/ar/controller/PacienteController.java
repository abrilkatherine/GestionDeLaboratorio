package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PacienteDao;
import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.mappers.PacienteMapper;
import main.uade.edu.ar.model.Paciente;
import main.uade.edu.ar.model.Peticion;

import java.util.List;

public class PacienteController {

    private static PacienteController pacienteController;
    private static PacienteDao pacienteDao;
    private static PeticionDao peticionDao;
    private static List<Paciente> pacientes;

    private PacienteController() {
    }

    public static synchronized PacienteController getInstance() throws Exception {
        if (pacienteController == null) {
            pacienteController = new PacienteController();
            pacienteDao = new PacienteDao();
            peticionDao = new PeticionDao();
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
        if (getPacientePorDni(pacienteDTO.getDni()) == null) {
            Paciente paciente = PacienteMapper.toModel(pacienteDTO);
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

    // TODO: AAA - No se puede dar de baja si tiene alguna peticion con resultado
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

        List<Peticion> peticiones = peticionDao.getAll()
                .stream()
                .filter(peticion -> peticion.getPaciente().getId() == paciente.getId())
                .toList();

        for (Peticion peticion : peticiones) {
            if (peticion.tieneResultado()) {
                return false;
            }
        }

        return true;
    }
}
