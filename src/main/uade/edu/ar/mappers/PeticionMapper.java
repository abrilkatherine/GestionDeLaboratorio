package main.uade.edu.ar.mappers;

import main.uade.edu.ar.controller.SucursalYUsuarioController;
import main.uade.edu.ar.dto.PeticionDto;
import main.uade.edu.ar.dto.PracticaDto;
import main.uade.edu.ar.dto.ResultadoDto;
import main.uade.edu.ar.model.Peticion;
import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.model.Resultado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeticionMapper {

    private PeticionMapper(){
    }

    public static Peticion toModel(PeticionDto peticionDto) {
        return new Peticion(
                peticionDto.getId(),
                peticionDto.getObraSocial(),
                peticionDto.getFechaCarga(),
                peticionDto.getFechaEntrega(),
                SucursalYUsuarioController.toModel(peticionDto.getSucursal()),
                practicaDTOtoModel(peticionDto.getPracticas())
        );
    }

    public static PeticionDto toDto(Peticion peticion) {
        return new PeticionDto(
                peticion.getId(),
                peticion.getObraSocial(),
                peticion.getFechaCarga(),
                peticion.getFechaEntrega(),
                SucursalYUsuarioController.toDto(peticion.getSucursal()),
                practicaModeltoDto(peticion.getPracticas())
        );
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

    public static List<Practica> practicaDTOtoModel(List<PracticaDto> practicasDto) {
        if (practicasDto == null) {
            return new ArrayList<>();
        }

        return practicasDto.stream()
                .map(PeticionMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<PracticaDto> practicaModeltoDto(List<Practica> practicas) {
        if (practicas == null) {
            return new ArrayList<>();
        }

        return practicas.stream()
                .map(PeticionMapper::toDto)
                .collect(Collectors.toList());
    }

    public static ResultadoDto toDto(Resultado resultado) {
        if (resultado == null) {
            return null;
        }

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

    public static List<Peticion> toModel(List<PeticionDto> peticionesDto) {
        if (peticionesDto == null) {
            return new ArrayList<>();
        }

        return peticionesDto.stream()
                .map(PeticionMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<PeticionDto> toDto(List<Peticion> peticiones) {
        if (peticiones == null) {
            return new ArrayList<>();
        }

        return peticiones.stream()
                .map(PeticionMapper::toDto)
                .collect(Collectors.toList());
    }

}
