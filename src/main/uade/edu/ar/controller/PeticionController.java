package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.mappers.PeticionMapper;
import main.uade.edu.ar.model.*;

import java.util.List;
import java.util.Optional;

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

    // Peticiones

    public Optional<Peticion> getPeticion(int id) {
        return peticiones.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public void crearPeticion(PeticionDto peticionDTO) throws Exception {
        if (getPeticion(peticionDTO.getId()).isEmpty()) {
            Peticion peticion = PeticionMapper.toModel(peticionDTO);
            peticionDao.save(peticion);
            peticiones.add(peticion);
        }
    }

    // TODO: Fix
    public void modificarPeticion(PeticionDto peticionDTO) throws Exception {
        Peticion peticion = peticiones.stream()
                .filter(p -> p.getId() == peticionDTO.getId())
                .findFirst()
                .orElse(null);

        if (peticion != null) {
            peticion.setObraSocial(peticionDTO.getObraSocial());
            peticion.setFechaCarga(peticionDTO.getFechaCarga());
            peticion.setFechaEntrega(peticionDTO.getFechaEntrega());
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

    // Practicas
    public Optional<Practica> getPractica(int id) {
        return peticiones.stream()
                .flatMap(peticion -> peticion.getPracticas().stream())
                .filter(practica -> practica.getId() == id)
                .findFirst();
    }

    public void crearPractica(int idPeticion, PracticaDto practicaDTO) throws Exception {
        if (getPractica(practicaDTO.getId()).isPresent()) {
            System.out.println("La práctica solicitada ya existe");
            return;
        }

        Peticion peticion = getPeticion(idPeticion)
                .orElse(null);

        if (peticion == null) {
            System.out.println("La petición ingresada no existe");
            return;
        }

        Practica practica = PeticionMapper.toModel(practicaDTO);
        peticion.getPracticas().add(practica);
        peticionDao.update(peticion);
    }

    public void modificarPractica(PracticaDto practicaDTO) throws Exception {
        for (Peticion peticion : peticiones) {
            Optional<Practica> practicaOptional = peticion.getPracticas()
                    .stream()
                    .filter(practica -> practica.getId() == practicaDTO.getId())
                    .findFirst();

            if (practicaOptional.isPresent()) {
                Practica practica = practicaOptional.get();
                practica.setCodigo(practicaDTO.getCodigo());
                practica.setNombre(practicaDTO.getNombre());
                practica.setGrupo(practicaDTO.getGrupo());
                practica.setHorasFaltantes(practicaDTO.getHorasFaltantes());
                practica.setResultado(PeticionMapper.toModel(practicaDTO.getResultado()));

                peticionDao.update(peticion);
                return;
            }
        }

        System.out.println("La práctica solicitada no existe");
    }

    public void borrarPractica(int id) throws Exception {
        for (Peticion peticion : peticiones) {
            Optional<Practica> practicaOptional = peticion.getPracticas()
                    .stream()
                    .filter(practica -> practica.getId() == id)
                    .findFirst();

            if (practicaOptional.isPresent()) {
                Practica practica = practicaOptional.get();
                peticion.getPracticas().remove(practica);
                peticionDao.update(peticion);
                return;
            }
        }

        System.out.println("La práctica solicitada no existe");
    }

}
