package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dao.PracticaDao;
import main.uade.edu.ar.dao.SucursalDao;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.model.Peticion;
import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.model.Resultado;
import main.uade.edu.ar.model.Sucursal;

import java.util.List;

public class PeticionYPracticaController {
    private static PeticionYPracticaController peticionYPracticaController;
    private static PeticionDao peticionDao;
    private static PracticaDao practicaDao;
    private static List<Peticion> peticiones;
    private static List<Practica> practicas;
    private PeticionYPracticaController() {
    }

    public static synchronized PeticionYPracticaController getInstance() throws Exception {
        if (peticionYPracticaController == null) {
            peticionYPracticaController = new PeticionYPracticaController();
            peticionDao = new PeticionDao();
            practicaDao = new PracticaDao();
            peticiones = peticionDao.getAll();
            practicas = practicaDao.getAll();
        }

        return peticionYPracticaController;
    }

    // Peticiones

    public PeticionDto getPeticion(int id) {
        return peticiones.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PeticionYPracticaController::toDto)
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
                peticionDto.getResultado(),
                peticionDto.getPaciente(),
                peticionDto.getPracticas(),
                peticionDto.getSucursal()
        );
    }

        public static PeticionDto toDto(Peticion peticion) {
        return new PeticionDto(
                peticion.getId(),
                peticion.getObraSocial(),
                peticion.getFechaCarga(),
                peticion.getFechaEntrega(),
                peticion.getResultado(),
                peticion.getPaciente(),
                peticion.getPracticas(),
                peticion.getSucursal()

        );
    }

    // Practicas

    public PracticaDto getPractica(int id) {
        return practicas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PeticionYPracticaController::toDto)
                .orElse(null);
    }
    public void crearPractica(PracticaDto practicaDTO) throws Exception {
        if (getPractica(practicaDTO.getId()) == null) {
            practicaDao.save(toModel(practicaDTO));
        }
    }
    public void modificarPractica(PracticaDto practicaDTO) throws Exception {
        Practica practica = practicas.stream()
                .filter(p -> p.getId() == practicaDTO.getId())
                .findFirst()
                .orElse(null);

        if (practica != null) {
            practicaDao.update(toModel(practicaDTO));
        }
    }
    public void borrarPractica(int id) throws Exception {
        Practica practica = practicas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if (practica != null) {
            practicaDao.delete(id);
            practicas.remove(practica);
        }
    }
    public static Practica toModel(PracticaDto practicaDto) {
        return new Practica(
                practicaDto.getId(),
                practicaDto.getCodigo(),
                practicaDto.getNombre(),
                practicaDto.getGrupo(),
                practicaDto.getHorasFaltantes(),
                toModel(practicaDto.getResultado())
        );
    }

    public static PracticaDto toDto(Practica practica) {
        return new PracticaDto(
                practica.getId(),
                practica.getCodigo(),
                practica.getNombre(),
                practica.getGrupo(),
                practica.getHorasFaltantes(),
                toDto(practica.getResultado())
        );
    }

    public static ResultadoDto toDto(Resultado resultado) {
        return new ResultadoDto(
                resultado.getValor(),
                resultado.getTipoResultado()
        );
    }

    public static Resultado toModel(ResultadoDto resultadoDto) {
        return new Resultado(
                resultadoDto.getValor(),
                resultadoDto.getTipoResultado()
        );
    }


}
