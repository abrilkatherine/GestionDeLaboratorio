package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PacienteDto;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.model.Paciente;
import main.uade.edu.ar.model.Peticion;

import java.util.List;

public class PeticionController {
    private static PeticionController peticionController;
    private static PeticionDao peticionDao;

    private static List<Peticion> peticiones;

    private PeticionController() {
    }

    public static synchronized PeticionController getInstance() throws Exception {
        if (peticionController == null) {
            peticionController = new PeticionController();
            peticionDao = new PeticionDao();
            peticiones = peticionDao.getAll();
        }

        return peticionController;
    }

    public PeticionDto getPeticion(int id) {
        return peticiones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PeticionController::toDto)
                .orElse(null);
    }

    public void crearPeticion(PeticionDto peticionDTO) throws Exception {
        if (getPeticion(peticionDTO.getId()) == null) {
            peticionDao.save(toModel(peticionDTO));
        }
    }

    public void modificarPeticion(PeticionDto peticionDTO) throws Exception {
        Peticion peticion = peticiones.stream()
                .filter(p -> p.getId() == peticionDTO.getId())
                .findFirst()
                .orElse(null);

        if (peticion != null) {
            peticion.setObraSocial(peticionDTO.getObraSocial());
            peticion.setFechaCarga(peticionDTO.getFechaCarga());
            peticion.setFechaEntrega(peticionDTO.getFechaEntrega());
            peticion.setResultado(peticionDTO.getResultado());
            peticionDao.update(peticion);
        }
    }
    public void borrarPeticion(int id) throws Exception {
        Peticion peticion = peticiones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (peticion != null) {
            peticionDao.delete(id);
            peticiones.remove(peticion);
        }
    }
    public static Peticion toModel(PeticionDto peticionDto) {
        return new Peticion(
                peticionDto.getId(),
                peticionDto.getObraSocial(),
                peticionDto.getFechaCarga(),
                peticionDto.getFechaEntrega(),
                peticionDto.getResultado()
        );
    }

        public static PeticionDto toDto(Peticion peticion) {
        return new PeticionDto(
                peticion.getId(),
                peticion.getObraSocial(),
                peticion.getFechaCarga(),
                peticion.getFechaEntrega(),
                peticion.getResultado()

        );
    }
}
