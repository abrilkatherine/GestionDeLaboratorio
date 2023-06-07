package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Peticion;
import main.uade.edu.ar.util.GenericDAO;

public class PeticionDao extends GenericDAO<Peticion> {

    private static final String fileName = "src/main/resources/peticiones.json";

    public PeticionDao() throws Exception {
        super(Peticion.class, fileName);
    }
}
