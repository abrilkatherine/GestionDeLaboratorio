package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import main.uade.edu.ar.dto.PracticaDto;

public class PracticasXPeticion extends JDialog {
    private JPanel contentPane;
    private JTable practicasTable;
    private JButton agregarButton;

    private List<PracticaDto> practicas;

    public PracticasXPeticion(List<PracticaDto> practicas) {
        this.practicas = practicas;
        initializeUI();
        cargarDatos();
    }

    private void initializeUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        // Panel de título
        JLabel tituloLabel = new JLabel("Prácticas");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel tituloPanel = new JPanel();
        tituloPanel.add(tituloLabel);
        contentPane.add(tituloPanel, BorderLayout.NORTH);

        // Modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Id", "Nombre", "Info", "Eliminar"}, 0);
        practicasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(practicasTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botón Agregar
        agregarButton = new JButton("Agregar");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        contentPane.add(buttonPanel, BorderLayout.EAST);

        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(600, 400);
    }

    private void cargarDatos() {
        DefaultTableModel tableModel = (DefaultTableModel) practicasTable.getModel();
        for (PracticaDto practica : practicas) {
            Object[] row = {practica.getId(), practica.getNombre(), "info", "Eliminar"};
            tableModel.addRow(row);
        }
    }
}
