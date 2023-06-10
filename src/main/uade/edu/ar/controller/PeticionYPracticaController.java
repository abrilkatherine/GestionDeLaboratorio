package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.model.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PeticionYPracticaController {
    private static PeticionYPracticaController peticionYPracticaController;
    private static PeticionDao peticionDao;
    private static List<Peticion> peticiones;

    private PeticionYPracticaController() {
    }

    public static synchronized PeticionYPracticaController getInstance() throws Exception {
        if (peticionYPracticaController == null) {
            peticionYPracticaController = new PeticionYPracticaController();
            peticionDao = new PeticionDao();
            peticiones = peticionDao.getAll();
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
            Peticion peticion = toModel(peticionDTO);
            peticionDao.save(peticion);
            peticiones.add(peticion);
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
                SucursalYUsuarioController.toModel(peticionDto.getSucursal()),
                toModel(peticionDto.getPracticas())
        );
    }

    public static PeticionDto toDto(Peticion peticion) {
        return new PeticionDto(
                peticion.getId(),
                peticion.getObraSocial(),
                peticion.getFechaCarga(),
                peticion.getFechaEntrega(),
                SucursalYUsuarioController.toDto(peticion.getSucursal()),
                toDto(peticion.getPracticas())
        );
    }

    // Practicas
/*
    public PracticaDto getPractica(int id) {
        return practicas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PeticionYPracticaController::toDto)
                .orElse(null);
    }

    public void crearPractica(PracticaDto practicaDTO) throws Exception {
        if (getPractica(practicaDTO.getId()) == null) {
            Practica practica = toModel(practicaDTO);
            practicaDao.save(toModel(practicaDTO));
            practicas.add(practica);
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
*/
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

    private static List<Practica> toModel(List<PracticaDto> practicasDto) {
        if (practicasDto == null) {
            return Collections.emptyList();
        }

        return practicasDto.stream()
                .map(PeticionYPracticaController::toModel)
                .collect(Collectors.toList());
    }

    private static List<PracticaDto> toDto(List<Practica> practicas) {
        if (practicas == null) {
            return Collections.emptyList();
        }

        return practicas.stream()
                .map(PeticionYPracticaController::toDto)
                .collect(Collectors.toList());
    }

    public static ResultadoDto toDto(Resultado resultado) {
        return new ResultadoDto(
                resultado.getValor(),
                resultado.getTipoResultado()
        );
    }

    public static Resultado toModel(ResultadoDto resultadoDto) {
        if (resultadoDto == null) {
            return null;
        }

        return new Resultado(
                resultadoDto.getValor(),
                resultadoDto.getTipoResultado()
        );
    }

}
