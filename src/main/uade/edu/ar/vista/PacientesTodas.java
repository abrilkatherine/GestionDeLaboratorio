package main.uade.edu.ar.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.uade.edu.ar.model.Paciente;
import java.lang.reflect.Type;
import java.util.List;
import main.uade.edu.ar.dao.PacienteDao;

public class PacientesTodas {

    public JPanel createPanel() {
        // Crear un JPanel para contener todos los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Agregar margen superior de 10 píxeles

        // Crear un JPanel para el encabezado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());

        // Crear el título "Sucursales" a la izquierda
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
            AgregarPaciente agregarPaciente = new AgregarPaciente();
            agregarPaciente.setVisible(true);
        });

        // Agregar el botón "Agregar" al JPanel del encabezado
        headerPanel.add(addButton, BorderLayout.EAST);

        // Agregar el JPanel del encabezado al JPanel principal
        panel.add(headerPanel, BorderLayout.NORTH);

        // Crear la tabla de sucursales
        JTable table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JTable createTable() {
        // Crear un modelo de tabla personalizado que haga que todas las celdas sean no editables
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Nombre");
        model.addColumn("DNI");
        model.addColumn("Editar");
        model.addColumn("Eliminar");

        // Agregar filas de ejemplo a la tabla
        PacienteDao pacienteDao;
        try {
            pacienteDao = new PacienteDao();
            List<Paciente> pacientes = pacienteDao.getAll();
            for (Paciente paciente : pacientes) {
                model.addRow(new Object[]{paciente.getNombre(),paciente.getDni(), "Info", "Eliminar"});
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace(); // Retornar la tabla vacía en caso de error
        }

        // Crear la tabla y configurar el modelo
        JTable table = new JTable(model);
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
                    int valorColumnaDNI = (int) model.getValueAt(row, 1);

                    List<Paciente> pacientes;
                    try {
                        PacienteDao pacienteDao = new PacienteDao();
                        pacientes = pacienteDao.getAll();
                    } catch (Exception exception) {
                        // Manejo de excepciones
                        exception.printStackTrace();
                        return; // Salir del método si ocurre un error
                    }
                    Paciente paciente = null;
                    for (Paciente p : pacientes) {
                        if (p.getDni() == valorColumnaDNI) {
                            paciente = p;
                            break;
                        }
                    }
                    // Crear y mostrar el diálogo de editar sucursal
                    if (paciente != null) {
                        // Crear y mostrar el diálogo de editar sucursal, pasando la sucursal correspondiente
                        EditarPaciente editarPaciente = new EditarPaciente(paciente);
                        editarPaciente.setVisible(true);
                    }
                }

                // Verificar si se hizo clic en la columna "Eliminar"
                if (column == 3 && row < table.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(table, "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar la fila correspondiente
                        model.removeRow(row);
                    }
                }
            }
        });

        return table;
    }
}