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
        JButton homeButton = createButton("Sucursal", Color.WHITE);
        buttonPanel.add(homeButton);

        // Usar BoxLayout con alineación derecha para los otros tres botones
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        JButton aboutButton = createButton("Pacientes", Color.WHITE);
        JButton contactButton = createButton("Practicas", Color.WHITE);
        JButton extraButton = createButton("Peticiones", Color.WHITE);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(aboutButton);
        buttonPanel.add(contactButton);
        buttonPanel.add(extraButton);

        // Agregar el panel de botones al panel del menú
        menuPanel.add(buttonPanel);

        // Crear un panel con CardLayout para contener las diferentes vistas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

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
                if (text.equals("Sucursal")) {
                    cardLayout.show(cardPanel, "sucursalTodas");
                } else if (text.equals("Pacientes")) {
                    // Lógica para mostrar el panel de "Pacientes"
                } else if (text.equals("Practicas")) {
                    // Lógica para mostrar el panel de "Practicas"
                } else if (text.equals("Peticiones")) {
                    // Lógica para mostrar el panel de "Peticiones"
                }
            }
        });
        return button;
    }
}
