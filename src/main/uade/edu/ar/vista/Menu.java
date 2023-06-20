package main.uade.edu.ar.vista;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private static BarraNavegacion barraNavegacion;
    private static SucursalTodas sucursalTodas;
    private static JPanel cardPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
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
        sucursalTodas = new SucursalTodas();
        JPanel sucursalTodasPanel = sucursalTodas.createPanel();
        cardPanel.add(sucursalTodasPanel, "sucursalTodas");

        // Mostrar el panel inicial
        cardLayout.show(cardPanel, "sucursalTodas");

        // Mostrar la ventana
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
