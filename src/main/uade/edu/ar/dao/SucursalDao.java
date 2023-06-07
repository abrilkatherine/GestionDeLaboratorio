package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Sucursal;
import main.uade.edu.ar.util.GenericDAO;

public class SucursalDao extends GenericDAO<Sucursal> {

    private static final String fileName = "src/main/resources/sucursales.json";

    public SucursalDao() throws Exception {
        super(Sucursal.class, fileName);
    }
}
