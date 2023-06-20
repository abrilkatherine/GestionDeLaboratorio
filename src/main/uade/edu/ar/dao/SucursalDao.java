package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Sucursal;
import main.uade.edu.ar.util.GenericDAO;
import java.util.List;
import java.util.ArrayList;

public class SucursalDao extends GenericDAO<Sucursal> {

    private static final String fileName = "src/main/resources/sucursales.json";


    public SucursalDao() throws Exception {
        super(Sucursal.class, fileName);
    }
}
