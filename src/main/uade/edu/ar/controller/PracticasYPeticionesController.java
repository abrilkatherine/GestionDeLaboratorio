package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.model.Peticion;

import java.util.List;

public class PracticasYPeticionesController {

    private static PracticasYPeticionesController practicasYPeticioneController;
    private static PeticionDao peticionDao;

    private static List<Peticion> peticiones;

    private PracticasYPeticionesController() {
    }

    public static synchronized PracticasYPeticionesController getInstance() throws Exception {
        if (practicasYPeticioneController == null) {
            practicasYPeticioneController = new PracticasYPeticionesController();
            peticionDao = new PeticionDao();
            peticiones = peticionDao.getAll();
        }

        return practicasYPeticioneController;
    }

    public PeticionDto getPeticion(int id) {
        return peticiones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PracticasYPeticionesController::toDto)
                .orElse(null);
    }

    public void crearPeticion(PeticionDto peticionDTO) throws Exception {
        if (getPeticion(peticionDTO.getId()) == null) {
            peticionDao.save(toModel(peticionDTO));
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
