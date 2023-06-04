package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Practica;
import main.uade.edu.ar.util.GenericDAO;

public class PracticaDao extends GenericDAO<Practica> {
    private static final String fileName = "src/main/resources/practicas.json";

    public PracticaDao() throws Exception {
        super(Practica.class, fileName);
    }
}
