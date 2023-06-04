package main.uade.edu.ar.dao;

import main.uade.edu.ar.model.Paciente;
import main.uade.edu.ar.util.GenericDAO;

public class PacienteDao extends GenericDAO<Paciente> {

    private static final String fileName = "src/main/resources/pacientes.json";

    public PacienteDao() throws Exception {
        super(Paciente.class, fileName);
    }

}
