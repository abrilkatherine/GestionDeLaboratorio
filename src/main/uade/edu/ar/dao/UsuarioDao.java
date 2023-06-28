package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Usuario;
import main.uade.edu.ar.util.GenericDAO;

public class UsuarioDao extends GenericDAO<Usuario> {

    private static final String fileName = "src/main/resources/usuarios.json";

    public UsuarioDao() throws Exception {
        super(Usuario.class, fileName);
    }
}
