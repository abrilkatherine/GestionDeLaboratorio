package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PeticionDao;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.enums.TipoResultado;
import main.uade.edu.ar.mappers.PeticionMapper;
import main.uade.edu.ar.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PeticionDto> getAllPeticiones(){
        return peticiones.stream()
                .map(PeticionMapper::toDto)
                .collect(Collectors.toList());
    }

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
            System.out.println("Se creó la petición con id: " + peticion.getId());
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
            peticion.setId(peticionDTO.getId());
            peticion.setSucursal(peticion.getSucursal());
            peticion.setPaciente(peticion.getPaciente());
            peticion.setPracticas(peticion.getPracticas());
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
    public Practica getPractica(int id) {
        return peticiones.stream()
                .flatMap(peticion -> peticion.getPracticas().stream())
                .filter(practica -> practica.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void crearPractica(int idPeticion, PracticaDto practicaDTO) throws Exception {
        if (getPractica(practicaDTO.getId()) != null) {
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
        System.out.println("Se creó la práctica con id " + practica.getId() + " para la petición " + peticion.getId());
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

    // Resultados

    public void crearResultado(int idPractica, ResultadoDto resultadoDTO) throws Exception {
        for (Peticion peticion : peticiones) {
            Optional<Practica> practicaOptional = peticion.getPracticas()
                    .stream()
                    .filter(practica -> practica.getId() == idPractica)
                    .findFirst();

            if (practicaOptional.isPresent()) {
                Practica practica = practicaOptional.get();
                practica.setResultado(PeticionMapper.toModel(resultadoDTO));
                peticionDao.update(peticion);
                return;
            }
        }

        System.out.println("La práctica solicitada no existe");
    }

    public void modificarResultado(int idPractica, ResultadoDto resultadoDTO) throws Exception {
        crearResultado(idPractica, resultadoDTO);
    }

    public void eliminarResultado(int idPractica) throws Exception {
        crearResultado(idPractica, null);
    }

    public List<Peticion> getPeticionesConResultadosCriticos() {
        List<Peticion> peticionesConResultadosCriticos = new ArrayList<>();

        for (Peticion peticion : peticiones) {
            boolean tieneResultadosCriticos = peticion.getPracticas()
                    .stream()
                    .anyMatch(Practica::esCritica);

            if (tieneResultadosCriticos) {
                peticionesConResultadosCriticos.add(peticion);
            }
        }

        return peticionesConResultadosCriticos;
    }

    public List<Practica> getPracticasConResultadosReservados(int idPeticion) {
        Peticion peticion = getPeticion(idPeticion).orElse(null);

        if (peticion == null) {
            System.out.println("La petición solicitada no existe");
            return new ArrayList<>();
        }

        List<Practica> practicasConResultadosOcultos = new ArrayList<>();

        for (Practica practica : peticion.getPracticas()) {
            if (practica.getResultado() != null && practica.esReservada()) {
                practica.ocultarResultado();
            }
            practicasConResultadosOcultos.add(practica);
        }

        return practicasConResultadosOcultos;
    }

}
