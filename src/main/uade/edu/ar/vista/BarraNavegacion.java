package main.uade.edu.ar.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraNavegacion {
    private JPanel menuPanel;
    private JPanel buttonPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public JPanel createNavBarPanel() {
        // Crear un panel para el menú
        menuPanel = new JPanel();
        menuPanel.setBackground(Color.decode("#0080F0"));

        // Crear un panel para los botones del menú
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        // Usar FlowLayout con alineación izquierda para el primer botón
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton homeButton = createButton("Pacientes", Color.WHITE);
        buttonPanel.add(homeButton);

        // Usar BoxLayout con alineación derecha para los otros tres botones
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        JButton sucursalesButton = createButton("Sucursales", Color.WHITE);
        JButton peticionesButton = createButton("Peticiones", Color.WHITE);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(sucursalesButton);
        buttonPanel.add(peticionesButton);

        // Agregar el panel de botones al panel del menú
        menuPanel.add(buttonPanel);

        // Crear un panel con CardLayout para contener las diferentes vistas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Agregar los paneles de vistas al cardPanel
        cardPanel.add(new PacientesTodas().createPanel(), "pacientesTodas");
        cardPanel.add(new SucursalTodas().createPanel(), "sucursalesTodas");
        cardPanel.add(new PeticionesTodas().createPanel(), "peticionesTodas");

        // Agregar el panel del menú y el panel con CardLayout al panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(menuPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    // Método de utilidad para crear botones
    private JButton createButton(String text, Color foregroundColor) {
        JButton button = new JButton(text);
        button.setForeground(foregroundColor);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Pacientes")) {
                    cardLayout.show(cardPanel, "pacientesTodas");
                } else if (text.equals("Sucursales")) {
                    cardLayout.show(cardPanel, "sucursalesTodas");
                } else if (text.equals("Prácticas")) {
                    cardLayout.show(cardPanel, "practicasTodas");
                } else if (text.equals("Peticiones")) {
                    cardLayout.show(cardPanel, "peticionesTodas");
                }
            }
        });
        return button;
    }
}