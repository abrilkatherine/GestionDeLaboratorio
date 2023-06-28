// Clase Menu
package main.uade.edu.ar.vista;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.controller.PeticionController;
import main.uade.edu.ar.controller.SucursalYUsuarioController;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private static BarraNavegacion barraNavegacion;
    private static SucursalTodas sucursalTodas;
    private static PacientesTodas pacienteTodas;
    private static UsuariosTodos usuariosTodos;
    private static PeticionesTodas peticionesTodas;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;
    private static SucursalYUsuarioController sucursalYUsuarioController;
    private static PacienteController pacienteController;
    private static PeticionController peticionController;

    public static void main(String[] args) {
        try {
            sucursalYUsuarioController = SucursalYUsuarioController.getInstance(); //Obtenemos la instancia del controller
            pacienteController = PacienteController.getInstance();
            peticionController = PeticionController.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowMenu();
            }
        });
    }

    private static void createAndShowMenu() {
        // Crear una instancia de JFrame para el menú
        JFrame frame = new JFrame("Menú");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear una instancia de BarraNavegacion y obtener su panel
        barraNavegacion = new BarraNavegacion();
        JPanel navBarPanel = barraNavegacion.createNavBarPanel();

        // Agregar el panel de barra de navegación al JFrame
        frame.add(navBarPanel, BorderLayout.NORTH);

        // Crear un panel con CardLayout para contener las diferentes vistas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Agregar el panel con CardLayout al JFrame
        frame.add(cardPanel, BorderLayout.CENTER);

        // Crear una instancia de SucursalTodas y obtener su panel
        sucursalTodas = new SucursalTodas(sucursalYUsuarioController);
        JPanel sucursalTodasPanel = sucursalTodas.createPanel();
        cardPanel.add(sucursalTodasPanel, "SucursalTodas");

        // Crear una instancia de PacientesTodas y obtener su panel
        pacienteTodas = new PacientesTodas(pacienteController);
        JPanel pacientesTodasPanel = pacienteTodas.createPanel();
        cardPanel.add(pacientesTodasPanel, "PacientesTodas");


        // Crear una instancia de PeticionesTodas y obtener su panel
        peticionesTodas = new PeticionesTodas(peticionController);
        JPanel peticionesTodasPanel = peticionesTodas.createPanel();
        cardPanel.add(peticionesTodasPanel, "PeticionesTodas");

        // Crear una instancia de UsuariosTodos y obtener su panel
        usuariosTodos = new UsuariosTodos(sucursalYUsuarioController);
        JPanel usuariosTodosPanel = usuariosTodos.createPanel();
        cardPanel.add(usuariosTodosPanel, "UsuariosTodos");

        // Mostrar la ventana
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
