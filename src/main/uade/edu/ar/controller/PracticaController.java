package main.uade.edu.ar.controller;

import main.uade.edu.ar.dao.PracticaDao;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.model.Resultado;

import java.util.List;

public class PracticaController {
    private static PracticaController practicaController;
    private static PracticaDao practicaDao;

    private static List<Practica> practicas;

    private PracticaController() {
    }

    public static synchronized PracticaController getInstance() throws Exception {
        if (practicaController == null) {
            practicaController = new PracticaController();
            practicaDao = new PracticaDao();
            practicas = practicaDao.getAll();
        }

        return practicaController;
    }
    public PracticaDto getPractica(int id) {
        return practicas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .map(PracticaController::toDto)
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
