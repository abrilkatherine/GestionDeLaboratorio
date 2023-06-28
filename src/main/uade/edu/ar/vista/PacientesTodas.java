package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import main.uade.edu.ar.controller.PacienteController;
import main.uade.edu.ar.dto.PacienteDto;

public class PacientesTodas {

    private PacienteController pacienteController;
    private DefaultTableModel tableModel;

    private PacientesTodas pacientesTodas;

    public PacientesTodas(PacienteController pacienteController) {
        this.pacienteController = pacienteController;
        this.pacientesTodas = this;
        this.tableModel = new DefaultTableModel();
    }

    public JPanel createPanel() {
        // Crear un JPanel para contener todos los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar margen superior de 10 píxeles

        // Crear un JPanel para el encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());

        // Crear el título "Pacientes" a la izquierda
        JLabel titleLabel = new JLabel("Pacientes");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Crear el botón "Agregar" a la derecha
        JButton addButton = new JButton("Agregar");
        addButton.setContentAreaFilled(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setBackground(Color.decode("#0080F0"));

        // Agregar ActionListener al botón "Agregar"
        addButton.addActionListener(e -> {
            AgregarPaciente agregarPaciente = new AgregarPaciente(pacienteController, this);
            agregarPaciente.setVisible(true);
        });

        // Agregar el botón "Agregar" al JPanel del encabezado
        headerPanel.add(addButton, BorderLayout.EAST);

        // Agregar el JPanel del encabezado al JPanel principal
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear la tabla de pacientes
        JTable table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JTable createTable() {
        // Configurar el modelo de tabla
        tableModel.addColumn("Nombre");
        tableModel.addColumn("DNI");
        tableModel.addColumn("Editar");
        tableModel.addColumn("Eliminar");

        // Obtener la lista de pacientes
        List<PacienteDto> pacientes = pacienteController.getAllPacientes();
        for (PacienteDto paciente : pacientes) {
            tableModel.addRow(new Object[]{paciente.getNombre(), paciente.getDni(), "Info", "Eliminar"});
        }

        // Crear la tabla y configurar el modelo
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(2).setPreferredWidth(50); // Ancho de la columna "Editar"
        table.getColumnModel().getColumn(3).setPreferredWidth(70); // Ancho de la columna "Eliminar"

        // Agregar MouseListener a la tabla para detectar clics en las columnas "Editar" y "Eliminar"
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                // Verificar si se hizo clic en la columna "Editar"
                if (column == 2 && row < table.getRowCount()) {
                    int valorColumnaDNI = (int) tableModel.getValueAt(row, 1);


                    PacienteDto paciente = null;
                    for (PacienteDto p : pacientes) {
                        if (p.getDni() == valorColumnaDNI) {
                            paciente = p;
                            break;
                        }
                    }
                    // Crear y mostrar el diálogo de editar paciente
                    if (paciente != null) {
                        // Crear y mostrar el diálogo de editar paciente, pasando el paciente correspondiente

                        EditarPaciente editarPaciente = new EditarPaciente(paciente, pacienteController, pacientesTodas);
                        editarPaciente.setVisible(true);
                    }
                }

                // Verificar si se hizo clic en la columna "Eliminar"
                if (column == 3 && row < table.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(table, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar la fila correspondiente
                        int valorColumnaDNI = (int) tableModel.getValueAt(row, 1);


                        PacienteDto paciente = null;
                        for (PacienteDto p : pacientes) {
                            if (p.getDni() == valorColumnaDNI) {
                                paciente = p;
                                break;
                            }
                        }
                        if (paciente != null) {
                            try{
                                pacienteController.borrarPaciente(paciente.getId());
                                tableModel.removeRow(row);
                            }
                            catch (Exception exception){
                                exception.printStackTrace(); // Imprimir información de la excepción
                                // Opcional: Mostrar un mensaje de error al usuario
                                // JOptionPane.showMessageDialog(, "Error al eliminar el paciente", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                }
            }
        });


        return table;
    }


    public void actualizarTablaPacientes() {
        tableModel.setRowCount(0); // Elimina todas las filas existentes en el modelo
        List<PacienteDto> pacientes = pacienteController.getAllPacientes();
        for (PacienteDto paciente : pacientes) {
            tableModel.addRow(new Object[]{paciente.getNombre(), paciente.getDni(), "Info", "Eliminar"});
        }
    }
}
